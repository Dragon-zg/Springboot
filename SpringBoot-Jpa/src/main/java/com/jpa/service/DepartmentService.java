package com.jpa.service;

import com.jpa.model.converter.InputConverter;
import com.jpa.model.entity.onetomany.Department;
import com.jpa.service.base.CurdService;

/**
 * @author Dragon-zg
 * @date 2019-05-18 14:53
 **/
public interface DepartmentService extends CurdService<Department, Long> {
    /**
     * 初始化部门数据
     */
    void initDepartment();

    /**
     * 更新
     *
     * @param id
     * @param inputConverter
     */
    void update(final Long id, final InputConverter inputConverter);
}
