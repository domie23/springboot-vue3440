package com.dao;

import com.entity.YuyuecunkuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YuyuecunkuanView;

/**
 * 预约存款 Dao 接口
 *
 * @author 
 */
public interface YuyuecunkuanDao extends BaseMapper<YuyuecunkuanEntity> {

   List<YuyuecunkuanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
