package com.lnnk.rabbitmq.controller;

import com.lnnk.rabbitmq.model.support.Message;
import com.lnnk.rabbitmq.topic.provider.TopicProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lnnk
 * @date 2019/8/2 9:04
 **/
@Api(tags = "主题交换机")
@RestController
@RequestMapping("/api/topic")
public class TopicController {

    private final TopicProvider topicProvider;

    public TopicController(TopicProvider topicProvider) {
        this.topicProvider = topicProvider;
    }

    @ApiOperation("向匹配routingKey为topic.key.message队列发送消息")
    @PostMapping("/queue1")
    public void sendQueue1(@RequestBody Message message) {
        topicProvider.sendQueue1(message);
    }

    @ApiOperation("向匹配routingKey为topic.key.msg队列发送消息")
    @PostMapping("/queue2")
    public void sendQueue2(@RequestBody Message message) {
        topicProvider.sendQueue2(message);
    }
}
