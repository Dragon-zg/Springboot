package com.lnnk.mongo.service.impl;

import com.lnnk.mongo.entity.MongoInfo;
import com.lnnk.mongo.repository.MongoInfoRepository;
import com.lnnk.mongo.service.MongoInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * MongoInfoServiceImpl
 *
 * @author lnnk
 * @date 2019/6/25 16:00
 **/
@Service
public class MongoInfoServiceImpl implements MongoInfoService {

    private final MongoInfoRepository mongoInfoRepository;

    public MongoInfoServiceImpl(MongoInfoRepository mongoInfoRepository) {
        this.mongoInfoRepository = mongoInfoRepository;
    }

    @Override
    public void init() {

    }

    @Override
    public void save(MongoInfo mongoInfo) {

    }

    @Override
    public void update(MongoInfo mongoInfo) {

    }

    @Override
    public void remove(String uuid) {

    }

    @Override
    public MongoInfo findOne(String uuid) {
        return null;
    }

    @Override
    public List<MongoInfo> list() {
        return null;
    }
}
