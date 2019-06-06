package com.jpa.service.impl;

import com.jpa.model.entity.unidirectional.onetomany.Department;
import com.jpa.model.entity.unidirectional.onetomany.Employee;
import com.jpa.repository.DepartmentRepository;
import com.jpa.service.DepartmentService;
import com.jpa.service.base.AbstractCurdService;
import com.web.enums.DateFormat;
import com.web.enums.ExceptionCode;
import com.web.exception.CustomizedException;
import com.web.util.DateUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author Dragon-zg
 * @date 2019-05-18 16:27
 **/
@Service
public class DepartmentServiceImpl extends AbstractCurdService<Department, Long> implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    protected DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        super(departmentRepository);
        this.departmentRepository = departmentRepository;
    }

    /**
     * 初始化部门数据
     *
     * @return void
     */
    @Override
    public void initDepartment() {
        Employee zhangsan1 = Employee.builder().name("zhangsan1").age(18)
                .birthday(DateUtils.parseDate("2016-01-13 12:13:14", DateFormat.DEFAULT_TIME)).build();
        Employee zhangsan2 = Employee.builder().name("zhangsan2").age(20)
                .birthday(DateUtils.parseDate("2016-02-26 20:13:14", DateFormat.DEFAULT_TIME)).build();
        Department department1 = Department.builder().name("department1").quantity(2).employees(Arrays.asList(zhangsan1, zhangsan2)).build();
        departmentRepository.save(department1);

        Employee lisi = Employee.builder().name("lisi").age(36)
                .birthday(DateUtils.parseDate("2016-04-10 15:13:14", DateFormat.DEFAULT_TIME)).build();
        Employee wangwu = Employee.builder().name("wangwu").age(41)
                .birthday(DateUtils.parseDate("2016-12-10 19:00:00", DateFormat.DEFAULT_TIME)).build();
        Department department2 = Department.builder().name("department2").quantity(2).employees(Arrays.asList(lisi, wangwu)).build();
        departmentRepository.save(department2);
    }

    /**
     * 详情
     *
     * @param id
     * @return com.jpa.model.entity.unidirectional.onetoone.Person
     */
    @Override
    public Department detail(Long id) {
        Optional<Department> optional = departmentRepository.findById(id);
        return optional.orElseThrow(() -> new CustomizedException(ExceptionCode.DATA_NOT_EXIST));
    }

    /**
     * 更新
     *
     * @param id
     * @param department
     * @return void
     */
    @Override
    public void update(Long id, Department department) {
        Optional<Department> optional = departmentRepository.findById(id);
        Department update = optional.orElseThrow(() -> new CustomizedException(ExceptionCode.DATA_NOT_EXIST));
        update.setName(department.getName());
        update.setQuantity(department.getQuantity());
        departmentRepository.save(update);
    }

    /**
     * 删除
     *
     * @param id
     * @return void
     */
    @Override
    public void delete(Long id) {
        Optional<Department> optional = departmentRepository.findById(id);
        optional.ifPresent((department) -> {
            department.setDeleteFlag(true);
            List<Employee> employees = department.getEmployees();
            if (CollectionUtils.isNotEmpty(employees)) {
                employees.forEach((employee) -> {
                    employee.setDeleteFlag(true);
                });
            }
            departmentRepository.save(department);
        });
    }
}
