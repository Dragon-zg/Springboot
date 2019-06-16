package com.lnnk.jpa.model.entity.base;

import com.lnnk.jpa.model.entity.listener.PersistenceListener;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @description: 所有实体的父类
 * @date: 2018-06-03 18:22
 * @author: Lnnk
 */
@Getter
@Setter
@ApiModel("实体基础属性类")
@MappedSuperclass
@EntityListeners({PersistenceListener.class})
public class BaseEntity implements Serializable {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "datetime default current_timestamp comment '创建时间'")
    @ApiModelProperty("创建时间")
    protected Date createTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", columnDefinition = "datetime default null comment '更新时间'")
    @ApiModelProperty("更新时间")
    protected Date updateTime;

    @Basic
    @Column(name = "delete_flag", columnDefinition = "tinyint(1) not null comment '删除标识符'")
    @ApiModelProperty("删除标识符")
    protected Boolean deleteFlag;
}
