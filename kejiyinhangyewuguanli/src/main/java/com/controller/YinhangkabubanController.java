
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
 * 银行卡补办
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yinhangkabuban")
public class YinhangkabubanController {
    private static final Logger logger = LoggerFactory.getLogger(YinhangkabubanController.class);

    @Autowired
    private YinhangkabubanService yinhangkabubanService;


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
        PageUtils page = yinhangkabubanService.queryPage(params);

        //字典表数据转换
        List<YinhangkabubanView> list =(List<YinhangkabubanView>)page.getList();
        for(YinhangkabubanView c:list){
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
        YinhangkabubanEntity yinhangkabuban = yinhangkabubanService.selectById(id);
        if(yinhangkabuban !=null){
            //entity转view
            YinhangkabubanView view = new YinhangkabubanView();
            BeanUtils.copyProperties( yinhangkabuban , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(yinhangkabuban.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                YinhangkaEntity yinhangka = yinhangkaService.selectById(yinhangkabuban.getYinhangkaId());
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
    public R save(@RequestBody YinhangkabubanEntity yinhangkabuban, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yinhangkabuban:{}",this.getClass().getName(),yinhangkabuban.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            yinhangkabuban.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<YinhangkabubanEntity> queryWrapper = new EntityWrapper<YinhangkabubanEntity>()
            .eq("yonghu_id", yinhangkabuban.getYonghuId())
            .eq("yinhangka_id", yinhangkabuban.getYinhangkaId())
            .eq("yinhangkabuban_uuid_number", yinhangkabuban.getYinhangkabubanUuidNumber())
            .eq("yinhangkabuban_yesno_types", yinhangkabuban.getYinhangkabubanYesnoTypes())
            .eq("yinhangkabuban_yesno_text", yinhangkabuban.getYinhangkabubanYesnoText())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YinhangkabubanEntity yinhangkabubanEntity = yinhangkabubanService.selectOne(queryWrapper);
        if(yinhangkabubanEntity==null){
            yinhangkabuban.setInsertTime(new Date());
            yinhangkabuban.setYinhangkabubanYesnoTypes(1);
            yinhangkabuban.setCreateTime(new Date());
            yinhangkabubanService.insert(yinhangkabuban);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YinhangkabubanEntity yinhangkabuban, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,yinhangkabuban:{}",this.getClass().getName(),yinhangkabuban.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            yinhangkabuban.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<YinhangkabubanEntity> queryWrapper = new EntityWrapper<YinhangkabubanEntity>()
            .notIn("id",yinhangkabuban.getId())
            .andNew()
            .eq("yonghu_id", yinhangkabuban.getYonghuId())
            .eq("yinhangka_id", yinhangkabuban.getYinhangkaId())
            .eq("yinhangkabuban_uuid_number", yinhangkabuban.getYinhangkabubanUuidNumber())
            .eq("insert_time", yinhangkabuban.getInsertTime())
            .eq("yinhangkabuban_yesno_types", yinhangkabuban.getYinhangkabubanYesnoTypes())
            .eq("yinhangkabuban_yesno_text", yinhangkabuban.getYinhangkabubanYesnoText())
            .eq("yinhangkabuban_shenhe_time", yinhangkabuban.getYinhangkabubanShenheTime())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YinhangkabubanEntity yinhangkabubanEntity = yinhangkabubanService.selectOne(queryWrapper);
        if(yinhangkabubanEntity==null){
            yinhangkabubanService.updateById(yinhangkabuban);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody YinhangkabubanEntity yinhangkabuban, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,yinhangkabuban:{}",this.getClass().getName(),yinhangkabuban.toString());

//        if(yinhangkabuban.getYinhangkabubanYesnoTypes() == 2){//通过
//            yinhangkabuban.setYinhangkabubanTypes();
//        }else if(yinhangkabuban.getYinhangkabubanYesnoTypes() == 3){//拒绝
//            yinhangkabuban.setYinhangkabubanTypes();
//        }
        yinhangkabuban.setYinhangkabubanShenheTime(new Date());//审核时间
        yinhangkabubanService.updateById(yinhangkabuban);//审核
        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        yinhangkabubanService.deleteBatchIds(Arrays.asList(ids));
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
            List<YinhangkabubanEntity> yinhangkabubanList = new ArrayList<>();//上传的东西
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
                            YinhangkabubanEntity yinhangkabubanEntity = new YinhangkabubanEntity();
//                            yinhangkabubanEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            yinhangkabubanEntity.setYinhangkaId(Integer.valueOf(data.get(0)));   //银行卡 要改的
//                            yinhangkabubanEntity.setYinhangkabubanUuidNumber(data.get(0));                    //补办编号 要改的
//                            yinhangkabubanEntity.setInsertTime(date);//时间
//                            yinhangkabubanEntity.setYinhangkabubanContent("");//详情和图片
//                            yinhangkabubanEntity.setYinhangkabubanYesnoTypes(Integer.valueOf(data.get(0)));   //审核状态 要改的
//                            yinhangkabubanEntity.setYinhangkabubanYesnoText(data.get(0));                    //审核意见 要改的
//                            yinhangkabubanEntity.setYinhangkabubanShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            yinhangkabubanEntity.setCreateTime(date);//时间
                            yinhangkabubanList.add(yinhangkabubanEntity);


                            //把要查询是否重复的字段放入map中
                                //补办编号
                                if(seachFields.containsKey("yinhangkabubanUuidNumber")){
                                    List<String> yinhangkabubanUuidNumber = seachFields.get("yinhangkabubanUuidNumber");
                                    yinhangkabubanUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yinhangkabubanUuidNumber = new ArrayList<>();
                                    yinhangkabubanUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("yinhangkabubanUuidNumber",yinhangkabubanUuidNumber);
                                }
                        }

                        //查询是否重复
                         //补办编号
                        List<YinhangkabubanEntity> yinhangkabubanEntities_yinhangkabubanUuidNumber = yinhangkabubanService.selectList(new EntityWrapper<YinhangkabubanEntity>().in("yinhangkabuban_uuid_number", seachFields.get("yinhangkabubanUuidNumber")));
                        if(yinhangkabubanEntities_yinhangkabubanUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YinhangkabubanEntity s:yinhangkabubanEntities_yinhangkabubanUuidNumber){
                                repeatFields.add(s.getYinhangkabubanUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [补办编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yinhangkabubanService.insertBatch(yinhangkabubanList);
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
