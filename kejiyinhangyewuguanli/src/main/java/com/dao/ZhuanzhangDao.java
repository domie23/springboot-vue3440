package com.dao;

import com.entity.ZhuanzhangEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhuanzhangView;

/**
 * 转账 Dao 接口
 *
 * @author 
 */
public interface ZhuanzhangDao extends BaseMapper<ZhuanzhangEntity> {

   List<ZhuanzhangView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
