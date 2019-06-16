package com.lnnk.jpa.service;

import com.lnnk.jpa.model.converter.InputConverter;
import com.lnnk.jpa.model.entity.onetoone.Person;
import com.lnnk.jpa.service.base.CurdService;

/**
 * @author Lnnk
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
