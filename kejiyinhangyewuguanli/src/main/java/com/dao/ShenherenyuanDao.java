package com.dao;

import com.entity.ShenherenyuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ShenherenyuanView;

/**
 * 审核人员 Dao 接口
 *
 * @author 
 */
public interface ShenherenyuanDao extends BaseMapper<ShenherenyuanEntity> {

   List<ShenherenyuanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
