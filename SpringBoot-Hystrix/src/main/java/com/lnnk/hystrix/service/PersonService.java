package com.lnnk.hystrix.service;

import com.lnnk.hystrix.model.entity.Person;

/**
 * PersonService
 *
 * @author Lnnk
 * @date 2019/8/11 16:41
 */
public interface PersonService {
    /**
     * 隔离策略: SEMAPHORE
     *
     * @param arg 参数
     * @return com.lnnk.hystrix.model.entity.Person
     */
    Person semaphore(String arg);

    /**
     * 隔离策略: THREAD
     * @param arg 参数
     * @return com.lnnk.hystrix.model.entity.Person
     */
    Person thread(String arg);
}
