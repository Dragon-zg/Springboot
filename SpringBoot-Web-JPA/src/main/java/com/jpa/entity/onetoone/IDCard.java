package com.jpa.entity.onetoone;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jpa.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;

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
@Where(clause = "delete_flag = 0")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class IDCard extends BaseEntity {

    @Basic
    @Column(name = "cardno", columnDefinition = "varchar(18) not null comment '身份证号码'")
    @ApiModelProperty("身份证号码")
    private String cardno;
}
