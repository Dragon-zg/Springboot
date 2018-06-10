package com.jpa.model.oneToOne;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @Description: 中国公民实体
 * @Date: 2018-06-10 20:23
 * @Author: Deagon-zg
 */
@ApiModel("中国公民实体")
@Entity
@Table(name = "jpa_person", schema = "test")
public class Person {
    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("公民姓名")
    private String name;

    private IDCard idcard;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcard_id", referencedColumnName = "id", columnDefinition = "int(11) comment '身份证ID 外键'", unique = true, nullable = false, insertable = false, updatable = false)
    public IDCard getIdcard() {
        return idcard;
    }

    public void setIdcard(IDCard idcard) {
        this.idcard = idcard;
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
    @Column(name = "name", columnDefinition = "varchar(40) not null comment '公民姓名'")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}