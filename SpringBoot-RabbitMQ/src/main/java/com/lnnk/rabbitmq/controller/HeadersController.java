package com.lnnk.rabbitmq.controller;

import com.google.common.collect.Maps;
import com.lnnk.rabbitmq.headers.provider.HeadersProvider;
import com.lnnk.rabbitmq.model.support.AmqpMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author lnnk
 * @date 2019/8/2 9:04
 **/
@Api(tags = "首部交换机")
@RestController
@RequestMapping("/api/headers")
public class HeadersController {

    private final HeadersProvider headersProvider;

    public HeadersController(HeadersProvider headersProvider) {
        this.headersProvider = headersProvider;
    }

    @ApiOperation("队列部分匹配")
    @PostMapping("/queue/part")
    public void sendQueue1Part(@RequestBody AmqpMessage amqpMessage) {
        headersProvider.sendQueue(getPartHeaders(), amqpMessage);
    }

    @ApiOperation("队列全部匹配")
    @PostMapping("/queue/all")
    public void sendQueue1All(@RequestBody AmqpMessage amqpMessage) {
        headersProvider.sendQueue(getAllHeaders(), amqpMessage);
    }

    private Map<String, Object> getAllHeaders() {
        Map<String, Object> headerValues = Maps.newHashMapWithExpectedSize(2);
        headerValues.put("type", "cash");
        headerValues.put("aging", "fast");
        return headerValues;
    }

    private Map<String, Object> getPartHeaders() {
        Map<String, Object> headerValues = Maps.newHashMapWithExpectedSize(1);
        headerValues.put("type", "cash");
        headerValues.put("aging", "slow");
        return headerValues;
    }
}
