package com.lnnk.rabbitmq.controller;

import com.lnnk.rabbitmq.fanout.provider.FanoutProvider;
import com.lnnk.rabbitmq.model.support.Message;
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
@Api(tags = "扇形交换机")
@RestController
@RequestMapping("/api/fanout")
public class FanoutController {

    private final FanoutProvider fanoutProvider;

    public FanoutController(FanoutProvider fanoutProvider) {
        this.fanoutProvider = fanoutProvider;
    }

    @ApiOperation("广播发送消息")
    @PostMapping("/queue")
    public void sendQueue(@RequestBody Message message) {
        fanoutProvider.sendQueue(message);
    }
}
