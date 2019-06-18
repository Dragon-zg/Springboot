package com.lnnk.jpa.model.entity.onetomany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lnnk.jpa.model.entity.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * @description: 员工实体
 * @date: 2018-06-03 18:11
 * @author: Lnnk
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "jpa_employee")
@Where(clause = "delete_flag = 0")
public class Employee extends BaseEntity {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * 员工姓名
     */
    @Basic
    @Column(name = "name", columnDefinition = "varchar(40) not null comment '员工姓名'")
    private String name;

    /**
     * 年龄
     */
    @Basic
    @Column(name = "age", columnDefinition = "int(2) not null comment '年龄'")
    private Integer age;

    /**
     * 生日
     */
    @Basic
    @Column(name = "birthday", columnDefinition = "date  comment '生日'")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
}
