package com.lnnk.jpa.model.vo;

import com.lnnk.jpa.model.converter.OutputConverter;
import com.lnnk.jpa.model.entity.onetomany.Department;
import lombok.Data;

/**
 * Department VO
 * @author Lnnk
 * @date 2019/6/10 15:14
 **/
@Data
public class DepartmentVO implements OutputConverter<DepartmentVO, Department> {

    private String name;

    private Integer quantity;

}
