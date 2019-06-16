package com.lnnk.jpa.model.entity.onetoone;

import com.lnnk.jpa.model.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * @description: 中国公民实体
 * @date: 2018-06-10 20:23
 * @author: Lnnk
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("中国公民实体")
@Entity
@Table(name = "jpa_person")
@Where(clause = "delete_flag = 0")
public class Person extends BaseEntity {

    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键ID")
    protected Long id;

    @Basic
    @Column(name = "name", columnDefinition = "varchar(40) not null comment '公民姓名'")
    @ApiModelProperty("公民姓名")
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idcard_id", referencedColumnName = "id", columnDefinition = "int(11) comment '身份证ID 外键'", unique = true, nullable = false)
    private IDCard idcard;
}