package com.lnnk.redis.controller;

import com.lnnk.redis.entity.CacheTable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * RedisController
 *
 * @author wangqiang
 * @date 2019/6/27 8:47
 **/
@Api(value = "redis", tags = "redis")
@RestController
@RequestMapping("/api/redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String, CacheTable> redisTemplate;

    @ApiOperation(value = "查询")
    @PostMapping(value = {"/cacheTable/get"})
    public CacheTable getCacheTable(@RequestParam String redisKey) {
        return redisTemplate.opsForValue().get(redisKey);
    }

    @ApiOperation(value = "添加")
    @PostMapping(value = {"/cacheTable/save"})
    public void saveCacheTable(@RequestBody CacheTable cacheTable) {
        if (null != cacheTable && null != cacheTable.getId()) {
            redisTemplate.opsForValue().set(String.valueOf(cacheTable.getId()), cacheTable);
        }
    }

    @ApiOperation(value = "查询")
    @PostMapping(value = {"/string/get"})
    public String getString(@RequestParam String redisKey) {
        return stringRedisTemplate.opsForValue().get(redisKey);
    }


    @ApiOperation(value = "添加")
    @PostMapping(value = {"/string/save"})
    public void saveString(@RequestBody String value) {
        stringRedisTemplate.opsForValue().set(value, value);
    }
}
