package com.jpa.model.dto;

import com.jpa.model.converter.InputConverter;
import com.jpa.model.entity.onetomany.Department;
import lombok.Data;

/**
 * Department DTO
 * @author Dragon-zg
 * @date 2019/6/10 15:13
 **/
@Data
public class DepartmentDTO implements InputConverter<Department> {

    private String name;

    private Integer quantity;

}
