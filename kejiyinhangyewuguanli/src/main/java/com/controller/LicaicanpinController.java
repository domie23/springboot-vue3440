
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
 * 理财产品
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/licaicanpin")
public class LicaicanpinController {
    private static final Logger logger = LoggerFactory.getLogger(LicaicanpinController.class);

    @Autowired
    private LicaicanpinService licaicanpinService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private YewurenyuanService yewurenyuanService;

    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private ShenherenyuanService shenherenyuanService;


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
        PageUtils page = licaicanpinService.queryPage(params);

        //字典表数据转换
        List<LicaicanpinView> list =(List<LicaicanpinView>)page.getList();
        for(LicaicanpinView c:list){
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
        LicaicanpinEntity licaicanpin = licaicanpinService.selectById(id);
        if(licaicanpin !=null){
            //entity转view
            LicaicanpinView view = new LicaicanpinView();
            BeanUtils.copyProperties( licaicanpin , view );//把实体数据重构到view中

                //级联表
                YewurenyuanEntity yewurenyuan = yewurenyuanService.selectById(licaicanpin.getYewurenyuanId());
                if(yewurenyuan != null){
                    BeanUtils.copyProperties( yewurenyuan , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYewurenyuanId(yewurenyuan.getId());
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
    public R save(@RequestBody LicaicanpinEntity licaicanpin, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,licaicanpin:{}",this.getClass().getName(),licaicanpin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("业务人员".equals(role))
            licaicanpin.setYewurenyuanId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<LicaicanpinEntity> queryWrapper = new EntityWrapper<LicaicanpinEntity>()
            .eq("yewurenyuan_id", licaicanpin.getYewurenyuanId())
            .eq("licaicanpin_uuid_number", licaicanpin.getLicaicanpinUuidNumber())
            .eq("licaicanpin_name", licaicanpin.getLicaicanpinName())
            .eq("licaicanpin_types", licaicanpin.getLicaicanpinTypes())
            .eq("licaicanpin_fenshu", licaicanpin.getLicaicanpinFenshu())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LicaicanpinEntity licaicanpinEntity = licaicanpinService.selectOne(queryWrapper);
        if(licaicanpinEntity==null){
            licaicanpin.setInsertTime(new Date());
            licaicanpin.setCreateTime(new Date());
            licaicanpinService.insert(licaicanpin);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody LicaicanpinEntity licaicanpin, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,licaicanpin:{}",this.getClass().getName(),licaicanpin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("业务人员".equals(role))
//            licaicanpin.setYewurenyuanId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<LicaicanpinEntity> queryWrapper = new EntityWrapper<LicaicanpinEntity>()
            .notIn("id",licaicanpin.getId())
            .andNew()
            .eq("yewurenyuan_id", licaicanpin.getYewurenyuanId())
            .eq("licaicanpin_uuid_number", licaicanpin.getLicaicanpinUuidNumber())
            .eq("licaicanpin_name", licaicanpin.getLicaicanpinName())
            .eq("licaicanpin_types", licaicanpin.getLicaicanpinTypes())
            .eq("licaicanpin_fenshu", licaicanpin.getLicaicanpinFenshu())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LicaicanpinEntity licaicanpinEntity = licaicanpinService.selectOne(queryWrapper);
        if(licaicanpinEntity==null){
            licaicanpinService.updateById(licaicanpin);//根据id更新
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
        licaicanpinService.deleteBatchIds(Arrays.asList(ids));
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
            List<LicaicanpinEntity> licaicanpinList = new ArrayList<>();//上传的东西
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
                            LicaicanpinEntity licaicanpinEntity = new LicaicanpinEntity();
//                            licaicanpinEntity.setYewurenyuanId(Integer.valueOf(data.get(0)));   //业务人员 要改的
//                            licaicanpinEntity.setLicaicanpinUuidNumber(data.get(0));                    //理财产品编号 要改的
//                            licaicanpinEntity.setLicaicanpinName(data.get(0));                    //理财产品名称 要改的
//                            licaicanpinEntity.setLicaicanpinTypes(Integer.valueOf(data.get(0)));   //理财产品类型 要改的
//                            licaicanpinEntity.setLicaicanpinJine(data.get(0));                    //每份/元 要改的
//                            licaicanpinEntity.setLicaicanpinFenshu(Integer.valueOf(data.get(0)));   //剩余份数 要改的
//                            licaicanpinEntity.setLicaicanpinContent("");//详情和图片
//                            licaicanpinEntity.setInsertTime(date);//时间
//                            licaicanpinEntity.setCreateTime(date);//时间
                            licaicanpinList.add(licaicanpinEntity);


                            //把要查询是否重复的字段放入map中
                                //理财产品编号
                                if(seachFields.containsKey("licaicanpinUuidNumber")){
                                    List<String> licaicanpinUuidNumber = seachFields.get("licaicanpinUuidNumber");
                                    licaicanpinUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> licaicanpinUuidNumber = new ArrayList<>();
                                    licaicanpinUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("licaicanpinUuidNumber",licaicanpinUuidNumber);
                                }
                        }

                        //查询是否重复
                         //理财产品编号
                        List<LicaicanpinEntity> licaicanpinEntities_licaicanpinUuidNumber = licaicanpinService.selectList(new EntityWrapper<LicaicanpinEntity>().in("licaicanpin_uuid_number", seachFields.get("licaicanpinUuidNumber")));
                        if(licaicanpinEntities_licaicanpinUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(LicaicanpinEntity s:licaicanpinEntities_licaicanpinUuidNumber){
                                repeatFields.add(s.getLicaicanpinUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [理财产品编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        licaicanpinService.insertBatch(licaicanpinList);
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
