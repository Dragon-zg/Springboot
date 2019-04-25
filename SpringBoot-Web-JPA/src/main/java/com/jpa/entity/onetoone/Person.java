package com.jpa.entity.onetoone;

import com.jpa.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

/**
 * @Description: 中国公民实体
 * @Date: 2018-06-10 20:23
 * @Author: Deagon-zg
 */
@Getter
@Builder(toBuilder=true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel("中国公民实体")
@Entity
@Table(name = "jpa_person")
public class Person extends BaseEntity {

    @Basic
    @Column(name = "name", columnDefinition = "varchar(40) not null comment '公民姓名'")
    @ApiModelProperty("公民姓名")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idcard_id", referencedColumnName = "id", columnDefinition = "int(11) comment '身份证ID 外键'", unique = true, nullable = false)
    private IDCard idcard;
}