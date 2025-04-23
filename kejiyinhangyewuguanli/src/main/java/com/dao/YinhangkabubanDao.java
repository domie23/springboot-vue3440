package com.dao;

import com.entity.YinhangkabubanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.YinhangkabubanView;

/**
 * 银行卡补办 Dao 接口
 *
 * @author 
 */
public interface YinhangkabubanDao extends BaseMapper<YinhangkabubanEntity> {

   List<YinhangkabubanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
