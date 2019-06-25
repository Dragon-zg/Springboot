package com.lnnk.caffeine.service.impl;

import com.lnnk.caffeine.repository.CacheTableRepository;
import com.lnnk.caffeine.service.CacheTableService;
import org.springframework.stereotype.Service;

/**
 * CacheTableServiceImpl
 *
 * @author wangqiang
 * @date 2019/6/25 16:00
 **/
@Service
public class CacheTableServiceImpl implements CacheTableService {

    private final CacheTableRepository cacheTableRepository;

    public CacheTableServiceImpl(CacheTableRepository cacheTableRepository) {
        this.cacheTableRepository = cacheTableRepository;
    }

}
