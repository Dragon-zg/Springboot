package com.jpa.repository;

import com.jpa.model.entity.unidirectional.onetomany.Department;
import com.jpa.repository.base.BaseRepository;

/**
 * PersonRepository
 *
 * @author Dragon-zg
 * @date 2019/4/24 16:17
 */
public interface DepartmentRepository extends BaseRepository<Department, Long> {
}
