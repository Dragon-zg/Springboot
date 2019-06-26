package com.lnnk.caffeine.controller;

import com.lnnk.caffeine.entity.CacheTable;
import com.lnnk.caffeine.service.CacheTableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "初始化")
    @PostMapping(value = {"/init"})
    public void init() {
        cacheTableService.init();
    }

    @ApiOperation(value = "保存并添加缓存")
    @PostMapping(value = {"/saveAndCache"})
    public void saveAndCache(@RequestBody CacheTable cacheTable) {
        cacheTableService.saveAndCache(cacheTable);
    }

    @ApiOperation(value = "查询并缓存")
    @GetMapping(value = {"/{id}/findOneCache"})
    public CacheTable findOneCache(@PathVariable("id") Long id) {
        CacheTable cacheTable = cacheTableService.findOneCache(id);
        return cacheTable;
    }

    @ApiOperation(value = "查询并根据条件缓存")
    @GetMapping(value = {"/{id}/findOneByCondition"})
    public CacheTable findOneByCondition(@PathVariable("id") Long id) {
        CacheTable cacheTable = cacheTableService.findOneByCondition(id);
        return cacheTable;
    }

    @ApiOperation(value = "删除并删除缓存")
    @DeleteMapping(value = {"/{id}/removeAndCache"})
    public void removeAndCache(@PathVariable("id") Long id) {
        cacheTableService.removeAndCache(id);
    }

    @ApiOperation(value = "删除所有缓存")
    @DeleteMapping(value = {"/removeCache"})
    public void removeCache() {
        cacheTableService.removeCache();
    }
}
