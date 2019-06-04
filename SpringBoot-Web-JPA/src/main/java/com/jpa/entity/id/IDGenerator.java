package com.jpa.entity.id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ID生成表
 *
 * @author gelif
 * @since 2015-5-18
 **/
@Entity
@Table(name = "id_generator")
public class IDGenerator implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "table_name", columnDefinition = "varchar(255) not null comment '表名'")
    private String tableName;

    @JsonIgnore
    @Column(name = "current_value", columnDefinition = "bigint(20) unsigned not null comment '当前值'")
    private Long currentValue;
}
