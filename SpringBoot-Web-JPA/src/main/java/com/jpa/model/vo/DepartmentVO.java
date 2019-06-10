package com.jpa.model.vo;

import com.jpa.model.converter.OutputConverter;
import com.jpa.model.entity.unidirectional.onetomany.Department;
import lombok.Data;

/**
 * Department VO
 * @author wangqiang
 * @date 2019/6/10 15:14
 **/
@Data
public class DepartmentVO implements OutputConverter<DepartmentVO, Department> {

    private String name;

    private Integer quantity;

}