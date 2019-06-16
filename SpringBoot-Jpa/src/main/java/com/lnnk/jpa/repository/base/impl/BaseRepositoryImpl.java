package com.lnnk.jpa.repository.base.impl;

import com.lnnk.jpa.model.entity.base.BaseEntity;
import com.lnnk.jpa.repository.base.BaseRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

/**
 * Implementation of base repository.
 * @author Lnnk
 * @date 2019/5/5 10:14
 **/
@Log4j2
public class BaseRepositoryImpl<T extends BaseEntity, ID> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final JpaEntityInformation<T, ID> entityInformation;

    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
    }
}
