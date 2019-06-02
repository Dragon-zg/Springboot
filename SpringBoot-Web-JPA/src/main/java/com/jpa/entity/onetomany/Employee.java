package com.jpa.entity.onetomany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jpa.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Description: 员工实体
 * @Date: 2018-06-03 18:11
 * @Author: Dragon-zg
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("员工表")
@Entity
@Table(name = "jpa_employee")
@Where(clause = "delete_flag = 0")
public class Employee extends BaseEntity {

    @Basic
    @Column(name = "name", columnDefinition = "varchar(40) not null comment '员工姓名'")
    @ApiModelProperty("员工姓名")
    private String name;

    @Basic
    @Column(name = "age", columnDefinition = "int(2) not null comment '年龄'")
    @ApiModelProperty("年龄")
    private Integer age;

    @Basic
    @Column(name = "birthday", columnDefinition = "date  comment '生日'")
    @ApiModelProperty("生日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
}
