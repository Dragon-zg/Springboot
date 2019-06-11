package com.jpa.model.entity.unidirectional.onetoone;

import com.jpa.model.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @description: 身份证实体
 * @date: 2018-06-10 20:25
 * @author: Dragon-zg
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("身份证实体")
@Entity
@Table(name = "jpa_idcard")
@Where(clause = "delete_flag = 0")
public class IDCard extends BaseEntity {

    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键ID")
    protected Long id;

    @Basic
    @Column(name = "cardno", columnDefinition = "varchar(18) not null comment '身份证号码'")
    @ApiModelProperty("身份证号码")
    private String cardno;
}
