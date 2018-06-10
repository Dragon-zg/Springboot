package com.jpa.model.oneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jpa.model.base.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description: 员工实体
 * @Date: 2018-06-03 18:11
 * @Author: Dragon-zg
 */
@ApiModel("员工表")
@Entity
@Table(name = "jpa_employee", schema = "test")
public class Employee extends AbstractEntity {
    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("员工姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("生日")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;

    private Department department;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", referencedColumnName = "id", columnDefinition = "int(11) comment 'department id 外键'", insertable = false, updatable = false)
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", columnDefinition = "varchar(40) not null comment '员工姓名'")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age", columnDefinition = "int(2) not null comment '年龄'")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "birthday", columnDefinition = "date  comment '生日'")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
