package com.lnnk.redis.service.impl;

import com.lnnk.redis.entity.CacheTable;
import com.lnnk.redis.repository.CacheTableRepository;
import com.lnnk.redis.service.CacheTableService;
import com.lnnk.web.enums.ExceptionCode;
import com.lnnk.web.exception.CustomizedException;
import com.lnnk.web.util.JsonUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * CacheTableServiceImpl
 *
 * @author lnnk
 * @date 2019/6/25 16:00
 **/
@Log4j2
@Service
@CacheConfig(cacheNames = "cacheTable")
public class CacheTableServiceImpl implements CacheTableService {

    private final CacheTableRepository cacheTableRepository;

    public CacheTableServiceImpl(CacheTableRepository cacheTableRepository) {
        this.cacheTableRepository = cacheTableRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void init() {
        CacheTable cacheTable1 = new CacheTable();
        cacheTable1.setContent("1");
        cacheTableRepository.save(cacheTable1);

        CacheTable cacheTable2 = new CacheTable();
        cacheTable2.setContent("2");
        cacheTableRepository.save(cacheTable2);

        CacheTable cacheTable3 = new CacheTable();
        cacheTable3.setContent("3333333333333333333333333");
        cacheTableRepository.save(cacheTable3);
    }

    @Override
    @CachePut(key = "#cacheTable.id")
    @Transactional(rollbackFor = Exception.class)
    public CacheTable saveAndCache(CacheTable cacheTable) {
        // @CachePut每次都会执行方法，并将结果存入指定的缓存中
        CacheTable saveModel = cacheTableRepository.save(cacheTable);
        log.info("保存并新增缓存: {}", saveModel.toString());
        return saveModel;
    }

    @Override
    @CacheEvict(key = "#id")
    @Transactional(rollbackFor = Exception.class)
    public void removeAndCache(Long id) {
        // @CacheEvict是用来标注在需要清除缓存的元素
        cacheTableRepository.deleteById(id);
        log.info("删除并删除缓存: {}", id);
    }

    @Override
    @Cacheable(key = "'cacheTable.' + #id", unless = "#result == null")
    public CacheTable findOneCache(Long id) {
        // @Cacheable执行前会去检查缓存中是否存在之前执行过的结果,若有则直接返回缓存数据,反之每次都会执行方法，并将结果存入指定的缓存中
        Optional<CacheTable> optional = cacheTableRepository.findById(id);
        CacheTable cacheTable = optional.orElseThrow(() -> new CustomizedException(ExceptionCode.DATA_NOT_EXIST));
        log.info("查询并缓存: {}", cacheTable.toString());
        return cacheTable;
    }

    @Override
    @Cacheable(key = "#id", condition = "#id <= 3", unless = "#result.content.length() > 10")
    public CacheTable findOneByCondition(Long id) {
        Optional<CacheTable> optional = cacheTableRepository.findById(id);
        CacheTable cacheTable = optional.orElseThrow(() -> new CustomizedException(ExceptionCode.DATA_NOT_EXIST));
        log.info("查询并缓存使用条件(id<3, content.length > 10): {}", cacheTable.toString());
        return cacheTable;
    }

    @Override
    @Cacheable(key = "'list'", unless = "#result == null")
    public List<CacheTable> list() {
        // @Cacheable执行前会去检查缓存中是否存在之前执行过的结果,若有则直接返回缓存数据,反之每次都会执行方法，并将结果存入指定的缓存中
        List<CacheTable> list = cacheTableRepository.findAll();
        log.info("列表并缓存: {}", JsonUtils.toJson(list));
        return list;
    }

    @Override
    @CacheEvict(allEntries = true, beforeInvocation = true)
    public void removeCache() {
        // allEntries是boolean类型，表示是否需要清除缓存中的所有元素。默认为false，表示不需要。
        // beforeInvocation可以改变触发清除操作的时间，当我们指定该属性值为true时，Spring会在调用该方法之前清除缓存中的指定元素
        throw new CustomizedException(ExceptionCode.SUCCESS);
    }

    @Override
    @Cacheable
    public CacheTable customizedKeyGenerator(Long id) {
        Optional<CacheTable> optional = cacheTableRepository.findById(id);
        CacheTable cacheTable = optional.orElseThrow(() -> new CustomizedException(ExceptionCode.DATA_NOT_EXIST));
        log.info("根据自定义key生成缓存key: {}", cacheTable.toString());
        return cacheTable;
    }
}
