package com.jpa.service;

import com.jpa.entity.onetomany.Department;
import com.jpa.service.base.CurdService;

/**
 * @author wangqiang
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
     * @return com.jpa.entity.onetoone.Person
     */
    Department detail(Long id);

    /**
     * 更新
     *
     * @param id
     * @param department
     * @return void
     */
    void update(final Long id, final Department department);

    /**
     * 删除
     *
     * @param id
     * @return void
     */
    void delete(final Long id);
}
