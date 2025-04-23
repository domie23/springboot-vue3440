
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
 * 银行卡
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/yinhangka")
public class YinhangkaController {
    private static final Logger logger = LoggerFactory.getLogger(YinhangkaController.class);

    @Autowired
    private YinhangkaService yinhangkaService;


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
        PageUtils page = yinhangkaService.queryPage(params);

        //字典表数据转换
        List<YinhangkaView> list =(List<YinhangkaView>)page.getList();
        for(YinhangkaView c:list){
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
        YinhangkaEntity yinhangka = yinhangkaService.selectById(id);
        if(yinhangka !=null){
            //entity转view
            YinhangkaView view = new YinhangkaView();
            BeanUtils.copyProperties( yinhangka , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(yinhangka.getYonghuId());
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
    public R save(@RequestBody YinhangkaEntity yinhangka, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,yinhangka:{}",this.getClass().getName(),yinhangka.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role)){
            yinhangka.setYinghangkaMoney(0.0);
            yinhangka.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        }

        Wrapper<YinhangkaEntity> queryWrapper = new EntityWrapper<YinhangkaEntity>()
            .eq("yinhangka_uuid_number", yinhangka.getYinhangkaUuidNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YinhangkaEntity yinhangkaEntity = yinhangkaService.selectOne(queryWrapper);
        if(yinhangkaEntity==null){
            yinhangka.setInsertTime(new Date());
            yinhangka.setCreateTime(new Date());
            yinhangkaService.insert(yinhangka);
            return R.ok();
        }else {
            return R.error(511,"该银行卡号已被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody YinhangkaEntity yinhangka, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,yinhangka:{}",this.getClass().getName(),yinhangka.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            yinhangka.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<YinhangkaEntity> queryWrapper = new EntityWrapper<YinhangkaEntity>()
            .notIn("id",yinhangka.getId())
            .andNew()
            .eq("yinhangka_uuid_number", yinhangka.getYinhangkaUuidNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        YinhangkaEntity yinhangkaEntity = yinhangkaService.selectOne(queryWrapper);
        if(yinhangkaEntity==null){
            yinhangkaService.updateById(yinhangka);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"该银行卡号已被使用");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        yinhangkaService.deleteBatchIds(Arrays.asList(ids));
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
            List<YinhangkaEntity> yinhangkaList = new ArrayList<>();//上传的东西
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
                            YinhangkaEntity yinhangkaEntity = new YinhangkaEntity();
//                            yinhangkaEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            yinhangkaEntity.setYinhangkaUuidNumber(data.get(0));                    //银行卡卡号 要改的
//                            yinhangkaEntity.setKaihudiTypes(Integer.valueOf(data.get(0)));   //开户地 要改的
//                            yinhangkaEntity.setYinghangkaMoney(data.get(0));                    //卡余额 要改的
//                            yinhangkaEntity.setYinhangkaPhone(data.get(0));                    //绑定手机号 要改的
//                            yinhangkaEntity.setYinhangkaEmail(data.get(0));                    //绑定邮箱 要改的
//                            yinhangkaEntity.setKaihuTime(sdf.parse(data.get(0)));          //开户时间 要改的
//                            yinhangkaEntity.setYinhangkaContent("");//详情和图片
//                            yinhangkaEntity.setInsertTime(date);//时间
//                            yinhangkaEntity.setCreateTime(date);//时间
                            yinhangkaList.add(yinhangkaEntity);


                            //把要查询是否重复的字段放入map中
                                //银行卡卡号
                                if(seachFields.containsKey("yinhangkaUuidNumber")){
                                    List<String> yinhangkaUuidNumber = seachFields.get("yinhangkaUuidNumber");
                                    yinhangkaUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> yinhangkaUuidNumber = new ArrayList<>();
                                    yinhangkaUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("yinhangkaUuidNumber",yinhangkaUuidNumber);
                                }
                                //绑定手机号
                                if(seachFields.containsKey("yinhangkaPhone")){
                                    List<String> yinhangkaPhone = seachFields.get("yinhangkaPhone");
                                    yinhangkaPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> yinhangkaPhone = new ArrayList<>();
                                    yinhangkaPhone.add(data.get(0));//要改的
                                    seachFields.put("yinhangkaPhone",yinhangkaPhone);
                                }
                        }

                        //查询是否重复
                         //银行卡卡号
                        List<YinhangkaEntity> yinhangkaEntities_yinhangkaUuidNumber = yinhangkaService.selectList(new EntityWrapper<YinhangkaEntity>().in("yinhangka_uuid_number", seachFields.get("yinhangkaUuidNumber")));
                        if(yinhangkaEntities_yinhangkaUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YinhangkaEntity s:yinhangkaEntities_yinhangkaUuidNumber){
                                repeatFields.add(s.getYinhangkaUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [银行卡卡号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //绑定手机号
                        List<YinhangkaEntity> yinhangkaEntities_yinhangkaPhone = yinhangkaService.selectList(new EntityWrapper<YinhangkaEntity>().in("yinhangka_phone", seachFields.get("yinhangkaPhone")));
                        if(yinhangkaEntities_yinhangkaPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(YinhangkaEntity s:yinhangkaEntities_yinhangkaPhone){
                                repeatFields.add(s.getYinhangkaPhone());
                            }
                            return R.error(511,"数据库的该表中的 [绑定手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        yinhangkaService.insertBatch(yinhangkaList);
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
