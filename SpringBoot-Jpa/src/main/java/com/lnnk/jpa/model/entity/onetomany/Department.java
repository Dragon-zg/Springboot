package com.lnnk.jpa.model.entity.onetomany;

import com.lnnk.jpa.model.entity.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * @description: 部门实体
 * @date: 2018-06-03 18:11
 * @author: Lnnk
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "jpa_department")
@Where(clause = "delete_flag = 0")
public class Department extends BaseEntity {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * 部门名称
     */
    @Basic
    @Column(name = "name", columnDefinition = "varchar(40) not null comment '部门名称'")
    private String name;

    /**
     *  部门员工数
     */
    @Basic
    @Column(name = "quantity", columnDefinition = "int(5) not null comment '部门员工数'")
    private Integer quantity;

    @OneToMany(targetEntity = Employee.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id", referencedColumnName = "id", columnDefinition = "int(11) comment 'department id 外键'")
    private List<Employee> employees;
}
