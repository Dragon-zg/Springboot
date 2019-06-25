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
    public CacheTable save(CacheTable cacheTable);

    /**
     * 删除并删除缓存
     *
     * @param id ID
     * @return void
     */
    public void remove(Long id);

    /**
     * 查询并缓存
     *
     * @param id ID
     * @return com.lnnk.caffeine.entity.CacheTable
     */
    public CacheTable findOne(Long id);
}
