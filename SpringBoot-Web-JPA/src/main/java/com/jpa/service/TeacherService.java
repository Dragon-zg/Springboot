package com.jpa.service;

import com.jpa.model.converter.InputConverter;
import com.jpa.model.entity.unidirectional.manttomany.Teacher;
import com.jpa.service.base.CurdService;

/**
 * @author Dragon-zg
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
