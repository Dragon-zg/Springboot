package com.lnnk.caffeine.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * 演示缓存的实体
 *
 * @author lnnk
 * @date 2019/6/25 9:37
 */
@Data
@Entity
@Table(name = "cache_table")
public class CacheTable {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 缓存内容
     */
    @Basic
    @Column(name = "content", columnDefinition = "varchar(255) default null comment '缓存内容'")
    private String content;
}
