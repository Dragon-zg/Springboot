package com.jpa.entity.base;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 所有实体的父类
 * @Date: 2018-06-03 18:22
 * @Author: Dragon-zg
 */
@ApiModel("实体基础属性类")
@MappedSuperclass
public class AbstractEntity implements Serializable {

    @ApiModelProperty("创建人")
    protected String createUser;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date createTime;

    @ApiModelProperty("更新人")
    protected String updateUser;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date updateTime;

    @ApiModelProperty("删除标识符")
    protected Boolean deleteFlag;

    @Basic
    @Column(name = "create_user", columnDefinition = "varchar(20) not null comment '创建人'")
    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "datetime default current_timestamp comment '创建时间'")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_user", columnDefinition = "varchar(20) default null comment '更新人'")
    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", columnDefinition = "datetime default null comment '更新时间'")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "delete_flag", columnDefinition = "tinyint(1) not null comment '删除标识符'")
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
