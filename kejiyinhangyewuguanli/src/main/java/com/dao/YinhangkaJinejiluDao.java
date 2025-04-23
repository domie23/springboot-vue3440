package com.dao;

import com.entity.YinhangkaJinejiluEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YinhangkaJinejiluView;

/**
 * 银行卡金额记录 Dao 接口
 *
 * @author 
 */
public interface YinhangkaJinejiluDao extends BaseMapper<YinhangkaJinejiluEntity> {

   List<YinhangkaJinejiluView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
