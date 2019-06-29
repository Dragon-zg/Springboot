package com.lnnk.mongo.controller;

import com.lnnk.mongo.entity.MongoInfo;
import com.lnnk.mongo.service.MongoInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CacheTableController
 *
 * @author lnnk
 * @date 2019/6/25 16:03
 **/
@Api(value = "mongoInfo", tags = "mongoInfo")
@RestController
@RequestMapping("/api/mongoInfo")
public class MongoInfoController {

    private final MongoInfoService mongoInfoService;

    public MongoInfoController(MongoInfoService mongoInfoService) {
        this.mongoInfoService = mongoInfoService;
    }

    @ApiOperation(value = "初始化")
    @PostMapping(value = {"/init"})
    public void init() {
        mongoInfoService.init();
    }

    @ApiOperation(value = "保存")
    @PostMapping(value = {"/save"})
    public void save(@RequestBody MongoInfo mongoInfo) {
        mongoInfoService.save(mongoInfo);
    }

    @ApiOperation(value = "查询")
    @GetMapping(value = {"/{uuid}/findOne"})
    public MongoInfo findOne(@PathVariable("uuid") String uuid) {
        MongoInfo mongoInfo = mongoInfoService.findOne(uuid);
        return mongoInfo;
    }

    @ApiOperation(value = "更新")
    @PutMapping(value = {"/{uuid}/update"})
    public void update(@PathVariable("uuid") String uuid, @RequestBody MongoInfo mongoInfo) {
        mongoInfoService.update(uuid, mongoInfo);
    }

    @ApiOperation(value = "列表")
    @GetMapping(value = {"/list"})
    public List<MongoInfo> list() {
        return mongoInfoService.list();
    }

    @ApiOperation(value = "删除")
    @DeleteMapping(value = {"/{uuid}/remove"})
    public void remove(@PathVariable("uuid") String uuid) {
        mongoInfoService.remove(uuid);
    }
}
