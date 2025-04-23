package com.service.impl;

import com.utils.StringUtil;
import org.springframework.stereotype.Service;
import java.lang.reflect.Field;
import java.util.*;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import com.dao.YinhangkabubanDao;
import com.entity.YinhangkabubanEntity;
import com.service.YinhangkabubanService;
import com.entity.view.YinhangkabubanView;

/**
 * 银行卡补办 服务实现类
 */
@Service("yinhangkabubanService")
@Transactional
public class YinhangkabubanServiceImpl extends ServiceImpl<YinhangkabubanDao, YinhangkabubanEntity> implements YinhangkabubanService {

    @Override
    public PageUtils queryPage(Map<String,Object> params) {
        if(params != null && (params.get("limit") == null || params.get("page") == null)){
            params.put("page","1");
            params.put("limit","10");
        }
        Page<YinhangkabubanView> page =new Query<YinhangkabubanView>(params).getPage();
        page.setRecords(baseMapper.selectListView(page,params));
        return new PageUtils(page);
    }


}
