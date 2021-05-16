package com.lnnk.netty.controller;

import cn.hutool.core.util.IdUtil;
import com.lnnk.netty.client.sync.NettyClient;
import com.lnnk.netty.codec.MessageBuf;
import com.lnnk.netty.entity.NettyTable;
import com.lnnk.netty.service.NettyTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * NettyTableController
 *
 * @author lnnk
 * @date 2019/6/25 16:03
 **/
@Api(value = "nettyTable", tags = "nettyTable")
@RestController
@RequestMapping("/api/netty")
public class NettyTableController {

    @Autowired
    private NettyTableService nettyTableService;

    @ApiOperation(value = "查询并缓存")
    @GetMapping(value = {"/list"})
    public List<NettyTable> list() {
        return nettyTableService.list();
    }

    @ApiOperation(value = "发送消息")
    @GetMapping(value = {"/send"})
    public String send() throws Exception {
        MessageBuf.JMTransfer jmTransfer = new NettyClient("127.0.0.1", 9999, build()).start();
        return StringUtils.defaultIfBlank(jmTransfer.getContent(), StringUtils.EMPTY);
    }

    public MessageBuf.JMTransfer build() {
        return MessageBuf.JMTransfer.newBuilder()
                .setVersion("send").setDeviceId(IdUtil.fastSimpleUUID()).setContent("send client...").build();
    }
}
