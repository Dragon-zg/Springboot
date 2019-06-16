package com.lnnk.jpa.service;

import com.lnnk.jpa.model.entity.l2cache.L2Cache;
import com.lnnk.jpa.service.base.CurdService;

/**
 * @author Lnnk
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
     * 保存
     *
     * @param l2Cache
     */
    void create(L2Cache l2Cache);
}
