package com.jpa.model;

import javax.persistence.*;

/**
 * @Description:
 * @Date: 2017-12-16 19:53
 */
//JPA注解,@Entity意味这个是数据库的一张表,
@Entity
@Table(name ="girl", schema = "girl")
public class Girl {
    //@Id id字段,自增
    private Integer id;

    private String cupSize;

    private Integer age;

    @Id
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
