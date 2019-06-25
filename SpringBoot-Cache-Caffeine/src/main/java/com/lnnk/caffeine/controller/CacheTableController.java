package com.lnnk.caffeine.controller;

import com.lnnk.caffeine.service.CacheTableService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CacheTableController
 *
 * @author wangqiang
 * @date 2019/6/25 16:03
 **/
@Api(value = "cacheTable", tags = "cacheTable")
@RestController
@RequestMapping("/api/cacheTable")
public class CacheTableController {

    private final CacheTableService cacheTableService;

    public CacheTableController(CacheTableService cacheTableService) {
        this.cacheTableService = cacheTableService;
    }
}
