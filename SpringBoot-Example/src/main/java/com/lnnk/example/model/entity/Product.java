package com.lnnk.example.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 商品
 *
 * @author lnnk
 * @date 2019/7/15 11:37
 **/
@Data
@Entity
@Table(name = "example_product")
public class Product {

    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Basic
    @Column(name = "name", columnDefinition = "varchar(40) not null comment '商品名称'")
    private String name;
}
