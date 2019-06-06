package com.jpa.service;

import com.jpa.entity.unidirectional.onetoone.Person;
import com.jpa.service.base.CurdService;

/**
 * @author Dragon-zg
 * @date 2019/5/7 11:06
 **/
public interface PersonService extends CurdService<Person, Long> {

    /**
     * 初始化公民数据
     *
     * @return void
     */
    void initPerson();

    /**
     * 详情
     *
     * @param id
     * @return com.jpa.entity.unidirectional.onetoone.Person
     */
    Person detail(Long id);

    /**
     * 更新
     *
     * @param id
     * @param person
     * @return void
     */
    void update(final Long id, final Person person);

    /**
     * 删除
     *
     * @param id
     * @return void
     */
    void delete(final Long id);
}
