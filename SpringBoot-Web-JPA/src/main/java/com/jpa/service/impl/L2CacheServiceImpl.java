package com.jpa.service.impl;

import com.jpa.entity.L2Cache.L2Cache;
import com.jpa.repository.L2CacheRepository;
import com.jpa.service.L2CacheService;
import com.jpa.service.base.AbstractCurdService;
import com.web.enums.ExceptionCode;
import com.web.exception.CustomizedException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author wangqiang
 * @date 2019-05-18 16:27
 **/
@Service
public class L2CacheServiceImpl extends AbstractCurdService<L2Cache, String> implements L2CacheService {

    private final L2CacheRepository l2CacheRepository;

    protected L2CacheServiceImpl(L2CacheRepository l2CacheRepository) {
        super(l2CacheRepository);
        this.l2CacheRepository = l2CacheRepository;
    }

    /**
     * 初始化部门数据
     *
     * @return void
     */
    @Override
    public void init() {
        L2Cache l2Cache = L2Cache.builder().build();
        l2CacheRepository.save(l2Cache);
    }

    /**
     * 详情
     *
     * @param id
     * @return com.jpa.entity.onetoone.Person
     */
    @Override
    public L2Cache detail(String id) {
        Optional<L2Cache> optional = l2CacheRepository.findById(id);
        return optional.orElseThrow(() -> new CustomizedException(ExceptionCode.DATA_NOT_EXIST));
    }
}
