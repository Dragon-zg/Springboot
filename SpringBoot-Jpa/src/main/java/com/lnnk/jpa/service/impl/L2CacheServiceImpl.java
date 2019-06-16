package com.lnnk.jpa.service.impl;

import com.lnnk.jpa.model.entity.l2cache.L2Cache;
import com.lnnk.jpa.model.enums.UseType;
import com.lnnk.jpa.repository.L2CacheRepository;
import com.lnnk.jpa.service.L2CacheService;
import com.lnnk.jpa.service.base.AbstractCurdService;
import org.springframework.stereotype.Service;

/**
 * @author Lnnk
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

    /**
     * 保存
     *
     * @param l2Cache
     */
    @Override
    public void create(L2Cache l2Cache) {
        l2CacheRepository.save(l2Cache);
    }
}
