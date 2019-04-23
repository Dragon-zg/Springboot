package com.jpa.entity.onetoone;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

/**
 * @Description: 身份证实体
 * @Date: 2018-06-10 20:25
 * @Author: Deagon-zg
 */
@Data
@Builder
@ApiModel("身份证实体")
@Entity
@Table(name = "jpa_idcard", schema = "test")
public class IDCard {

    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键ID")
    private Integer id;

    @Basic
    @Column(name = "cardno", columnDefinition = "varchar(18) not null comment '身份证号码'")
    @ApiModelProperty("身份证号码")
    private String cardno;

    @OneToOne(mappedBy = "idcard", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Person person;
}
