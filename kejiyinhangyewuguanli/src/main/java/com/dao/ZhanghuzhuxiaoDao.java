package com.dao;

import com.entity.ZhanghuzhuxiaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhanghuzhuxiaoView;

/**
 * 账户注销 Dao 接口
 *
 * @author 
 */
public interface ZhanghuzhuxiaoDao extends BaseMapper<ZhanghuzhuxiaoEntity> {

   List<ZhanghuzhuxiaoView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
