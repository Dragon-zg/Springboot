package com.jpa.controller;

import com.jpa.entity.l2cache.L2Cache;
import com.jpa.service.L2CacheService;
import com.web.model.ResultModel;
import com.web.util.ResultUtils;
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
    public ResultModel init() {
        l2CacheService.init();
        return ResultUtils.success();
    }

    @ApiOperation(value = "详情")
    @GetMapping(value = {"/{id}/detail"})
    public ResultModel<L2Cache> detail(@PathVariable("id") String id) {
        return ResultUtils.success(l2CacheService.detail(id));
    }
}
