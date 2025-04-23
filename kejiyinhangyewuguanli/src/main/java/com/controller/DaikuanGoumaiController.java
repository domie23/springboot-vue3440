
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
 * 贷款购买
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/daikuanGoumai")
public class DaikuanGoumaiController {
    private static final Logger logger = LoggerFactory.getLogger(DaikuanGoumaiController.class);

    @Autowired
    private DaikuanGoumaiService daikuanGoumaiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private DaikuanService daikuanService;
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private YinhangkaService yinhangkaService;

    @Autowired
    private ShenherenyuanService shenherenyuanService;
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
        PageUtils page = daikuanGoumaiService.queryPage(params);

        //字典表数据转换
        List<DaikuanGoumaiView> list =(List<DaikuanGoumaiView>)page.getList();
        for(DaikuanGoumaiView c:list){
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
        DaikuanGoumaiEntity daikuanGoumai = daikuanGoumaiService.selectById(id);
        if(daikuanGoumai !=null){
            //entity转view
            DaikuanGoumaiView view = new DaikuanGoumaiView();
            BeanUtils.copyProperties( daikuanGoumai , view );//把实体数据重构到view中

                //级联表
                DaikuanEntity daikuan = daikuanService.selectById(daikuanGoumai.getDaikuanId());
                if(daikuan != null){
                    BeanUtils.copyProperties( daikuan , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setDaikuanId(daikuan.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(daikuanGoumai.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                YinhangkaEntity yinhangka = yinhangkaService.selectById(daikuanGoumai.getYinhangkaId());
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
    public R save(@RequestBody DaikuanGoumaiEntity daikuanGoumai, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,daikuanGoumai:{}",this.getClass().getName(),daikuanGoumai.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            daikuanGoumai.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<DaikuanGoumaiEntity> queryWrapper = new EntityWrapper<DaikuanGoumaiEntity>()
            .eq("yonghu_id", daikuanGoumai.getYonghuId())
            .eq("daikuan_id", daikuanGoumai.getDaikuanId())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DaikuanGoumaiEntity daikuanGoumaiEntity = daikuanGoumaiService.selectOne(queryWrapper);
        if(daikuanGoumaiEntity==null){


           /* DaikuanEntity daikuanEntity = daikuanService.selectById(daikuanGoumai.getDaikuanId());
            if(daikuanEntity == null)
                return R.error("查不到要购买的贷款");

            Double daikuanJine = daikuanEntity.getDaikuanJine();
            if(daikuanJine>100000){//贷款金额大于十万
                daikuanGoumai.setDaikuanGoumaiYesnoTypes(1);
                daikuan
            }else{
                daikuanGoumai.setDaikuanGoumaiYesnoTypes(2);

            }
*/
            daikuanGoumai.setDaikuanGoumaiYesnoTypes(1);
            daikuanGoumai.setInsertTime(new Date());
            daikuanGoumai.setCreateTime(new Date());
            daikuanGoumaiService.insert(daikuanGoumai);
            return R.ok();
        }else {
            return R.error(511,"该用户已经购买过此贷款了");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody DaikuanGoumaiEntity daikuanGoumai, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,daikuanGoumai:{}",this.getClass().getName(),daikuanGoumai.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            daikuanGoumai.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<DaikuanGoumaiEntity> queryWrapper = new EntityWrapper<DaikuanGoumaiEntity>()
            .notIn("id",daikuanGoumai.getId())
            .andNew()
            .eq("yonghu_id", daikuanGoumai.getYonghuId())
            .eq("daikuan_id", daikuanGoumai.getDaikuanId())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DaikuanGoumaiEntity daikuanGoumaiEntity = daikuanGoumaiService.selectOne(queryWrapper);
        if(daikuanGoumaiEntity==null){
            daikuanGoumaiService.updateById(daikuanGoumai);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"该用户已经购买过此贷款了");
        }
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody DaikuanGoumaiEntity daikuanGoumai, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,daikuanGoumai:{}",this.getClass().getName(),daikuanGoumai.toString());

        if(daikuanGoumai.getDaikuanGoumaiYesnoTypes() == 2){//通过

            DaikuanGoumaiEntity daikuanGoumaiEntity = daikuanGoumaiService.selectById(daikuanGoumai.getId());
            DaikuanEntity daikuanEntity = daikuanService.selectById(daikuanGoumaiEntity.getDaikuanId());
            Double daikuanJine = daikuanEntity.getDaikuanJine();

            YinhangkaEntity yinhangkaEntity = yinhangkaService.selectById(daikuanGoumaiEntity.getYinhangkaId());
            if(yinhangkaEntity == null)
                return R.error("查不到贷款账户所绑定收款银行卡");
            yinhangkaEntity.setYinghangkaMoney(yinhangkaEntity.getYinghangkaMoney()+daikuanJine);
            yinhangkaService.updateById(yinhangkaEntity);//更新银行卡余额字段


            YinhangkaJinejiluEntity yinhangkaJinejiluEntity = new YinhangkaJinejiluEntity();
            yinhangkaJinejiluEntity.setYinhangkaId(yinhangkaEntity.getId());
            yinhangkaJinejiluEntity.setCreateTime(new Date());
            yinhangkaJinejiluEntity.setInsertTime(new Date());
            yinhangkaJinejiluEntity.setYonghuId(yinhangkaEntity.getYonghuId());
            yinhangkaJinejiluEntity.setYinhangkaJinejiluUuidNumber(String.valueOf(new Date().getTime()));
            yinhangkaJinejiluEntity.setYinhangkaJinejiluMoney(daikuanJine);
            yinhangkaJinejiluEntity.setYinhangkaJinejiluContent("购买贷款通过,金额增加"+daikuanJine+"元");
            yinhangkaJinejiluEntity.setJiluTypes(4);
            yinhangkaJinejiluService.insert(yinhangkaJinejiluEntity);





//            daikuanGoumai.setDaikuanGoumaiTypes();
        }else if(daikuanGoumai.getDaikuanGoumaiYesnoTypes() == 3){//拒绝
//            daikuanGoumai.setDaikuanGoumaiTypes();
        }
        daikuanGoumai.setDaikuanGoumaiShenheTime(new Date());//审核时间
        daikuanGoumaiService.updateById(daikuanGoumai);//审核
        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        daikuanGoumaiService.deleteBatchIds(Arrays.asList(ids));
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
            List<DaikuanGoumaiEntity> daikuanGoumaiList = new ArrayList<>();//上传的东西
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
                            DaikuanGoumaiEntity daikuanGoumaiEntity = new DaikuanGoumaiEntity();
//                            daikuanGoumaiEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            daikuanGoumaiEntity.setYinhangkaId(Integer.valueOf(data.get(0)));   //银行卡 要改的
//                            daikuanGoumaiEntity.setDaikuanId(Integer.valueOf(data.get(0)));   //贷款 要改的
//                            daikuanGoumaiEntity.setDaikuanGoumaiUuidNumber(data.get(0));                    //贷款购买编号 要改的
//                            daikuanGoumaiEntity.setInsertTime(date);//时间
//                            daikuanGoumaiEntity.setDaikuanGoumaiYesnoTypes(Integer.valueOf(data.get(0)));   //审核状态 要改的
//                            daikuanGoumaiEntity.setDaikuanGoumaiYesnoText(data.get(0));                    //审核意见 要改的
//                            daikuanGoumaiEntity.setDaikuanGoumaiShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            daikuanGoumaiEntity.setCreateTime(date);//时间
                            daikuanGoumaiList.add(daikuanGoumaiEntity);


                            //把要查询是否重复的字段放入map中
                                //贷款购买编号
                                if(seachFields.containsKey("daikuanGoumaiUuidNumber")){
                                    List<String> daikuanGoumaiUuidNumber = seachFields.get("daikuanGoumaiUuidNumber");
                                    daikuanGoumaiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> daikuanGoumaiUuidNumber = new ArrayList<>();
                                    daikuanGoumaiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("daikuanGoumaiUuidNumber",daikuanGoumaiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //贷款购买编号
                        List<DaikuanGoumaiEntity> daikuanGoumaiEntities_daikuanGoumaiUuidNumber = daikuanGoumaiService.selectList(new EntityWrapper<DaikuanGoumaiEntity>().in("daikuan_goumai_uuid_number", seachFields.get("daikuanGoumaiUuidNumber")));
                        if(daikuanGoumaiEntities_daikuanGoumaiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(DaikuanGoumaiEntity s:daikuanGoumaiEntities_daikuanGoumaiUuidNumber){
                                repeatFields.add(s.getDaikuanGoumaiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [贷款购买编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        daikuanGoumaiService.insertBatch(daikuanGoumaiList);
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
