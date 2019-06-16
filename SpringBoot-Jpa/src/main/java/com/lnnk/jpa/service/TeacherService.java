package com.lnnk.jpa.service;

import com.lnnk.jpa.model.converter.InputConverter;
import com.lnnk.jpa.model.entity.manttomany.Teacher;
import com.lnnk.jpa.service.base.CurdService;

/**
 * @author Lnnk
 * @date 2019/6/11 14:29
 **/
public interface TeacherService extends CurdService<Teacher, Long> {

    /**
     * 初始化数据
     *
     * @return void
     */
    void init();

    /**
     * 更新
     *
     * @param id
     * @param inputConverter
     * @return void
     */
    void update(final Long id, final InputConverter inputConverter);
}
