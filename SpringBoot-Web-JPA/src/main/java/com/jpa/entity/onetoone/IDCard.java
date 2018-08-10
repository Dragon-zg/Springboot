package com.jpa.entity.onetoone;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * @Description: 身份证实体
 * @Date: 2018-06-10 20:25
 * @Author: Deagon-zg
 */
@ApiModel("身份证实体")
@Entity
@Table(name = "jpa_idcard", schema = "test")
public class IDCard {
    @ApiModelProperty("主键ID")
    private Integer id;

    @ApiModelProperty("身份证号码")
    private String cardno;

    private Person person;

    @OneToOne(mappedBy = "idcard", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cardno", columnDefinition = "varchar(18) not null comment '身份证号码'")
    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }
}
