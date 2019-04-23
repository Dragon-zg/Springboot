package com.jpa.entity.onetomany;

import com.google.common.collect.Lists;
import com.jpa.entity.base.AbstractEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Description: 部门实体
 * @Date: 2018-06-03 18:11
 * @Author: Dragon-zg
 */
@Data
@Builder
@ApiModel("部门表")
@Entity
@Table(name = "jpa_department", schema = "test")
public class Department extends AbstractEntity {

    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键ID")
    private Integer id;

    @Basic
    @Column(name = "name", columnDefinition = "varchar(40) not null comment '部门名称'")
    @ApiModelProperty("部门名称")
    private String name;

    @Basic
    @Column(name = "quantity", columnDefinition = "int(5) not null comment '部门员工数'")
    @ApiModelProperty("部门员工数")
    private Integer quantity;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees = Lists.newArrayList();
}
