package com.jpa.service;

import com.jpa.model.converter.InputConverter;
import com.jpa.model.entity.unidirectional.onetoone.Person;
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
     * 更新
     *
     * @param id
     * @param inputConverter
     * @return void
     */
    void update(final Long id, final InputConverter inputConverter);
}
