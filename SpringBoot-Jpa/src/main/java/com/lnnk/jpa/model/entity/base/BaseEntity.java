package com.lnnk.jpa.model.entity.base;

import com.lnnk.jpa.model.entity.listener.PersistenceListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 实体基础属性类
 * @date 2018-06-03 18:22
 * @author Lnnk
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners({PersistenceListener.class})
public class BaseEntity implements Serializable {

    /**
     * 创建时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", columnDefinition = "datetime default current_timestamp comment '创建时间'")
    protected Date createTime;

    /**
     * 更新时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", columnDefinition = "datetime default null comment '更新时间'")
    protected Date updateTime;

    /**
     * 删除标识符 0.未删除 1.已删除
     */
    @Basic
    @Column(name = "delete_flag", columnDefinition = "tinyint(1) not null comment '删除标识符'")
    protected Boolean deleteFlag;
}
