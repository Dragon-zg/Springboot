package com.jpa.controller;

import com.jpa.model.dto.L2CacheDTO;
import com.jpa.model.entity.l2cache.L2Cache;
import com.jpa.model.vo.L2CacheVO;
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

    @ApiOperation(value = "保存")
    @PostMapping(value = {"/create"})
    public void create(@RequestBody L2CacheDTO l2CacheDto) {
        L2Cache l2Cache = new L2Cache();
        l2CacheDto.convertTo(l2Cache);
        l2CacheService.create(l2Cache);
    }

    @ApiOperation(value = "详情")
    @GetMapping(value = {"/{id}/detail"})
    public L2CacheVO detail(@PathVariable("id") String id) {
        return new L2CacheVO().convertFrom(l2CacheService.detail(id));
    }
}
