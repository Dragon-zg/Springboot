package com.lnnk.rabbitmq.controller;

import com.lnnk.rabbitmq.deadletter.provider.DeadLetterProvider;
import com.lnnk.rabbitmq.model.support.AmqpMessage;
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
@Api(tags = "死信队列")
@RestController
@RequestMapping("/api/deadLetter")
public class DeadLetterController {

    private final DeadLetterProvider deadLetterProvider;

    public DeadLetterController(DeadLetterProvider deadLetterProvider) {
        this.deadLetterProvider = deadLetterProvider;
    }

    @ApiOperation("向死信队列发送消息")
    @PostMapping("/deadLetter")
    public void deadLetter(@RequestBody AmqpMessage amqpMessage) {
        deadLetterProvider.deadLetter(amqpMessage);
    }
}
