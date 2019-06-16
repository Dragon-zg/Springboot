package com.lnnk.jpa.model.dto;

import com.lnnk.jpa.model.converter.InputConverter;
import com.lnnk.jpa.model.entity.onetomany.Department;
import lombok.Data;

/**
 * Department DTO
 * @author Lnnk
 * @date 2019/6/10 15:13
 **/
@Data
public class DepartmentDTO implements InputConverter<Department> {

    private String name;

    private Integer quantity;

}
