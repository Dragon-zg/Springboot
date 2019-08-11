package com.lnnk.hystrix.service;

import com.lnnk.hystrix.model.entity.Person;

/**
 * PersonService
 *
 * @author Lnnk
 * @date 2019/8/11 16:41
 */
public interface PersonService {
    Person semaphore(String arg);

    Person thread(String arg);
}
