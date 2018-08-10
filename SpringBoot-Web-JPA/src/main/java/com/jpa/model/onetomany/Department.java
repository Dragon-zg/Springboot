package com.jpa.model.onetomany;

import com.google.common.collect.Lists;
import com.jpa.model.base.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

/**
 * @Description: 部门实体
 * @Date: 2018-06-03 18:11
 * @Author: Dragon-zg
 */
@ApiModel("部门表")
@Entity
@Table(name = "jpa_department", schema = "test")
public class Department extends AbstractEntity {
    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("部门名称")
    private String name;

    @ApiModelProperty("部门员工数")
    private Integer quantity;

    private List<Employee> employees = Lists.newArrayList();

    @OneToMany(mappedBy = "department")
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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
    @Column(name = "name", columnDefinition = "varchar(40) not null comment '部门名称'")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "quantity", columnDefinition = "int(5) not null comment '部门员工数'")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
