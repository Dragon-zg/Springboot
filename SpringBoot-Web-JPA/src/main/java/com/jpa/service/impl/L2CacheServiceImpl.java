package com.jpa.service.impl;

import com.jpa.model.entity.l2cache.L2Cache;
import com.jpa.model.enums.UseType;
import com.jpa.repository.L2CacheRepository;
import com.jpa.service.L2CacheService;
import com.jpa.service.base.AbstractCurdService;
import org.springframework.stereotype.Service;

/**
 * @author Dragon-zg
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
        L2Cache l2Cache1 = L2Cache.builder().useType(UseType.Enable).build();
        l2CacheRepository.save(l2Cache1);
        L2Cache l2Cache2 = L2Cache.builder().useType(UseType.Disable).build();
        l2CacheRepository.save(l2Cache2);
    }
}
