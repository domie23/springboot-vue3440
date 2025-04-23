package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DaikuanEntity;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * 贷款 服务类
 */
public interface DaikuanService extends IService<DaikuanEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}