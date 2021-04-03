package com.lnnk.netty.controller;

import com.lnnk.netty.entity.NettyTable;
import com.lnnk.netty.service.NettyTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

}
