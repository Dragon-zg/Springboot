package com.lnnk.mybatis.service.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lnnk.mybatis.model.base.BaseEntity;

/**
 * abstract service implementation.
 *
 * @param <T> entity type
 * @author Lnnk
 */
public abstract class AbstractCurdService<T extends BaseEntity> implements CurdService<T> {

    private final BaseMapper<T> baseMapper;

    protected AbstractCurdService(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }
}
