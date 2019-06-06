package com.jpa.model.entity.unidirectional.onetomany;

import com.jpa.model.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * @Description: 部门实体
 * @Date: 2018-06-03 18:11
 * @Author: Dragon-zg
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("部门表")
@Entity
@Table(name = "jpa_department")
@Where(clause = "delete_flag = 0")
public class Department extends BaseEntity {

    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键ID")
    protected Long id;

    @Basic
    @Column(name = "name", columnDefinition = "varchar(40) not null comment '部门名称'")
    @ApiModelProperty("部门名称")
    private String name;

    @Basic
    @Column(name = "quantity", columnDefinition = "int(5) not null comment '部门员工数'")
    @ApiModelProperty("部门员工数")
    private Integer quantity;

    @OneToMany(targetEntity = Employee.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", referencedColumnName = "id", columnDefinition = "int(11) comment 'department id 外键'")
    private List<Employee> employees;
}
