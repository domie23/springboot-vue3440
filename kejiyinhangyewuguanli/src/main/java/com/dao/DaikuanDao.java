package com.dao;

import com.entity.DaikuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.DaikuanView;

/**
 * 贷款 Dao 接口
 *
 * @author 
 */
public interface DaikuanDao extends BaseMapper<DaikuanEntity> {

   List<DaikuanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
