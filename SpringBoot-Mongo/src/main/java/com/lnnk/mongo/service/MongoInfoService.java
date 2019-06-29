package com.lnnk.mongo.service;

import com.lnnk.mongo.entity.MongoInfo;

import java.util.List;

/**
 * MongoInfoService
 *
 * @author lnnk
 * @date 2019/6/25 15:59
 **/
public interface MongoInfoService {

    /**
     * 初始化
     */
    void init();

    /**
     * 保存
     *
     * @param mongoInfo mongoInfo
     * @return com.lnnk.mongo.entity.MongoInfo
     */
    void save(MongoInfo mongoInfo);

    /**
     * 保存
     *
     * @param mongoInfo mongoInfo
     * @return com.lnnk.mongo.entity.MongoInfo
     */
    void update(String uuid, MongoInfo mongoInfo);

    /**
     * 删除
     *
     * @param uuid ID
     * @return void
     */
    void remove(String uuid);

    /**
     * 查询并缓存
     *
     * @param uuid ID
     * @return com.lnnk.mongo.entity.MongoInfo
     */
    MongoInfo findOne(String uuid);

    /**
     * 列表并缓存
     *
     * @return java.util.List<com.lnnk.mongo.entity.MongoInfo>
     */
    List<MongoInfo> list();
}
