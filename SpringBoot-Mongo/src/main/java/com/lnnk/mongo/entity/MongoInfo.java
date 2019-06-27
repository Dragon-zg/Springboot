package com.lnnk.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 演示mongo的实体
 *
 * @author lnnk
 * @date 2019/6/25 9:37
 */
@Data
@Document
public class MongoInfo {

    /**
     * 主键ID
     */
    @Id
    private String uuid;

    /**
     * 内容
     */
    private String content;

    /**
     * 是否删除 1.是 0.否
     */
    @Field("deleteFlag")
    private Integer flag;
}
