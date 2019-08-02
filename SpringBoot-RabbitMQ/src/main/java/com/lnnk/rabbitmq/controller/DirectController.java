package com.lnnk.rabbitmq.controller;

import com.lnnk.rabbitmq.direct.provider.DirectProvider;
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
@Api(tags = "直连交换机")
@RestController
@RequestMapping("/api/direct")
public class DirectController {

    private final DirectProvider directProvider;

    public DirectController(DirectProvider directProvider) {
        this.directProvider = directProvider;
    }

    @ApiOperation("向队列1发送消息")
    @PostMapping("/queue1")
    public void sendQueue1(@RequestBody AmqpMessage amqpMessage) {
        directProvider.sendQueue1(amqpMessage);
    }

    @ApiOperation("向队列1发送消息,类型string")
    @PostMapping("/queue1/string")
    public void sendQueue1(@RequestBody String message) {
        directProvider.sendQueue1(message);
    }

    @ApiOperation("向队列2发送消息")
    @PostMapping("/queue2")
    public void sendQueue2(@RequestBody AmqpMessage amqpMessage) {
        directProvider.sendQueue2(amqpMessage);
    }
}
