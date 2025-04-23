
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
 * 账户注销
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/zhanghuzhuxiao")
public class ZhanghuzhuxiaoController {
    private static final Logger logger = LoggerFactory.getLogger(ZhanghuzhuxiaoController.class);

    @Autowired
    private ZhanghuzhuxiaoService zhanghuzhuxiaoService;


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
        PageUtils page = zhanghuzhuxiaoService.queryPage(params);

        //字典表数据转换
        List<ZhanghuzhuxiaoView> list =(List<ZhanghuzhuxiaoView>)page.getList();
        for(ZhanghuzhuxiaoView c:list){
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
        ZhanghuzhuxiaoEntity zhanghuzhuxiao = zhanghuzhuxiaoService.selectById(id);
        if(zhanghuzhuxiao !=null){
            //entity转view
            ZhanghuzhuxiaoView view = new ZhanghuzhuxiaoView();
            BeanUtils.copyProperties( zhanghuzhuxiao , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(zhanghuzhuxiao.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
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
    public R save(@RequestBody ZhanghuzhuxiaoEntity zhanghuzhuxiao, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,zhanghuzhuxiao:{}",this.getClass().getName(),zhanghuzhuxiao.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            zhanghuzhuxiao.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<ZhanghuzhuxiaoEntity> queryWrapper = new EntityWrapper<ZhanghuzhuxiaoEntity>()
            .eq("yonghu_id", zhanghuzhuxiao.getYonghuId())
            .eq("zhanghuzhuxiao_uuid_number", zhanghuzhuxiao.getZhanghuzhuxiaoUuidNumber())
            .eq("zhanghuzhuxiao_yesno_types", zhanghuzhuxiao.getZhanghuzhuxiaoYesnoTypes())
            .eq("zhanghuzhuxiao_yesno_text", zhanghuzhuxiao.getZhanghuzhuxiaoYesnoText())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhanghuzhuxiaoEntity zhanghuzhuxiaoEntity = zhanghuzhuxiaoService.selectOne(queryWrapper);
        if(zhanghuzhuxiaoEntity==null){
            zhanghuzhuxiao.setInsertTime(new Date());
            zhanghuzhuxiao.setZhanghuzhuxiaoYesnoTypes(1);
            zhanghuzhuxiao.setCreateTime(new Date());
            zhanghuzhuxiaoService.insert(zhanghuzhuxiao);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ZhanghuzhuxiaoEntity zhanghuzhuxiao, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,zhanghuzhuxiao:{}",this.getClass().getName(),zhanghuzhuxiao.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            zhanghuzhuxiao.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<ZhanghuzhuxiaoEntity> queryWrapper = new EntityWrapper<ZhanghuzhuxiaoEntity>()
            .notIn("id",zhanghuzhuxiao.getId())
            .andNew()
            .eq("yonghu_id", zhanghuzhuxiao.getYonghuId())
            .eq("zhanghuzhuxiao_uuid_number", zhanghuzhuxiao.getZhanghuzhuxiaoUuidNumber())
            .eq("insert_time", zhanghuzhuxiao.getInsertTime())
            .eq("zhanghuzhuxiao_yesno_types", zhanghuzhuxiao.getZhanghuzhuxiaoYesnoTypes())
            .eq("zhanghuzhuxiao_yesno_text", zhanghuzhuxiao.getZhanghuzhuxiaoYesnoText())
            .eq("zhanghuzhuxiao_shenhe_time", zhanghuzhuxiao.getZhanghuzhuxiaoShenheTime())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ZhanghuzhuxiaoEntity zhanghuzhuxiaoEntity = zhanghuzhuxiaoService.selectOne(queryWrapper);
        if(zhanghuzhuxiaoEntity==null){
            zhanghuzhuxiaoService.updateById(zhanghuzhuxiao);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody ZhanghuzhuxiaoEntity zhanghuzhuxiao, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,zhanghuzhuxiao:{}",this.getClass().getName(),zhanghuzhuxiao.toString());

        if(zhanghuzhuxiao.getZhanghuzhuxiaoYesnoTypes() == 2){//通过
            ZhanghuzhuxiaoEntity zhanghuzhuxiaoEntity = zhanghuzhuxiaoService.selectById(zhanghuzhuxiao.getId());
            if(zhanghuzhuxiaoEntity == null)
                return R.error("查不到账户注销记录");
            YonghuEntity yonghuEntity = yonghuService.selectById(zhanghuzhuxiaoEntity.getYonghuId());
            if(yonghuEntity == null)
                R.error("查不到用户");
            yonghuEntity.setZhuxiaoTypes(2);
            yonghuService.updateById(yonghuEntity);
//            zhanghuzhuxiao.setZhanghuzhuxiaoTypes();
        }else if(zhanghuzhuxiao.getZhanghuzhuxiaoYesnoTypes() == 3){//拒绝
//            zhanghuzhuxiao.setZhanghuzhuxiaoTypes();
        }
        zhanghuzhuxiao.setZhanghuzhuxiaoShenheTime(new Date());//审核时间
        zhanghuzhuxiaoService.updateById(zhanghuzhuxiao);//审核
        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        zhanghuzhuxiaoService.deleteBatchIds(Arrays.asList(ids));
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
            List<ZhanghuzhuxiaoEntity> zhanghuzhuxiaoList = new ArrayList<>();//上传的东西
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
                            ZhanghuzhuxiaoEntity zhanghuzhuxiaoEntity = new ZhanghuzhuxiaoEntity();
//                            zhanghuzhuxiaoEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            zhanghuzhuxiaoEntity.setZhanghuzhuxiaoUuidNumber(data.get(0));                    //注销编号 要改的
//                            zhanghuzhuxiaoEntity.setInsertTime(date);//时间
//                            zhanghuzhuxiaoEntity.setZhanghuzhuxiaoContent("");//详情和图片
//                            zhanghuzhuxiaoEntity.setZhanghuzhuxiaoYesnoTypes(Integer.valueOf(data.get(0)));   //审核状态 要改的
//                            zhanghuzhuxiaoEntity.setZhanghuzhuxiaoYesnoText(data.get(0));                    //审核意见 要改的
//                            zhanghuzhuxiaoEntity.setZhanghuzhuxiaoShenheTime(sdf.parse(data.get(0)));          //审核时间 要改的
//                            zhanghuzhuxiaoEntity.setCreateTime(date);//时间
                            zhanghuzhuxiaoList.add(zhanghuzhuxiaoEntity);


                            //把要查询是否重复的字段放入map中
                                //注销编号
                                if(seachFields.containsKey("zhanghuzhuxiaoUuidNumber")){
                                    List<String> zhanghuzhuxiaoUuidNumber = seachFields.get("zhanghuzhuxiaoUuidNumber");
                                    zhanghuzhuxiaoUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> zhanghuzhuxiaoUuidNumber = new ArrayList<>();
                                    zhanghuzhuxiaoUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("zhanghuzhuxiaoUuidNumber",zhanghuzhuxiaoUuidNumber);
                                }
                        }

                        //查询是否重复
                         //注销编号
                        List<ZhanghuzhuxiaoEntity> zhanghuzhuxiaoEntities_zhanghuzhuxiaoUuidNumber = zhanghuzhuxiaoService.selectList(new EntityWrapper<ZhanghuzhuxiaoEntity>().in("zhanghuzhuxiao_uuid_number", seachFields.get("zhanghuzhuxiaoUuidNumber")));
                        if(zhanghuzhuxiaoEntities_zhanghuzhuxiaoUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ZhanghuzhuxiaoEntity s:zhanghuzhuxiaoEntities_zhanghuzhuxiaoUuidNumber){
                                repeatFields.add(s.getZhanghuzhuxiaoUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [注销编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        zhanghuzhuxiaoService.insertBatch(zhanghuzhuxiaoList);
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
