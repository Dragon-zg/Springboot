package com.lnnk.jpa.service;

import com.lnnk.jpa.model.converter.InputConverter;
import com.lnnk.jpa.model.entity.onetomany.Department;
import com.lnnk.jpa.service.base.CurdService;

/**
 * @author Lnnk
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
