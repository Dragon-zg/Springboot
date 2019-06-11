package com.jpa.model.entity.unidirectional.onetomany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jpa.model.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * @description: 员工实体
 * @date: 2018-06-03 18:11
 * @author: Dragon-zg
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

    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键ID")
    protected Long id;

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
