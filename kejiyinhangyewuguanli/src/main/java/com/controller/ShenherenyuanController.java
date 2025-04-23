
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
 * 审核人员
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/shenherenyuan")
public class ShenherenyuanController {
    private static final Logger logger = LoggerFactory.getLogger(ShenherenyuanController.class);

    @Autowired
    private ShenherenyuanService shenherenyuanService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;
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
        PageUtils page = shenherenyuanService.queryPage(params);

        //字典表数据转换
        List<ShenherenyuanView> list =(List<ShenherenyuanView>)page.getList();
        for(ShenherenyuanView c:list){
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
        ShenherenyuanEntity shenherenyuan = shenherenyuanService.selectById(id);
        if(shenherenyuan !=null){
            //entity转view
            ShenherenyuanView view = new ShenherenyuanView();
            BeanUtils.copyProperties( shenherenyuan , view );//把实体数据重构到view中

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
    public R save(@RequestBody ShenherenyuanEntity shenherenyuan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,shenherenyuan:{}",this.getClass().getName(),shenherenyuan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<ShenherenyuanEntity> queryWrapper = new EntityWrapper<ShenherenyuanEntity>()
            .eq("username", shenherenyuan.getUsername())
            .or()
            .eq("shenherenyuan_phone", shenherenyuan.getShenherenyuanPhone())
            .or()
            .eq("shenherenyuan_id_number", shenherenyuan.getShenherenyuanIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShenherenyuanEntity shenherenyuanEntity = shenherenyuanService.selectOne(queryWrapper);
        if(shenherenyuanEntity==null){
            shenherenyuan.setCreateTime(new Date());
            shenherenyuan.setPassword("123456");
            shenherenyuanService.insert(shenherenyuan);
            return R.ok();
        }else {
            return R.error(511,"账户或者审核人员手机号或者审核人员身份证号已经被使用");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody ShenherenyuanEntity shenherenyuan, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,shenherenyuan:{}",this.getClass().getName(),shenherenyuan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<ShenherenyuanEntity> queryWrapper = new EntityWrapper<ShenherenyuanEntity>()
            .notIn("id",shenherenyuan.getId())
            .andNew()
            .eq("username", shenherenyuan.getUsername())
            .or()
            .eq("shenherenyuan_phone", shenherenyuan.getShenherenyuanPhone())
            .or()
            .eq("shenherenyuan_id_number", shenherenyuan.getShenherenyuanIdNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        ShenherenyuanEntity shenherenyuanEntity = shenherenyuanService.selectOne(queryWrapper);
        if("".equals(shenherenyuan.getShenherenyuanPhoto()) || "null".equals(shenherenyuan.getShenherenyuanPhoto())){
                shenherenyuan.setShenherenyuanPhoto(null);
        }
        if(shenherenyuanEntity==null){
            shenherenyuanService.updateById(shenherenyuan);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"账户或者审核人员手机号或者审核人员身份证号已经被使用");
        }
    }



    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        shenherenyuanService.deleteBatchIds(Arrays.asList(ids));
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
            List<ShenherenyuanEntity> shenherenyuanList = new ArrayList<>();//上传的东西
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
                            ShenherenyuanEntity shenherenyuanEntity = new ShenherenyuanEntity();
//                            shenherenyuanEntity.setUsername(data.get(0));                    //账户 要改的
//                            //shenherenyuanEntity.setPassword("123456");//密码
//                            shenherenyuanEntity.setShenherenyuanUuidNumber(data.get(0));                    //审核人员工号 要改的
//                            shenherenyuanEntity.setShenherenyuanName(data.get(0));                    //审核人员姓名 要改的
//                            shenherenyuanEntity.setShenherenyuanPhone(data.get(0));                    //审核人员手机号 要改的
//                            shenherenyuanEntity.setShenherenyuanIdNumber(data.get(0));                    //审核人员身份证号 要改的
//                            shenherenyuanEntity.setShenherenyuanPhoto("");//详情和图片
//                            shenherenyuanEntity.setSexTypes(Integer.valueOf(data.get(0)));   //性别 要改的
//                            shenherenyuanEntity.setShenherenyuanEmail(data.get(0));                    //电子邮箱 要改的
//                            shenherenyuanEntity.setCreateTime(date);//时间
                            shenherenyuanList.add(shenherenyuanEntity);


                            //把要查询是否重复的字段放入map中
                                //账户
                                if(seachFields.containsKey("username")){
                                    List<String> username = seachFields.get("username");
                                    username.add(data.get(0));//要改的
                                }else{
                                    List<String> username = new ArrayList<>();
                                    username.add(data.get(0));//要改的
                                    seachFields.put("username",username);
                                }
                                //审核人员工号
                                if(seachFields.containsKey("shenherenyuanUuidNumber")){
                                    List<String> shenherenyuanUuidNumber = seachFields.get("shenherenyuanUuidNumber");
                                    shenherenyuanUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> shenherenyuanUuidNumber = new ArrayList<>();
                                    shenherenyuanUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("shenherenyuanUuidNumber",shenherenyuanUuidNumber);
                                }
                                //审核人员手机号
                                if(seachFields.containsKey("shenherenyuanPhone")){
                                    List<String> shenherenyuanPhone = seachFields.get("shenherenyuanPhone");
                                    shenherenyuanPhone.add(data.get(0));//要改的
                                }else{
                                    List<String> shenherenyuanPhone = new ArrayList<>();
                                    shenherenyuanPhone.add(data.get(0));//要改的
                                    seachFields.put("shenherenyuanPhone",shenherenyuanPhone);
                                }
                                //审核人员身份证号
                                if(seachFields.containsKey("shenherenyuanIdNumber")){
                                    List<String> shenherenyuanIdNumber = seachFields.get("shenherenyuanIdNumber");
                                    shenherenyuanIdNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> shenherenyuanIdNumber = new ArrayList<>();
                                    shenherenyuanIdNumber.add(data.get(0));//要改的
                                    seachFields.put("shenherenyuanIdNumber",shenherenyuanIdNumber);
                                }
                        }

                        //查询是否重复
                         //账户
                        List<ShenherenyuanEntity> shenherenyuanEntities_username = shenherenyuanService.selectList(new EntityWrapper<ShenherenyuanEntity>().in("username", seachFields.get("username")));
                        if(shenherenyuanEntities_username.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShenherenyuanEntity s:shenherenyuanEntities_username){
                                repeatFields.add(s.getUsername());
                            }
                            return R.error(511,"数据库的该表中的 [账户] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //审核人员工号
                        List<ShenherenyuanEntity> shenherenyuanEntities_shenherenyuanUuidNumber = shenherenyuanService.selectList(new EntityWrapper<ShenherenyuanEntity>().in("shenherenyuan_uuid_number", seachFields.get("shenherenyuanUuidNumber")));
                        if(shenherenyuanEntities_shenherenyuanUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShenherenyuanEntity s:shenherenyuanEntities_shenherenyuanUuidNumber){
                                repeatFields.add(s.getShenherenyuanUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [审核人员工号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //审核人员手机号
                        List<ShenherenyuanEntity> shenherenyuanEntities_shenherenyuanPhone = shenherenyuanService.selectList(new EntityWrapper<ShenherenyuanEntity>().in("shenherenyuan_phone", seachFields.get("shenherenyuanPhone")));
                        if(shenherenyuanEntities_shenherenyuanPhone.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShenherenyuanEntity s:shenherenyuanEntities_shenherenyuanPhone){
                                repeatFields.add(s.getShenherenyuanPhone());
                            }
                            return R.error(511,"数据库的该表中的 [审核人员手机号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                         //审核人员身份证号
                        List<ShenherenyuanEntity> shenherenyuanEntities_shenherenyuanIdNumber = shenherenyuanService.selectList(new EntityWrapper<ShenherenyuanEntity>().in("shenherenyuan_id_number", seachFields.get("shenherenyuanIdNumber")));
                        if(shenherenyuanEntities_shenherenyuanIdNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(ShenherenyuanEntity s:shenherenyuanEntities_shenherenyuanIdNumber){
                                repeatFields.add(s.getShenherenyuanIdNumber());
                            }
                            return R.error(511,"数据库的该表中的 [审核人员身份证号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        shenherenyuanService.insertBatch(shenherenyuanList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }


    /**
    * 登录
    */
    @IgnoreAuth
    @RequestMapping(value = "/login")
    public R login(String username, String password, String captcha, HttpServletRequest request) {
        ShenherenyuanEntity shenherenyuan = shenherenyuanService.selectOne(new EntityWrapper<ShenherenyuanEntity>().eq("username", username));
        if(shenherenyuan==null || !shenherenyuan.getPassword().equals(password))
            return R.error("账号或密码不正确");
        //  // 获取监听器中的字典表
        // ServletContext servletContext = ContextLoader.getCurrentWebApplicationContext().getServletContext();
        // Map<String, Map<Integer, String>> dictionaryMap= (Map<String, Map<Integer, String>>) servletContext.getAttribute("dictionaryMap");
        // Map<Integer, String> role_types = dictionaryMap.get("role_types");
        // role_types.get(.getRoleTypes());
        String token = tokenService.generateToken(shenherenyuan.getId(),username, "shenherenyuan", "审核人员");
        R r = R.ok();
        r.put("token", token);
        r.put("role","审核人员");
        r.put("username",shenherenyuan.getShenherenyuanName());
        r.put("tableName","shenherenyuan");
        r.put("userId",shenherenyuan.getId());
        return r;
    }

    /**
    * 注册
    */
    @IgnoreAuth
    @PostMapping(value = "/register")
    public R register(@RequestBody ShenherenyuanEntity shenherenyuan){
//    	ValidatorUtils.validateEntity(user);
        Wrapper<ShenherenyuanEntity> queryWrapper = new EntityWrapper<ShenherenyuanEntity>()
            .eq("username", shenherenyuan.getUsername())
            .or()
            .eq("shenherenyuan_phone", shenherenyuan.getShenherenyuanPhone())
            .or()
            .eq("shenherenyuan_id_number", shenherenyuan.getShenherenyuanIdNumber())
            ;
        ShenherenyuanEntity shenherenyuanEntity = shenherenyuanService.selectOne(queryWrapper);
        if(shenherenyuanEntity != null)
            return R.error("账户或者审核人员手机号或者审核人员身份证号已经被使用");
        shenherenyuan.setCreateTime(new Date());
        shenherenyuanService.insert(shenherenyuan);
        return R.ok();
    }

    /**
     * 重置密码
     */
    @GetMapping(value = "/resetPassword")
    public R resetPassword(Integer  id){
        ShenherenyuanEntity shenherenyuan = new ShenherenyuanEntity();
        shenherenyuan.setPassword("123456");
        shenherenyuan.setId(id);
        shenherenyuanService.updateById(shenherenyuan);
        return R.ok();
    }


    /**
     * 忘记密码
     */
    @IgnoreAuth
    @RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request) {
        ShenherenyuanEntity shenherenyuan = shenherenyuanService.selectOne(new EntityWrapper<ShenherenyuanEntity>().eq("username", username));
        if(shenherenyuan!=null){
            shenherenyuan.setPassword("123456");
            boolean b = shenherenyuanService.updateById(shenherenyuan);
            if(!b){
               return R.error();
            }
        }else{
           return R.error("账号不存在");
        }
        return R.ok();
    }


    /**
    * 获取用户的session用户信息
    */
    @RequestMapping("/session")
    public R getCurrShenherenyuan(HttpServletRequest request){
        Integer id = (Integer)request.getSession().getAttribute("userId");
        ShenherenyuanEntity shenherenyuan = shenherenyuanService.selectById(id);
        if(shenherenyuan !=null){
            //entity转view
            ShenherenyuanView view = new ShenherenyuanView();
            BeanUtils.copyProperties( shenherenyuan , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }
    }


    /**
    * 退出
    */
    @GetMapping(value = "logout")
    public R logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return R.ok("退出成功");
    }





}
