package com.lnnk.rabbitmq.model.support;

import lombok.Data;

import java.io.Serializable;

/**
 * 消息实体
 *
 * @author lnnk
 * @date 2019/8/1 17:29
 **/
@Data
public class Message implements Serializable {
    /**
     * 消息内容
     */
    private String content;
}
