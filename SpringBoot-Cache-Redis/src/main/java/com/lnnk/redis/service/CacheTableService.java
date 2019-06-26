package com.lnnk.redis.service;

import com.lnnk.redis.entity.CacheTable;

import java.util.List;

/**
 * CacheTableService
 *
 * @author wangqiang
 * @date 2019/6/25 15:59
 **/
public interface CacheTableService {

    /**
     * 初始化
     */
    void init();

    /**
     * 保存并添加缓存
     *
     * @param cacheTable cacheTable
     * @return com.lnnk.caffeine.entity.CacheTable
     */
    CacheTable saveAndCache(CacheTable cacheTable);

    /**
     * 删除并删除缓存
     *
     * @param id ID
     * @return void
     */
    void removeAndCache(Long id);

    /**
     * 查询并缓存
     *
     * @param id ID
     * @return com.lnnk.caffeine.entity.CacheTable
     */
    CacheTable findOneCache(Long id);

    /**
     * 查询并缓存(id<3, content.length > 10)
     *
     * @param id ID
     * @return com.lnnk.caffeine.entity.CacheTable
     */
    CacheTable findOneByCondition(Long id);


    /**
     * 列表并缓存
     *
     * @return java.util.List<com.lnnk.caffeine.entity.CacheTable>
     */
    List<CacheTable> list();

    /**
     * 删除所有缓存
     */
    void removeCache();
}
