package com.lnnk.caffeine.service.impl;

import com.lnnk.caffeine.entity.CacheTable;
import com.lnnk.caffeine.repository.CacheTableRepository;
import com.lnnk.caffeine.service.CacheTableService;
import com.lnnk.web.enums.ExceptionCode;
import com.lnnk.web.exception.CustomizedException;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * CacheTableServiceImpl
 *
 * @author wangqiang
 * @date 2019/6/25 16:00
 **/
@Log4j2
@Service
public class CacheTableServiceImpl implements CacheTableService {

    private final CacheTableRepository cacheTableRepository;

    public CacheTableServiceImpl(CacheTableRepository cacheTableRepository) {
        this.cacheTableRepository = cacheTableRepository;
    }

    @Override
    @CachePut(value = "cacheTable", key = "#cacheTable.id")
    public CacheTable save(CacheTable cacheTable) {
        CacheTable saveModel = cacheTableRepository.save(cacheTable);
        log.info("保存并新增缓存: {}", saveModel.toString());
        return saveModel;
    }

    @Override
    @CacheEvict(value = "cacheTable", key = "#id")
    public void remove(Long id) {
        cacheTableRepository.deleteById(id);
        log.info("删除并删除缓存: {}", id);
    }

    @Override
    @Cacheable(value = "people", key = "#id", sync = true)
    public CacheTable findOne(Long id) {
        Optional<CacheTable> optional = cacheTableRepository.findById(id);
        CacheTable cacheTable = optional.orElseThrow(() -> new CustomizedException(ExceptionCode.DATA_NOT_EXIST));
        log.info("查询并缓存: {}", cacheTable.toString());
        return cacheTable;
    }
}
