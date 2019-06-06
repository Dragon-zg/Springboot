package com.jpa.model.entity.unidirectional.manttomany;

import io.swagger.annotations.ApiModelProperty;

/**
 * 学生实体
 * @author Dragon-zg
 * @date 2019-03-13 22:59
 **/
//@ApiModel("学生实体")
//@Entity
//@Table(name = "jpa_student", schema = "test")
public class Student {
    @ApiModelProperty("主键ID")
    private Integer id;
}
