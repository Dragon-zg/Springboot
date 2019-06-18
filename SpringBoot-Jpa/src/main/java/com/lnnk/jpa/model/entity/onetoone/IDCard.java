package com.lnnk.jpa.model.entity.onetoone;

import com.lnnk.jpa.model.entity.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * 身份证实体
 * @date 2018-06-10 20:25
 * @author Lnnk
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "jpa_idcard")
@Where(clause = "delete_flag = 0")
public class IDCard extends BaseEntity {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    /**
     * 身份证号码
     */
    @Basic
    @Column(name = "cardno", columnDefinition = "varchar(18) not null comment '身份证号码'")
    private String cardno;
}
