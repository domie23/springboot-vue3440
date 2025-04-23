
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 理财产品购买
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/licaicanpinGoumai")
public class LicaicanpinGoumaiController {
    private static final Logger logger = LoggerFactory.getLogger(LicaicanpinGoumaiController.class);

    @Autowired
    private LicaicanpinGoumaiService licaicanpinGoumaiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private LicaicanpinService licaicanpinService;

    @Autowired
    private ShenherenyuanService shenherenyuanService;
    @Autowired
    private YewurenyuanService yewurenyuanService;
    @Autowired
    private YinhangkaJinejiluService yinhangkaJinejiluService;
    @Autowired
    private YinhangkaService yinhangkaService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("审核人员".equals(role))
            params.put("shenherenyuanId",request.getSession().getAttribute("userId"));
        else if("业务人员".equals(role))
            params.put("yewurenyuanId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = licaicanpinGoumaiService.queryPage(params);

        //字典表数据转换
        List<LicaicanpinGoumaiView> list =(List<LicaicanpinGoumaiView>)page.getList();
        for(LicaicanpinGoumaiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        LicaicanpinGoumaiEntity licaicanpinGoumai = licaicanpinGoumaiService.selectById(id);
        if(licaicanpinGoumai !=null){
            //entity转view
            LicaicanpinGoumaiView view = new LicaicanpinGoumaiView();
            BeanUtils.copyProperties( licaicanpinGoumai , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(licaicanpinGoumai.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                LicaicanpinEntity licaicanpin = licaicanpinService.selectById(licaicanpinGoumai.getLicaicanpinId());
                if(licaicanpin != null){
                    BeanUtils.copyProperties( licaicanpin , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setLicaicanpinId(licaicanpin.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody LicaicanpinGoumaiEntity licaicanpinGoumai, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,licaicanpinGoumai:{}",this.getClass().getName(),licaicanpinGoumai.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            licaicanpinGoumai.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));


        LicaicanpinEntity licaicanpinEntity = licaicanpinService.selectById(licaicanpinGoumai.getLicaicanpinId());
        if(licaicanpinEntity == null)
            return R.error("查不到理财产品");
        Double balanceFenshu = licaicanpinEntity.getLicaicanpinFenshu() - licaicanpinGoumai.getLicaicanpinGoumaiFenshu();
        if(balanceFenshu <0)
            return R.error("购买份数不能大于总份数");
        licaicanpinGoumai.setLicaicanpinGoumaiHuafei(licaicanpinEntity.getLicaicanpinJine()*licaicanpinGoumai.getLicaicanpinGoumaiFenshu());


        /**
         * 检查银行卡中余额是否够这次购买
         */
        List<YinhangkaEntity> yinhangkaEntityList = yinhangkaService.selectList(
                new EntityWrapper<YinhangkaEntity>().eq("yonghu_id", licaicanpinGoumai.getYonghuId())
        );
        if(yinhangkaEntityList == null)
            return R.error("当前账户下没有绑定银行卡,无法支付");
        boolean flag = true;
        for(YinhangkaEntity y:yinhangkaEntityList){
            if(y.getYinghangkaMoney() >licaicanpinGoumai.getLicaicanpinGoumaiHuafei())
                flag = false;
        }
        if(flag)
            return R.error("当前用户绑定的银行卡中并没有金额大于"+licaicanpinGoumai.getLicaicanpinGoumaiHuafei()+"元的,购买失败");
        if(licaicanpinGoumai.getLicaicanpinGoumaiHuafei()>100000){//大于十万 人工审核
            licaicanpinGoumai.setLicaicanpinGoumaiYesnoTypes(1);
        }else{//小于十万 自动审核通过

            String yinhangkakahao= "";
            boolean flag111= true;
            for(YinhangkaEntity y:yinhangkaEntityList){
                if(flag111){//没支付
                    if(y.getYinghangkaMoney() >licaicanpinGoumai.getLicaicanpinGoumaiHuafei()){
                        flag111 = false;
                        double balance = y.getYinghangkaMoney() - licaicanpinGoumai.getLicaicanpinGoumaiHuafei();
                        y.setYinghangkaMoney(balance);

                        yinhangkakahao = y.getYinhangkaUuidNumber();
                        yinhangkaService.updateById(y);//更新银行卡余额字段

                        YinhangkaJinejiluEntity yinhangkaJinejiluEntity = new YinhangkaJinejiluEntity();
                        yinhangkaJinejiluEntity.setYinhangkaId(y.getId());
                        yinhangkaJinejiluEntity.setCreateTime(new Date());
                        yinhangkaJinejiluEntity.setInsertTime(new Date());
                        yinhangkaJinejiluEntity.setYonghuId(y.getYonghuId());
                        yinhangkaJinejiluEntity.setYinhangkaJinejiluUuidNumber(String.valueOf(new Date().getTime()));
                        yinhangkaJinejiluEntity.setYinhangkaJinejiluMoney(licaicanpinGoumai.getLicaicanpinGoumaiHuafei());
                        yinhangkaJinejiluEntity.setYinhangkaJinejiluContent("购买理财产品["+licaicanpinEntity.getLicaicanpinName()+"],金额减少"+licaicanpinGoumai.getLicaicanpinGoumaiHuafei()+"元");
                        yinhangkaJinejiluEntity.setJiluTypes(3);
                        yinhangkaJinejiluService.insert(yinhangkaJinejiluEntity);

                        licaicanpinEntity.setLicaicanpinFenshu(balanceFenshu.intValue());
                        licaicanpinService.updateById(licaicanpinEntity);

                    }
                }
            }

            if(flag111)
                return R.error("当前用户的所有银行卡余额都不够支付此次理财支付,请存款后再购买");
            licaicanpinGoumai.setLicaicanpinGoumaiYesnoTypes(2);
            licaicanpinGoumai.setLicaicanpinGoumaiShenheTime(new Date());
            licaicanpinGoumai.setLicaicanpinGoumaiYesnoText("购买理财产品["+licaicanpinEntity.getLicaicanpinName()+"],花费账户为["+yinhangkakahao+"]的银行卡内的"+licaicanpinGoumai.getLicaicanpinGoumaiHuafei()+"元,低于十万自动审核通过");

        }



        licaicanpinGoumai.setInsertTime(new Date());
        licaicanpinGoumai.setCreateTime(new Date());
        licaicanpinGoumaiService.insert(licaicanpinGoumai);
        return R.ok();

    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody LicaicanpinGoumaiEntity licaicanpinGoumai, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,licaicanpinGoumai:{}",this.getClass().getName(),licaicanpinGoumai.toString());

            licaicanpinGoumaiService.updateById(licaicanpinGoumai);//根据id更新
            return R.ok();

    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody LicaicanpinGoumaiEntity licaicanpinGoumai, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,licaicanpinGoumai:{}",this.getClass().getName(),licaicanpinGoumai.toString());

        if(licaicanpinGoumai.getLicaicanpinGoumaiYesnoTypes() == 2){//通过
            LicaicanpinGoumaiEntity licaicanpinGoumaiEntity = licaicanpinGoumaiService.selectById(licaicanpinGoumai.getId());
            LicaicanpinEntity licaicanpinEntity = licaicanpinService.selectById(licaicanpinGoumaiEntity.getLicaicanpinId());
            if(licaicanpinEntity == null)
                return R.error("查不到要购买的理财产品,无法审核通过");


            Double balanceFenshu = licaicanpinEntity.getLicaicanpinFenshu() - licaicanpinGoumaiEntity.getLicaicanpinGoumaiFenshu();
            if(balanceFenshu <0)
                return R.error("购买份数超过最大份数,无法购买");

            licaicanpinEntity.setLicaicanpinFenshu(balanceFenshu.intValue());

            /**
             * 检查银行卡中余额是否够这次购买
             */
            List<YinhangkaEntity> yinhangkaEntityList = yinhangkaService.selectList(
                    new EntityWrapper<YinhangkaEntity>().eq("yonghu_id", licaicanpinGoumaiEntity.getYonghuId())
            );
            if(yinhangkaEntityList == null)
                return R.error("当前账户下没有绑定银行卡,无法支付此次购买");
            boolean flag = true;
            for(YinhangkaEntity y:yinhangkaEntityList){
                if(flag){//没支付
                    if(y.getYinghangkaMoney() >licaicanpinGoumaiEntity.getLicaicanpinGoumaiHuafei()){
                        flag = false;
                        double balance = y.getYinghangkaMoney() - licaicanpinGoumaiEntity.getLicaicanpinGoumaiHuafei();
                        y.setYinghangkaMoney(balance);

                        yinhangkaService.updateById(y);//更新银行卡余额字段

                        YinhangkaJinejiluEntity yinhangkaJinejiluEntity = new YinhangkaJinejiluEntity();
                        yinhangkaJinejiluEntity.setYinhangkaId(y.getId());
                        yinhangkaJinejiluEntity.setCreateTime(new Date());
                        yinhangkaJinejiluEntity.setInsertTime(new Date());
                        yinhangkaJinejiluEntity.setYonghuId(y.getYonghuId());
                        yinhangkaJinejiluEntity.setYinhangkaJinejiluUuidNumber(String.valueOf(new Date().getTime()));
                        yinhangkaJinejiluEntity.setYinhangkaJinejiluMoney(licaicanpinGoumaiEntity.getLicaicanpinGoumaiHuafei());
                        yinhangkaJinejiluEntity.setYinhangkaJinejiluContent("购买理财产品["+licaicanpinEntity.getLicaicanpinName()+"],金额减少"+licaicanpinGoumaiEntity.getLicaicanpinGoumaiHuafei()+"元");
                        yinhangkaJinejiluEntity.setJiluTypes(3);
                        yinhangkaJinejiluService.insert(yinhangkaJinejiluEntity);




                    }
                }
            }

            if(flag)
                return R.error("当前用户的所有银行卡余额都不够支付此次理财支付,请存款后再购买");

            licaicanpinService.updateById(licaicanpinEntity);


//            licaicanpinGoumai.setLicaicanpinGoumaiTypes();
        }else if(licaicanpinGoumai.getLicaicanpinGoumaiYesnoTypes() == 3){//拒绝
//            licaicanpinGoumai.setLicaicanpinGoumaiTypes();
        }
        licaicanpinGoumai.setLicaicanpinGoumaiShenheTime(new Date());//审核时间
        licaicanpinGoumaiService.updateById(licaicanpinGoumai);//审核
        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        licaicanpinGoumaiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<LicaicanpinGoumaiEntity> licaicanpinGoumaiList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("../../upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            LicaicanpinGoumaiEntity licaicanpinGoumaiEntity = new LicaicanpinGoumaiEntity();
//                            licaicanpinGoumaiEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            licaicanpinGoumaiEntity.setLicaicanpinId(Integer.valueOf(data.get(0)));   //理财产品 要改的
//                            licaicanpinGoumaiEntity.setLicaicanpinGoumaiUuidNumber(data.get(0));                    //理财产品购买编号 要改的
//                            licaicanpinGoumaiEntity.setLicaicanpinGoumaiFenshu(data.get(0));                    //购买份数 要改的
//                            licaicanpinGoumaiEntity.setLicaicanpinGoumaiHuafei(data.get(0));                    //花费总额 要改的
//                            licaicanpinGoumaiEntity.setInsertTime(date);//时间
//                            licaicanpinGoumaiEntity.setLicaicanpinGoumaiYesnoTypes(Integer.valueOf(data.get(0)));   //审核状态 要改的
//                            licaicanpinGoumaiEntity.setLicaicanpinGoumaiYesnoText(data.get(0));                    //审核意见 要改的
//                            licaicanpinGoumaiEntity.setLicaicanpinGoumaiShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            licaicanpinGoumaiEntity.setCreateTime(date);//时间
                            licaicanpinGoumaiList.add(licaicanpinGoumaiEntity);


                            //把要查询是否重复的字段放入map中
                                //理财产品购买编号
                                if(seachFields.containsKey("licaicanpinGoumaiUuidNumber")){
                                    List<String> licaicanpinGoumaiUuidNumber = seachFields.get("licaicanpinGoumaiUuidNumber");
                                    licaicanpinGoumaiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> licaicanpinGoumaiUuidNumber = new ArrayList<>();
                                    licaicanpinGoumaiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("licaicanpinGoumaiUuidNumber",licaicanpinGoumaiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //理财产品购买编号
                        List<LicaicanpinGoumaiEntity> licaicanpinGoumaiEntities_licaicanpinGoumaiUuidNumber = licaicanpinGoumaiService.selectList(new EntityWrapper<LicaicanpinGoumaiEntity>().in("licaicanpin_goumai_uuid_number", seachFields.get("licaicanpinGoumaiUuidNumber")));
                        if(licaicanpinGoumaiEntities_licaicanpinGoumaiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(LicaicanpinGoumaiEntity s:licaicanpinGoumaiEntities_licaicanpinGoumaiUuidNumber){
                                repeatFields.add(s.getLicaicanpinGoumaiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [理财产品购买编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        licaicanpinGoumaiService.insertBatch(licaicanpinGoumaiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
