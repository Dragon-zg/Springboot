package com.jpa.model.dto;

import com.jpa.model.converter.InputConverter;
import com.jpa.model.entity.unidirectional.onetomany.Department;
import lombok.Data;

/**
 * Department DTO
 * @author wangqiang
 * @date 2019/6/10 15:13
 **/
@Data
public class DepartmentDTO implements InputConverter<Department> {

    private String name;

    private Integer quantity;

}
