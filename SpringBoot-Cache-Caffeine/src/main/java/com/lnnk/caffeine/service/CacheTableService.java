package com.lnnk.caffeine.service;

import com.lnnk.caffeine.entity.CacheTable;

/**
 * CacheTableService
 *
 * @author wangqiang
 * @date 2019/6/25 15:59
 **/
public interface CacheTableService {

    /**
     * 保存并添加缓存
     *
     * @param cacheTable cacheTable
     * @return com.lnnk.caffeine.entity.CacheTable
     */
    CacheTable save(CacheTable cacheTable);

    /**
     * 删除并删除缓存
     *
     * @param id ID
     * @return void
     */
    void remove(Long id);

    /**
     * 查询并缓存
     *
     * @param id ID
     * @return com.lnnk.caffeine.entity.CacheTable
     */
    CacheTable findOne(Long id);

    /**
     * 查询并缓存(id<3, content.length > 10)
     *
     * @param id ID
     * @return com.lnnk.caffeine.entity.CacheTable
     */
    CacheTable findOneByConditionAndUnless(Long id);

    /**
     * 删除所有缓存
     */
    void removeCache();
}
