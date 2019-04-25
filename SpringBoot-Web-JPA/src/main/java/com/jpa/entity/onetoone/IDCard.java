package com.jpa.entity.onetoone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jpa.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

/**
 * @Description: 身份证实体
 * @Date: 2018-06-10 20:25
 * @Author: Deagon-zg
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("身份证实体")
@Entity
@Table(name = "jpa_idcard")
public class IDCard extends BaseEntity {

    @Basic
    @Column(name = "cardno", columnDefinition = "varchar(18) not null comment '身份证号码'")
    @ApiModelProperty("身份证号码")
    private String cardno;

    @JsonIgnore
    @OneToOne(mappedBy = "idcard", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Person person;
}
