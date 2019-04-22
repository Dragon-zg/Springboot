package com.jpa.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * @Description:
 * @Date: 2017-12-16 19:53
 */
@Data
@Entity
@Table(name = "girl", schema = "test")
public class Girl {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键ID")
    private Integer id;

    @Basic
    @Column(name = "cup_size")
    @ApiModelProperty("")
    private String cupSize;

    @Basic
    @Column(name = "age")
    @ApiModelProperty("年纪")
    private Integer age;
}
