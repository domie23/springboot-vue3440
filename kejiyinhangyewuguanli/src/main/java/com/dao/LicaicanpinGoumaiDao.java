package com.dao;

import com.entity.LicaicanpinGoumaiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.LicaicanpinGoumaiView;

/**
 * 理财产品购买 Dao 接口
 *
 * @author 
 */
public interface LicaicanpinGoumaiDao extends BaseMapper<LicaicanpinGoumaiEntity> {

   List<LicaicanpinGoumaiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
