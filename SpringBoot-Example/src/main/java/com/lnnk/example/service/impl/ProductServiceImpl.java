package com.lnnk.example.service.impl;

import com.lnnk.example.exception.RollbackException;
import com.lnnk.example.model.entity.Product;
import com.lnnk.example.repository.ProductRepository;
import com.lnnk.example.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * ProductService
 *
 * @author lnnk
 * @date 2019/7/15 13:45
 **/
@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    /**
     * Propagation.REQUIRED 支持当前事务,如果当前没有事务,就新建一个事务.这是最常见的选择.
     *
     * @throws RollbackException 自定义
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void insertByRequiredThenRollback() throws RollbackException {
        productRepository.save(Product.builder().name("REQUIRES").build());
        throw new RollbackException();
    }

    /**
     * PROPAGATION_REQUIRES_NEW – 新建事务,如果当前存在事务,把当前事务挂起.
     *
     * @throws RollbackException 自定义
     */
    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void insertByRequireNewThenRollback() throws RollbackException {
        productRepository.save(Product.builder().name("REQUIRES_NEW").build());
        throw new RollbackException();
    }

    /**
     * 插入一条记录并抛出异常,该方法事务不会回滚
     *
     * @throws RollbackException 自定义
     */
    @Override
    public void invokeInsertThenRollback1() throws RollbackException {
        insertByRequiredThenRollback();
    }

    /**
     * 插入一条记录并抛出异常,该方法事务会回滚
     *
     * @throws RollbackException 自定义
     */
    @Override
    public void invokeInsertThenRollback2() throws RollbackException {
        productService.insertByRequiredThenRollback();
    }

    /**
     * 插入一条记录并抛出异常,该方法事务会回滚
     *
     * @throws RollbackException 自定义
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void invokeInsertThenRollback3() throws RollbackException {
        insertByRequiredThenRollback();
    }

    /**
     * 测试Propagation.REQUIRES_NEW
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void requiresNew() {
        productRepository.save(Product.builder().name("TEST REQUIRES_NEW").build());
        try {
            productService.insertByRequireNewThenRollback();
        } catch (RollbackException e) {
            log.warn("测试Propagation.REQUIRES_NEW");
        }
    }
}
