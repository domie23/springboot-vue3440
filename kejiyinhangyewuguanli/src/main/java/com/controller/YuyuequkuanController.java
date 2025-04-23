
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
 * 预约取款
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yuyuequkuan")
public class YuyuequkuanController {
    private static final Logger logger = LoggerFactory.getLogger(YuyuequkuanController.class);

    @Autowired
    private YuyuequkuanService yuyuequkuanService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
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
        PageUtils page = yuyuequkuanService.queryPage(params);

        //字典表数据转换
        List<YuyuequkuanView> list =(List<YuyuequkuanView>)page.getList();
        for(YuyuequkuanView c:list){
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
        YuyuequkuanEntity yuyuequkuan = yuyuequkuanService.selectById(id);
        if(yuyuequkuan !=null){
            //entity转view
            YuyuequkuanView view = new YuyuequkuanView();
            BeanUtils.copyProperties( yuyuequkuan , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(yuyuequkuan.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                YinhangkaEntity yinhangka = yinhangkaService.selectById(yuyuequkuan.getYinhangkaId());
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
    public R save(@RequestBody YuyuequkuanEntity yuyuequkuan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yuyuequkuan:{}",this.getClass().getName(),yuyuequkuan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            yuyuequkuan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if(yuyuequkuan.getYuyuequkuanMoney() >100000){//大于十万
            yuyuequkuan.setYuyuequkuanYesnoTypes(1);

            YinhangkaEntity yinhangkaEntity = yinhangkaService.selectById(yuyuequkuan.getYinhangkaId());
            if(yinhangkaEntity == null)
                return R.error("查不到银行卡");
            double balance = yinhangkaEntity.getYinghangkaMoney() - yuyuequkuan.getYuyuequkuanMoney();
            if(balance<0)
                return R.error("当前银行卡余额不足以支付此次取款");
        }else{
            YinhangkaEntity yinhangkaEntity = yinhangkaService.selectById(yuyuequkuan.getYinhangkaId());
            double balance = yinhangkaEntity.getYinghangkaMoney() - yuyuequkuan.getYuyuequkuanMoney();
            if(balance<0)
                return R.error("当前银行卡余额不足以支付此次取款");
            yinhangkaEntity.setYinghangkaMoney(balance);
            yinhangkaService.updateById(yinhangkaEntity);


            YinhangkaJinejiluEntity yinhangkaJinejiluEntity = new YinhangkaJinejiluEntity();
            yinhangkaJinejiluEntity.setYinhangkaId(yinhangkaEntity.getId());
            yinhangkaJinejiluEntity.setCreateTime(new Date());
            yinhangkaJinejiluEntity.setInsertTime(new Date());
            yinhangkaJinejiluEntity.setYonghuId(yinhangkaEntity.getYonghuId());
            yinhangkaJinejiluEntity.setYinhangkaJinejiluUuidNumber(String.valueOf(new Date().getTime()));
            yinhangkaJinejiluEntity.setYinhangkaJinejiluMoney(yuyuequkuan.getYuyuequkuanMoney());
            yinhangkaJinejiluEntity.setYinhangkaJinejiluContent("取款"+yuyuequkuan.getYuyuequkuanMoney()+"元");
            yinhangkaJinejiluEntity.setJiluTypes(7);
            yinhangkaJinejiluService.insert(yinhangkaJinejiluEntity);

            yuyuequkuan.setYuyuequkuanYesnoTypes(2);
            yuyuequkuan.setYuyuequkuanYesnoText("取款低于十万,自动审核通过");
            yuyuequkuan.setYuyuequkuanShenheTime(new Date());
        }
            yuyuequkuan.setInsertTime(new Date());
            yuyuequkuan.setCreateTime(new Date());
            yuyuequkuanService.insert(yuyuequkuan);
            return R.ok();

    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YuyuequkuanEntity yuyuequkuan, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,yuyuequkuan:{}",this.getClass().getName(),yuyuequkuan.toString());

            yuyuequkuanService.updateById(yuyuequkuan);//根据id更新
            return R.ok();

    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody YuyuequkuanEntity yuyuequkuan, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,yuyuequkuan:{}",this.getClass().getName(),yuyuequkuan.toString());

        if(yuyuequkuan.getYuyuequkuanYesnoTypes() == 2){//通过
            YuyuequkuanEntity yuyuequkuanEntity = yuyuequkuanService.selectById(yuyuequkuan.getId());
            YinhangkaEntity yinhangkaEntity = yinhangkaService.selectById(yuyuequkuanEntity.getYinhangkaId());
            double balance = yinhangkaEntity.getYinghangkaMoney() - yuyuequkuanEntity.getYuyuequkuanMoney();
            if(balance<0)
                return R.error("当前银行卡余额不足以支付此次取款");
            yinhangkaEntity.setYinghangkaMoney(balance);
            yinhangkaService.updateById(yinhangkaEntity);


            YinhangkaJinejiluEntity yinhangkaJinejiluEntity = new YinhangkaJinejiluEntity();
            yinhangkaJinejiluEntity.setYinhangkaId(yinhangkaEntity.getId());
            yinhangkaJinejiluEntity.setCreateTime(new Date());
            yinhangkaJinejiluEntity.setInsertTime(new Date());
            yinhangkaJinejiluEntity.setYonghuId(yinhangkaEntity.getYonghuId());
            yinhangkaJinejiluEntity.setYinhangkaJinejiluUuidNumber(String.valueOf(new Date().getTime()));
            yinhangkaJinejiluEntity.setYinhangkaJinejiluMoney(yuyuequkuanEntity.getYuyuequkuanMoney());
            yinhangkaJinejiluEntity.setYinhangkaJinejiluContent("取款审核成功,取款"+yuyuequkuanEntity.getYuyuequkuanMoney()+"元");
            yinhangkaJinejiluEntity.setJiluTypes(7);
            yinhangkaJinejiluService.insert(yinhangkaJinejiluEntity);
//            yuyuequkuan.setYuyuequkuanTypes();
        }else if(yuyuequkuan.getYuyuequkuanYesnoTypes() == 3){//拒绝
//            yuyuequkuan.setYuyuequkuanTypes();
        }
        yuyuequkuan.setYuyuequkuanShenheTime(new Date());//审核时间
        yuyuequkuanService.updateById(yuyuequkuan);//审核
        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        yuyuequkuanService.deleteBatchIds(Arrays.asList(ids));
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
            List<YuyuequkuanEntity> yuyuequkuanList = new ArrayList<>();//上传的东西
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
                            YuyuequkuanEntity yuyuequkuanEntity = new YuyuequkuanEntity();
//                            yuyuequkuanEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            yuyuequkuanEntity.setYinhangkaId(Integer.valueOf(data.get(0)));   //银行卡 要改的
//                            yuyuequkuanEntity.setYuyuequkuanUuidNumber(data.get(0));                    //预约编号 要改的
//                            yuyuequkuanEntity.setYuyuequkuanMoney(data.get(0));                    //取款金额 要改的
//                            yuyuequkuanEntity.setYuyuequkuanContent("");//详情和图片
//                            yuyuequkuanEntity.setInsertTime(date);//时间
//                            yuyuequkuanEntity.setYuyuequkuanTime(sdf.parse(data.get(0)));          //预约日期 要改的
//                            yuyuequkuanEntity.setYuyuequkuanYesnoTypes(Integer.valueOf(data.get(0)));   //审核状态 要改的
//                            yuyuequkuanEntity.setYuyuequkuanYesnoText(data.get(0));                    //审核意见 要改的
//                            yuyuequkuanEntity.setYuyuequkuanShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            yuyuequkuanEntity.setCreateTime(date);//时间
                            yuyuequkuanList.add(yuyuequkuanEntity);


                            //把要查询是否重复的字段放入map中
                                //预约编号
                                if(seachFields.containsKey("yuyuequkuanUuidNumber")){
                                    List<String> yuyuequkuanUuidNumber = seachFields.get("yuyuequkuanUuidNumber");
                                    yuyuequkuanUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yuyuequkuanUuidNumber = new ArrayList<>();
                                    yuyuequkuanUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("yuyuequkuanUuidNumber",yuyuequkuanUuidNumber);
                                }
                        }

                        //查询是否重复
                         //预约编号
                        List<YuyuequkuanEntity> yuyuequkuanEntities_yuyuequkuanUuidNumber = yuyuequkuanService.selectList(new EntityWrapper<YuyuequkuanEntity>().in("yuyuequkuan_uuid_number", seachFields.get("yuyuequkuanUuidNumber")));
                        if(yuyuequkuanEntities_yuyuequkuanUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YuyuequkuanEntity s:yuyuequkuanEntities_yuyuequkuanUuidNumber){
                                repeatFields.add(s.getYuyuequkuanUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [预约编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yuyuequkuanService.insertBatch(yuyuequkuanList);
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
