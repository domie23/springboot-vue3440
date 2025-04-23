
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
 * 银行卡金额记录
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yinhangkaJinejilu")
public class YinhangkaJinejiluController {
    private static final Logger logger = LoggerFactory.getLogger(YinhangkaJinejiluController.class);

    @Autowired
    private YinhangkaJinejiluService yinhangkaJinejiluService;


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
        PageUtils page = yinhangkaJinejiluService.queryPage(params);

        //字典表数据转换
        List<YinhangkaJinejiluView> list =(List<YinhangkaJinejiluView>)page.getList();
        for(YinhangkaJinejiluView c:list){
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
        YinhangkaJinejiluEntity yinhangkaJinejilu = yinhangkaJinejiluService.selectById(id);
        if(yinhangkaJinejilu !=null){
            //entity转view
            YinhangkaJinejiluView view = new YinhangkaJinejiluView();
            BeanUtils.copyProperties( yinhangkaJinejilu , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(yinhangkaJinejilu.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                YinhangkaEntity yinhangka = yinhangkaService.selectById(yinhangkaJinejilu.getYinhangkaId());
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
    public R save(@RequestBody YinhangkaJinejiluEntity yinhangkaJinejilu, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yinhangkaJinejilu:{}",this.getClass().getName(),yinhangkaJinejilu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            yinhangkaJinejilu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<YinhangkaJinejiluEntity> queryWrapper = new EntityWrapper<YinhangkaJinejiluEntity>()
            .eq("yonghu_id", yinhangkaJinejilu.getYonghuId())
            .eq("yinhangka_id", yinhangkaJinejilu.getYinhangkaId())
            .eq("yinhangka_jinejilu_uuid_number", yinhangkaJinejilu.getYinhangkaJinejiluUuidNumber())
            .eq("jilu_types", yinhangkaJinejilu.getJiluTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YinhangkaJinejiluEntity yinhangkaJinejiluEntity = yinhangkaJinejiluService.selectOne(queryWrapper);
        if(yinhangkaJinejiluEntity==null){
            yinhangkaJinejilu.setInsertTime(new Date());
            yinhangkaJinejilu.setCreateTime(new Date());
            yinhangkaJinejiluService.insert(yinhangkaJinejilu);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YinhangkaJinejiluEntity yinhangkaJinejilu, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,yinhangkaJinejilu:{}",this.getClass().getName(),yinhangkaJinejilu.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            yinhangkaJinejilu.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<YinhangkaJinejiluEntity> queryWrapper = new EntityWrapper<YinhangkaJinejiluEntity>()
            .notIn("id",yinhangkaJinejilu.getId())
            .andNew()
            .eq("yonghu_id", yinhangkaJinejilu.getYonghuId())
            .eq("yinhangka_id", yinhangkaJinejilu.getYinhangkaId())
            .eq("yinhangka_jinejilu_uuid_number", yinhangkaJinejilu.getYinhangkaJinejiluUuidNumber())
            .eq("jilu_types", yinhangkaJinejilu.getJiluTypes())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YinhangkaJinejiluEntity yinhangkaJinejiluEntity = yinhangkaJinejiluService.selectOne(queryWrapper);
        if(yinhangkaJinejiluEntity==null){
            yinhangkaJinejiluService.updateById(yinhangkaJinejilu);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        yinhangkaJinejiluService.deleteBatchIds(Arrays.asList(ids));
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
            List<YinhangkaJinejiluEntity> yinhangkaJinejiluList = new ArrayList<>();//上传的东西
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
                            YinhangkaJinejiluEntity yinhangkaJinejiluEntity = new YinhangkaJinejiluEntity();
//                            yinhangkaJinejiluEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            yinhangkaJinejiluEntity.setYinhangkaId(Integer.valueOf(data.get(0)));   //银行卡 要改的
//                            yinhangkaJinejiluEntity.setYinhangkaJinejiluUuidNumber(data.get(0));                    //记录编号 要改的
//                            yinhangkaJinejiluEntity.setJiluTypes(Integer.valueOf(data.get(0)));   //记录类型 要改的
//                            yinhangkaJinejiluEntity.setYinhangkaJinejiluMoney(data.get(0));                    //记录金额 要改的
//                            yinhangkaJinejiluEntity.setYinhangkaJinejiluContent("");//详情和图片
//                            yinhangkaJinejiluEntity.setInsertTime(date);//时间
//                            yinhangkaJinejiluEntity.setCreateTime(date);//时间
                            yinhangkaJinejiluList.add(yinhangkaJinejiluEntity);


                            //把要查询是否重复的字段放入map中
                                //记录编号
                                if(seachFields.containsKey("yinhangkaJinejiluUuidNumber")){
                                    List<String> yinhangkaJinejiluUuidNumber = seachFields.get("yinhangkaJinejiluUuidNumber");
                                    yinhangkaJinejiluUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yinhangkaJinejiluUuidNumber = new ArrayList<>();
                                    yinhangkaJinejiluUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("yinhangkaJinejiluUuidNumber",yinhangkaJinejiluUuidNumber);
                                }
                        }

                        //查询是否重复
                         //记录编号
                        List<YinhangkaJinejiluEntity> yinhangkaJinejiluEntities_yinhangkaJinejiluUuidNumber = yinhangkaJinejiluService.selectList(new EntityWrapper<YinhangkaJinejiluEntity>().in("yinhangka_jinejilu_uuid_number", seachFields.get("yinhangkaJinejiluUuidNumber")));
                        if(yinhangkaJinejiluEntities_yinhangkaJinejiluUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YinhangkaJinejiluEntity s:yinhangkaJinejiluEntities_yinhangkaJinejiluUuidNumber){
                                repeatFields.add(s.getYinhangkaJinejiluUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [记录编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yinhangkaJinejiluService.insertBatch(yinhangkaJinejiluList);
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
