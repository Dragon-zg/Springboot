package com.jpa.entity.onetomany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jpa.entity.base.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description: 员工实体
 * @Date: 2018-06-03 18:11
 * @Author: Dragon-zg
 */
@Data
@Builder
@ApiModel("员工表")
@Entity
@Table(name = "jpa_employee", schema = "test")
public class Employee extends AbstractEntity {

    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键ID")
    private Integer id;

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

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", referencedColumnName = "id", columnDefinition = "int(11) comment 'department id 外键'", insertable = false, updatable = false)
    private Department department;
}
