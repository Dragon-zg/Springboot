package com.jpa.service;

import com.jpa.entity.L2Cache.L2Cache;
import com.jpa.service.base.CurdService;

/**
 * @author wangqiang
 * @date 2019-05-18 14:53
 **/
public interface L2CacheService extends CurdService<L2Cache, String> {
    /**
     * 初始化数据
     *
     * @return void
     */
    void init();

    /**
     * 详情
     *
     * @param id
     * @return com.jpa.entity.onetoone.Person
     */
    L2Cache detail(String id);
}
