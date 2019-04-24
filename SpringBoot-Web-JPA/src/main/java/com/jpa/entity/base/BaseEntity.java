package com.jpa.entity.base;

import com.jpa.entity.listener.PersistenceListener;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 所有实体的父类
 * @Date: 2018-06-03 18:22
 * @Author: Dragon-zg
 */
@Data
@ApiModel("实体基础属性类")
@MappedSuperclass
@EntityListeners({PersistenceListener.class})
public class BaseEntity implements Serializable {

    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键ID")
    private Long id;

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
