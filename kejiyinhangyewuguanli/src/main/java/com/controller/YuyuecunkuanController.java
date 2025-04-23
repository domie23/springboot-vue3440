
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
 * 预约存款
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yuyuecunkuan")
public class YuyuecunkuanController {
    private static final Logger logger = LoggerFactory.getLogger(YuyuecunkuanController.class);

    @Autowired
    private YuyuecunkuanService yuyuecunkuanService;


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
        PageUtils page = yuyuecunkuanService.queryPage(params);

        //字典表数据转换
        List<YuyuecunkuanView> list =(List<YuyuecunkuanView>)page.getList();
        for(YuyuecunkuanView c:list){
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
        YuyuecunkuanEntity yuyuecunkuan = yuyuecunkuanService.selectById(id);
        if(yuyuecunkuan !=null){
            //entity转view
            YuyuecunkuanView view = new YuyuecunkuanView();
            BeanUtils.copyProperties( yuyuecunkuan , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(yuyuecunkuan.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                ShenherenyuanEntity shenherenyuan = shenherenyuanService.selectById(yuyuecunkuan.getShenherenyuanId());
                if(shenherenyuan != null){
                    BeanUtils.copyProperties( shenherenyuan , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShenherenyuanId(shenherenyuan.getId());
                }
                //级联表
                YinhangkaEntity yinhangka = yinhangkaService.selectById(yuyuecunkuan.getYinhangkaId());
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
    public R save(@RequestBody YuyuecunkuanEntity yuyuecunkuan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yuyuecunkuan:{}",this.getClass().getName(),yuyuecunkuan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            yuyuecunkuan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("审核人员".equals(role))
            yuyuecunkuan.setShenherenyuanId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        if(yuyuecunkuan.getYuyuecunkuanMoney() >100000){//大于十万
            yuyuecunkuan.setYuyuecunkuanYesnoTypes(1);


        }else{
            YinhangkaEntity yinhangkaEntity = yinhangkaService.selectById(yuyuecunkuan.getYinhangkaId());
            yinhangkaEntity.setYinghangkaMoney(yinhangkaEntity.getYinghangkaMoney()+yuyuecunkuan.getYuyuecunkuanMoney());
            yinhangkaService.updateById(yinhangkaEntity);


            YinhangkaJinejiluEntity yinhangkaJinejiluEntity = new YinhangkaJinejiluEntity();
            yinhangkaJinejiluEntity.setYinhangkaId(yinhangkaEntity.getId());
            yinhangkaJinejiluEntity.setCreateTime(new Date());
            yinhangkaJinejiluEntity.setInsertTime(new Date());
            yinhangkaJinejiluEntity.setYonghuId(yinhangkaEntity.getYonghuId());
            yinhangkaJinejiluEntity.setYinhangkaJinejiluUuidNumber(String.valueOf(new Date().getTime()));
            yinhangkaJinejiluEntity.setYinhangkaJinejiluMoney(yuyuecunkuan.getYuyuecunkuanMoney());
            yinhangkaJinejiluEntity.setYinhangkaJinejiluContent("存款"+yuyuecunkuan.getYuyuecunkuanMoney()+"元");
            yinhangkaJinejiluEntity.setJiluTypes(6);
            yinhangkaJinejiluService.insert(yinhangkaJinejiluEntity);

            yuyuecunkuan.setYuyuecunkuanYesnoTypes(2);
            yuyuecunkuan.setYuyuecunkuanYesnoText("存款低于十万,自动审核通过");
            yuyuecunkuan.setYuyuecunkuanShenheTime(new Date());
        }


        yuyuecunkuan.setInsertTime(new Date());
        yuyuecunkuan.setCreateTime(new Date());
        yuyuecunkuanService.insert(yuyuecunkuan);
        return R.ok();

    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YuyuecunkuanEntity yuyuecunkuan, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,yuyuecunkuan:{}",this.getClass().getName(),yuyuecunkuan.toString());

            yuyuecunkuanService.updateById(yuyuecunkuan);//根据id更新
            return R.ok();

    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody YuyuecunkuanEntity yuyuecunkuan, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,yuyuecunkuan:{}",this.getClass().getName(),yuyuecunkuan.toString());

        if(yuyuecunkuan.getYuyuecunkuanYesnoTypes() == 2){//通过

            YuyuecunkuanEntity yuyuecunkuanEntity = yuyuecunkuanService.selectById(yuyuecunkuan.getId());
            YinhangkaEntity yinhangkaEntity = yinhangkaService.selectById(yuyuecunkuanEntity.getYinhangkaId());
            yinhangkaEntity.setYinghangkaMoney(yinhangkaEntity.getYinghangkaMoney()+yuyuecunkuanEntity.getYuyuecunkuanMoney());
            yinhangkaService.updateById(yinhangkaEntity);


            YinhangkaJinejiluEntity yinhangkaJinejiluEntity = new YinhangkaJinejiluEntity();
            yinhangkaJinejiluEntity.setYinhangkaId(yinhangkaEntity.getId());
            yinhangkaJinejiluEntity.setCreateTime(new Date());
            yinhangkaJinejiluEntity.setInsertTime(new Date());
            yinhangkaJinejiluEntity.setYonghuId(yinhangkaEntity.getYonghuId());
            yinhangkaJinejiluEntity.setYinhangkaJinejiluUuidNumber(String.valueOf(new Date().getTime()));
            yinhangkaJinejiluEntity.setYinhangkaJinejiluMoney(yuyuecunkuanEntity.getYuyuecunkuanMoney());
            yinhangkaJinejiluEntity.setYinhangkaJinejiluContent("存款审核通过,存款"+yuyuecunkuanEntity.getYuyuecunkuanMoney()+"元");
            yinhangkaJinejiluEntity.setJiluTypes(6);
            yinhangkaJinejiluService.insert(yinhangkaJinejiluEntity);
//            yuyuecunkuan.setYuyuecunkuanTypes();
        }else if(yuyuecunkuan.getYuyuecunkuanYesnoTypes() == 3){//拒绝
//            yuyuecunkuan.setYuyuecunkuanTypes();
        }
        yuyuecunkuan.setYuyuecunkuanShenheTime(new Date());//审核时间
        yuyuecunkuanService.updateById(yuyuecunkuan);//审核
        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        yuyuecunkuanService.deleteBatchIds(Arrays.asList(ids));
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
            List<YuyuecunkuanEntity> yuyuecunkuanList = new ArrayList<>();//上传的东西
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
                            YuyuecunkuanEntity yuyuecunkuanEntity = new YuyuecunkuanEntity();
//                            yuyuecunkuanEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            yuyuecunkuanEntity.setYinhangkaId(Integer.valueOf(data.get(0)));   //银行卡 要改的
//                            yuyuecunkuanEntity.setShenherenyuanId(Integer.valueOf(data.get(0)));   //审核人员 要改的
//                            yuyuecunkuanEntity.setYuyuecunkuanUuidNumber(data.get(0));                    //预约编号 要改的
//                            yuyuecunkuanEntity.setYuyuecunkuanMoney(data.get(0));                    //存款金额 要改的
//                            yuyuecunkuanEntity.setYuyuecunkuanContent("");//详情和图片
//                            yuyuecunkuanEntity.setInsertTime(date);//时间
//                            yuyuecunkuanEntity.setYuyuecunkuanTime(sdf.parse(data.get(0)));          //预约日期 要改的
//                            yuyuecunkuanEntity.setYuyuecunkuanYesnoTypes(Integer.valueOf(data.get(0)));   //审核状态 要改的
//                            yuyuecunkuanEntity.setYuyuecunkuanYesnoText(data.get(0));                    //审核意见 要改的
//                            yuyuecunkuanEntity.setYuyuecunkuanShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            yuyuecunkuanEntity.setCreateTime(date);//时间
                            yuyuecunkuanList.add(yuyuecunkuanEntity);


                            //把要查询是否重复的字段放入map中
                                //预约编号
                                if(seachFields.containsKey("yuyuecunkuanUuidNumber")){
                                    List<String> yuyuecunkuanUuidNumber = seachFields.get("yuyuecunkuanUuidNumber");
                                    yuyuecunkuanUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yuyuecunkuanUuidNumber = new ArrayList<>();
                                    yuyuecunkuanUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("yuyuecunkuanUuidNumber",yuyuecunkuanUuidNumber);
                                }
                        }

                        //查询是否重复
                         //预约编号
                        List<YuyuecunkuanEntity> yuyuecunkuanEntities_yuyuecunkuanUuidNumber = yuyuecunkuanService.selectList(new EntityWrapper<YuyuecunkuanEntity>().in("yuyuecunkuan_uuid_number", seachFields.get("yuyuecunkuanUuidNumber")));
                        if(yuyuecunkuanEntities_yuyuecunkuanUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YuyuecunkuanEntity s:yuyuecunkuanEntities_yuyuecunkuanUuidNumber){
                                repeatFields.add(s.getYuyuecunkuanUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [预约编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yuyuecunkuanService.insertBatch(yuyuecunkuanList);
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
