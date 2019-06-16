package com.lnnk.jpa.model.entity.manttomany;

import com.lnnk.jpa.model.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 学生实体
 *
 * @author Lnnk
 * @date 2019-03-13 22:59
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("学生实体")
@Entity
@Table(name = "jpa_student")
@Where(clause = "delete_flag = 0")
public class Student extends BaseEntity {

    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键ID")
    private Integer id;

    @Basic
    @Column(name = "name", columnDefinition = "varchar(40) not null comment '学生姓名'")
    @ApiModelProperty("学生姓名")
    private String name;
}
