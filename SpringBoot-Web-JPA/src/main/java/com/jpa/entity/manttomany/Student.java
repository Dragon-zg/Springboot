package com.jpa.entity.manttomany;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 学生实体
 * @author wangqiang
 * @date 2019-03-13 22:59
 **/
@ApiModel("学生实体")
@Entity
@Table(name = "jpa_student", schema = "test")
public class Student {
    @ApiModelProperty("主键ID")
    private Integer id;
}
