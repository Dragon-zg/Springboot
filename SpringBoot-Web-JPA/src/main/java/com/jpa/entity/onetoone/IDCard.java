package com.jpa.entity.onetoone;

import com.jpa.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * @Description: 身份证实体
 * @Date: 2018-06-10 20:25
 * @Author: Deagon-zg
 */
@Data
@Builder
@ToString(callSuper = true)
@ApiModel("身份证实体")
@Entity
@Table(name = "jpa_idcard")
public class IDCard extends BaseEntity {

    @Basic
    @Column(name = "cardno", columnDefinition = "varchar(18) not null comment '身份证号码'")
    @ApiModelProperty("身份证号码")
    private String cardno;

    @OneToOne(mappedBy = "idcard", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Person person;
}
