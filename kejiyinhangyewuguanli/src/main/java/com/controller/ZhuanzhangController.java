
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
 * 转账
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zhuanzhang")
public class ZhuanzhangController {
    private static final Logger logger = LoggerFactory.getLogger(ZhuanzhangController.class);

    @Autowired
    private ZhuanzhangService zhuanzhangService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private ShenherenyuanService shenherenyuanService;
    @Autowired
    private YinhangkaService yinhangkaService;

    @Autowired
    private YewurenyuanService yewurenyuanService;
    @Autowired
    private YinhangkaJinejiluService yinhangkaJinejiluService;


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
        PageUtils page = zhuanzhangService.queryPage(params);

        //字典表数据转换
        List<ZhuanzhangView> list =(List<ZhuanzhangView>)page.getList();
        for(ZhuanzhangView c:list){
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
        ZhuanzhangEntity zhuanzhang = zhuanzhangService.selectById(id);
        if(zhuanzhang !=null){
            //entity转view
            ZhuanzhangView view = new ZhuanzhangView();
            BeanUtils.copyProperties( zhuanzhang , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(zhuanzhang.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                ShenherenyuanEntity shenherenyuan = shenherenyuanService.selectById(zhuanzhang.getShenherenyuanId());
                if(shenherenyuan != null){
                    BeanUtils.copyProperties( shenherenyuan , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShenherenyuanId(shenherenyuan.getId());
                }
                //级联表
                YinhangkaEntity yinhangka = yinhangkaService.selectById(zhuanzhang.getYinhangkaId());
                if(yinhangka != null){
                    BeanUtils.copyProperties( yinhangka , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYinhangkaId(yinhangka.getId());
                    view.setYinhangkaYonghuId(yinhangka.getYonghuId());
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
    public R save(@RequestBody ZhuanzhangEntity zhuanzhang, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zhuanzhang:{}",this.getClass().getName(),zhuanzhang.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            zhuanzhang.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("审核人员".equals(role))
            zhuanzhang.setShenherenyuanId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));


        /**
         * 查出转出方信息
         */
        YinhangkaEntity yinhangkaEntity = yinhangkaService.selectById(zhuanzhang.getYinhangkaId());//我方银行卡
        if(yinhangkaEntity == null)
            return R.error("查不到银行卡");
        double balance = yinhangkaEntity.getYinghangkaMoney() - zhuanzhang.getZhuanzhangMoney();
        if(balance<0)
            return R.error("当前银行卡余额不足以支付此次转账");

        /**
         * 到达银行卡验证
         */
        YinhangkaEntity daodazhanghu = yinhangkaService.selectOne(
                new EntityWrapper<YinhangkaEntity>()
                        .eq("yinhangka_uuid_number", zhuanzhang.getShoukuanfangZhanghu())
        );
        if(daodazhanghu == null)
            return R.error("查不到到达账户");
        YonghuEntity yonghuEntity = yonghuService.selectById(daodazhanghu.getYonghuId());
        if(!yonghuEntity.getYonghuName().equals(zhuanzhang.getShoukuanfangName()))
            return R.error("收款方账户绑定的用户姓名不正确,请核实后再转账");
        if(zhuanzhang.getZhuanzhangMoney() >100000){//大于十万
            zhuanzhang.setZhuanzhangYesnoTypes(1);
            zhuanzhang.setZhuanzhangTypes(1);
        }else{

            /**
             * 转出方账户信息
             */
            yinhangkaEntity.setYinghangkaMoney(balance);
            /**
             * 到达方账户信息
             */
            daodazhanghu.setYinghangkaMoney(daodazhanghu.getYinghangkaMoney()+zhuanzhang.getZhuanzhangMoney());
            yinhangkaService.updateById(yinhangkaEntity);//转出方更新信息
            yinhangkaService.updateById(daodazhanghu);//到达方更新信息


            YinhangkaJinejiluEntity yinhangkaJinejiluEntity = new YinhangkaJinejiluEntity();
            yinhangkaJinejiluEntity.setYinhangkaId(yinhangkaEntity.getId());
            yinhangkaJinejiluEntity.setCreateTime(new Date());
            yinhangkaJinejiluEntity.setInsertTime(new Date());
            yinhangkaJinejiluEntity.setYonghuId(yinhangkaEntity.getYonghuId());
            yinhangkaJinejiluEntity.setYinhangkaJinejiluUuidNumber(String.valueOf(new Date().getTime()));
            yinhangkaJinejiluEntity.setYinhangkaJinejiluMoney(zhuanzhang.getZhuanzhangMoney());
            yinhangkaJinejiluEntity.setYinhangkaJinejiluContent("转账转出"+zhuanzhang.getZhuanzhangMoney()+"元");
            yinhangkaJinejiluEntity.setJiluTypes(1);
            yinhangkaJinejiluService.insert(yinhangkaJinejiluEntity);

            YinhangkaJinejiluEntity yinhangkaJinejiluEntity1 = new YinhangkaJinejiluEntity();
            yinhangkaJinejiluEntity1.setYinhangkaId(daodazhanghu.getId());
            yinhangkaJinejiluEntity1.setCreateTime(new Date());
            yinhangkaJinejiluEntity1.setInsertTime(new Date());
            yinhangkaJinejiluEntity1.setYonghuId(daodazhanghu.getYonghuId());
            yinhangkaJinejiluEntity1.setYinhangkaJinejiluUuidNumber(String.valueOf(new Date().getTime()+String.valueOf(new Random().nextInt(100))));
            yinhangkaJinejiluEntity1.setYinhangkaJinejiluMoney(zhuanzhang.getZhuanzhangMoney());
            yinhangkaJinejiluEntity1.setYinhangkaJinejiluContent("转账转入"+zhuanzhang.getZhuanzhangMoney()+"元");
            yinhangkaJinejiluEntity1.setJiluTypes(2);
            yinhangkaJinejiluService.insert(yinhangkaJinejiluEntity1);


            zhuanzhang.setZhuanzhangTypes(2);
            zhuanzhang.setZhuanzhangYesnoTypes(2);
            zhuanzhang.setZhuanzhangYesnoText("转账低于十万,自动审核通过");
            zhuanzhang.setZhuanzhangShenheTime(new Date());
        }

            zhuanzhang.setInsertTime(new Date());
            zhuanzhang.setCreateTime(new Date());
            zhuanzhangService.insert(zhuanzhang);
            return R.ok();

    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZhuanzhangEntity zhuanzhang, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,zhuanzhang:{}",this.getClass().getName(),zhuanzhang.toString());

            zhuanzhangService.updateById(zhuanzhang);//根据id更新
            return R.ok();

    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody ZhuanzhangEntity zhuanzhangEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,zhuanzhangEntity:{}",this.getClass().getName(),zhuanzhangEntity.toString());

        if(zhuanzhangEntity.getZhuanzhangYesnoTypes() == 2){//通过
            ZhuanzhangEntity zhuanzhang = zhuanzhangService.selectById(zhuanzhangEntity.getId());

            /**
             * 查出转出方信息
             */
            YinhangkaEntity yinhangkaEntity = yinhangkaService.selectById(zhuanzhang.getYinhangkaId());//我方银行卡
            if(yinhangkaEntity == null)
                return R.error("查不到银行卡");
            double balance = yinhangkaEntity.getYinghangkaMoney() - zhuanzhang.getZhuanzhangMoney();
            if(balance<0)
                return R.error("当前银行卡余额不足以支付此次转账");

            /**
             * 到达银行卡验证
             */
            YinhangkaEntity daodazhanghu = yinhangkaService.selectOne(
                    new EntityWrapper<YinhangkaEntity>()
                            .eq("yinhangka_uuid_number", zhuanzhang.getShoukuanfangZhanghu())
            );
            if(daodazhanghu == null)
                return R.error("查不到到达账户");
            YonghuEntity yonghuEntity = yonghuService.selectById(daodazhanghu.getYonghuId());
            if(!yonghuEntity.getYonghuName().equals(zhuanzhang.getShoukuanfangName()))
                return R.error("收款方账户绑定的用户姓名不正确,请核实后再转账");


            /**
             * 转出方账户信息
             */
            yinhangkaEntity.setYinghangkaMoney(balance);
            /**
             * 到达方账户信息
             */
            daodazhanghu.setYinghangkaMoney(daodazhanghu.getYinghangkaMoney()+zhuanzhang.getZhuanzhangMoney());
            yinhangkaService.updateById(yinhangkaEntity);//转出方更新信息
            yinhangkaService.updateById(daodazhanghu);//到达方更新信息


            YinhangkaJinejiluEntity yinhangkaJinejiluEntity = new YinhangkaJinejiluEntity();
            yinhangkaJinejiluEntity.setYinhangkaId(yinhangkaEntity.getId());
            yinhangkaJinejiluEntity.setCreateTime(new Date());
            yinhangkaJinejiluEntity.setInsertTime(new Date());
            yinhangkaJinejiluEntity.setYonghuId(yinhangkaEntity.getYonghuId());
            yinhangkaJinejiluEntity.setYinhangkaJinejiluUuidNumber(String.valueOf(new Date().getTime()));
            yinhangkaJinejiluEntity.setYinhangkaJinejiluMoney(zhuanzhang.getZhuanzhangMoney());
            yinhangkaJinejiluEntity.setYinhangkaJinejiluContent("转账转出"+zhuanzhang.getZhuanzhangMoney()+"元");
            yinhangkaJinejiluEntity.setJiluTypes(1);
            yinhangkaJinejiluService.insert(yinhangkaJinejiluEntity);

            YinhangkaJinejiluEntity yinhangkaJinejiluEntity1 = new YinhangkaJinejiluEntity();
            yinhangkaJinejiluEntity1.setYinhangkaId(daodazhanghu.getId());
            yinhangkaJinejiluEntity1.setCreateTime(new Date());
            yinhangkaJinejiluEntity1.setInsertTime(new Date());
            yinhangkaJinejiluEntity1.setYonghuId(daodazhanghu.getYonghuId());
            yinhangkaJinejiluEntity1.setYinhangkaJinejiluUuidNumber(String.valueOf(new Date().getTime()+String.valueOf(new Random().nextInt(100))));
            yinhangkaJinejiluEntity1.setYinhangkaJinejiluMoney(zhuanzhang.getZhuanzhangMoney());
            yinhangkaJinejiluEntity1.setYinhangkaJinejiluContent("转账转入"+zhuanzhang.getZhuanzhangMoney()+"元");
            yinhangkaJinejiluEntity1.setJiluTypes(2);
            yinhangkaJinejiluService.insert(yinhangkaJinejiluEntity1);

            zhuanzhangEntity.setZhuanzhangTypes(2);
        }else if(zhuanzhangEntity.getZhuanzhangYesnoTypes() == 3){//拒绝
            zhuanzhangEntity.setZhuanzhangTypes(3);
        }
        zhuanzhangEntity.setZhuanzhangShenheTime(new Date());//审核时间
        zhuanzhangService.updateById(zhuanzhangEntity);//审核
        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        zhuanzhangService.deleteBatchIds(Arrays.asList(ids));
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
            List<ZhuanzhangEntity> zhuanzhangList = new ArrayList<>();//上传的东西
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
                            ZhuanzhangEntity zhuanzhangEntity = new ZhuanzhangEntity();
//                            zhuanzhangEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            zhuanzhangEntity.setYinhangkaId(Integer.valueOf(data.get(0)));   //银行卡 要改的
//                            zhuanzhangEntity.setShenherenyuanId(Integer.valueOf(data.get(0)));   //审核人员 要改的
//                            zhuanzhangEntity.setZhuanzhangUuidNumber(data.get(0));                    //转账流水号 要改的
//                            zhuanzhangEntity.setShoukuanfangName(data.get(0));                    //收款方姓名 要改的
//                            zhuanzhangEntity.setShoukuanfangZhanghu(data.get(0));                    //收款方账户 要改的
//                            zhuanzhangEntity.setZhuanzhangMoney(data.get(0));                    //转账金额 要改的
//                            zhuanzhangEntity.setZhuanzhangContent("");//详情和图片
//                            zhuanzhangEntity.setInsertTime(date);//时间
//                            zhuanzhangEntity.setZhuanzhangTypes(Integer.valueOf(data.get(0)));   //转账状态 要改的
//                            zhuanzhangEntity.setZhuanzhangYesnoTypes(Integer.valueOf(data.get(0)));   //审核状态 要改的
//                            zhuanzhangEntity.setZhuanzhangYesnoText(data.get(0));                    //审核意见 要改的
//                            zhuanzhangEntity.setZhuanzhangShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            zhuanzhangEntity.setCreateTime(date);//时间
                            zhuanzhangList.add(zhuanzhangEntity);


                            //把要查询是否重复的字段放入map中
                                //转账流水号
                                if(seachFields.containsKey("zhuanzhangUuidNumber")){
                                    List<String> zhuanzhangUuidNumber = seachFields.get("zhuanzhangUuidNumber");
                                    zhuanzhangUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> zhuanzhangUuidNumber = new ArrayList<>();
                                    zhuanzhangUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("zhuanzhangUuidNumber",zhuanzhangUuidNumber);
                                }
                        }

                        //查询是否重复
                         //转账流水号
                        List<ZhuanzhangEntity> zhuanzhangEntities_zhuanzhangUuidNumber = zhuanzhangService.selectList(new EntityWrapper<ZhuanzhangEntity>().in("zhuanzhang_uuid_number", seachFields.get("zhuanzhangUuidNumber")));
                        if(zhuanzhangEntities_zhuanzhangUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZhuanzhangEntity s:zhuanzhangEntities_zhuanzhangUuidNumber){
                                repeatFields.add(s.getZhuanzhangUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [转账流水号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        zhuanzhangService.insertBatch(zhuanzhangList);
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
