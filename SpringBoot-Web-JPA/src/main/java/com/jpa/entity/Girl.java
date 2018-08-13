package com.jpa.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @Description:
 * @Date: 2017-12-16 19:53
 */
//JPA注解,@Entity意味这个是数据库的一张表,
@Entity
@Table(name ="girl", schema = "test")
public class Girl {
    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("")
    private String cupSize;

    @ApiModelProperty("年纪")
    private Integer age;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cup_size")
    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Girl{" +
                "id=" + id +
                ", cupSize='" + cupSize + '\'' +
                ", age=" + age +
                '}';
    }
}
