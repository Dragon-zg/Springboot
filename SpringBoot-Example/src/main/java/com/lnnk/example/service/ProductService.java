package com.lnnk.example.service;

import com.lnnk.example.exception.RollbackException;

/**
 * ProductService
 *
 * @author lnnk
 * @date 2019/7/15 13:45
 **/
public interface ProductService {

    /**
     * Propagation.REQUIRED 支持当前事务,如果当前没有事务,就新建一个事务.这是最常见的选择.
     *
     * @throws RollbackException 自定义
     */
    void insertByRequiredThenRollback() throws RollbackException;

    /**
     * PROPAGATION_REQUIRES_NEW – 新建事务,如果当前存在事务,把当前事务挂起.
     *
     * @throws RollbackException 自定义
     */
    void insertByRequireNewThenRollback() throws RollbackException;

    /**
     * 插入一条记录并抛出异常,该方法事务不会回滚
     *
     * @throws RollbackException 自定义
     */
    void invokeInsertThenRollback1() throws RollbackException;

    /**
     * 插入一条记录并抛出异常,该方法事务会回滚
     *
     * @throws RollbackException 自定义
     */
    void invokeInsertThenRollback2() throws RollbackException;

    /**
     * 插入一条记录并抛出异常,该方法事务会回滚
     *
     * @throws RollbackException 自定义
     */
    void invokeInsertThenRollback3() throws RollbackException;

    /**
     * 测试Propagation.REQUIRES_NEW
     */
    void requiresNew();
}
