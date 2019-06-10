package com.jpa.service;

import com.jpa.model.converter.InputConverter;
import com.jpa.model.entity.unidirectional.onetomany.Department;
import com.jpa.service.base.CurdService;

/**
 * @author Dragon-zg
 * @date 2019-05-18 14:53
 **/
public interface DepartmentService extends CurdService<Department, Long> {
    /**
     * 初始化部门数据
     *
     * @return void
     */
    void initDepartment();

    /**
     * 详情
     *
     * @param id
     * @return com.jpa.model.entity.unidirectional.onetoone.Person
     */
    Department detail(Long id);

    /**
     * 更新
     *
     * @param id
     * @param department
     * @return void
     */
    void update(final Long id, final InputConverter inputConverter);

    /**
     * 删除
     *
     * @param id
     * @return void
     */
    void delete(final Long id);
}
