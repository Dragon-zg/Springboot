package com.jpa.controller;

import com.jpa.model.entity.l2cache.L2Cache;
import com.jpa.service.L2CacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dragon-zg
 * @date 2019/4/24 10:40
 **/
@Api(value = "l2Cache", tags = "l2Cache")
@RestController
@RequestMapping("/api/l2Cache")
public class L2CacheController {

    private final L2CacheService l2CacheService;

    public L2CacheController(L2CacheService departmentService) {
        this.l2CacheService = departmentService;
    }

    @ApiOperation(value = "初始化")
    @PostMapping(value = {"/init"})
    public void init() {
        l2CacheService.init();
    }

    @ApiOperation(value = "详情")
    @GetMapping(value = {"/{id}/detail"})
    public L2Cache detail(@PathVariable("id") String id) {
        return l2CacheService.detail(id);
    }
}
