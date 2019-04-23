package com.jpa.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class AbstractEntity implements Serializable {

    @Basic
    @Column(name = "create_user", columnDefinition = "varchar(20) not null comment '创建人'")
    @ApiModelProperty("创建人")
    protected String createUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "datetime default current_timestamp comment '创建时间'")
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date createTime;

    @Basic
    @Column(name = "update_user", columnDefinition = "varchar(20) default null comment '更新人'")
    @ApiModelProperty("更新人")
    protected String updateUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", columnDefinition = "datetime default null comment '更新时间'")
    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date updateTime;

    @Basic
    @Column(name = "delete_flag", columnDefinition = "tinyint(1) not null comment '删除标识符'")
    @ApiModelProperty("删除标识符")
    protected Boolean deleteFlag;
}
