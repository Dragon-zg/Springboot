package com.lnnk.mongo.service.impl;

import com.lnnk.mongo.entity.MongoInfo;
import com.lnnk.mongo.repository.MongoInfoRepository;
import com.lnnk.mongo.service.MongoInfoService;
import com.lnnk.web.enums.ExceptionCode;
import com.lnnk.web.exception.CustomizedException;
import com.lnnk.web.util.UUIDUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    @Transactional(rollbackFor = Exception.class)
    public void init() {
        MongoInfo info1 = new MongoInfo();
        info1.setUuid(UUIDUtils.getUUID());
        info1.setContent("1111");
        info1.setFlag(0);
        mongoInfoRepository.save(info1);

        MongoInfo info2 = new MongoInfo();
        info2.setUuid(UUIDUtils.getUUID());
        info2.setContent("2222");
        info2.setFlag(0);
        mongoInfoRepository.save(info2);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(MongoInfo mongoInfo) {
        mongoInfo.setUuid(UUIDUtils.getUUID());
        mongoInfoRepository.save(mongoInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(String uuid, MongoInfo mongoInfo) {
        Optional<MongoInfo> optional = mongoInfoRepository.findById(uuid);
        MongoInfo info = optional.orElseThrow(() -> new CustomizedException(ExceptionCode.DATA_NOT_EXIST));
        info.setFlag(mongoInfo.getFlag());
        info.setContent(mongoInfo.getContent());
        mongoInfoRepository.save(info);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(String uuid) {
        Optional<MongoInfo> optional = mongoInfoRepository.findById(uuid);
        optional.ifPresent(mongoInfoRepository::delete);
    }

    @Override
    public MongoInfo findOne(String uuid) {
        Optional<MongoInfo> optional = mongoInfoRepository.findById(uuid);
        return optional.orElseThrow(() -> new CustomizedException(ExceptionCode.DATA_NOT_EXIST));
    }

    @Override
    public List<MongoInfo> list() {
        return mongoInfoRepository.findAll();
    }
}
