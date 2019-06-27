package com.lnnk.mongo.repository;

import com.lnnk.mongo.entity.MongoInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author lnnk
 * @date 2019/6/25 14:09
 **/
public interface MongoInfoRepository extends MongoRepository<MongoInfo, String> {
}
