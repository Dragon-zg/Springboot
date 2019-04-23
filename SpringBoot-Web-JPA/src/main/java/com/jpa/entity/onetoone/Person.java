package com.jpa.entity.onetoone;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * @Description: 中国公民实体
 * @Date: 2018-06-10 20:23
 * @Author: Deagon-zg
 */
@Data
@Builder
@ApiModel("中国公民实体")
@Entity
@Table(name = "jpa_person", schema = "test")
public class Person {

    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键ID")
    private Integer id;

    @Basic
    @Column(name = "name", columnDefinition = "varchar(40) not null comment '公民姓名'")
    @ApiModelProperty("公民姓名")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcard_id", referencedColumnName = "id", columnDefinition = "int(11) comment '身份证ID 外键'", unique = true, nullable = false, insertable = false, updatable = false)
    private IDCard idcard;
}