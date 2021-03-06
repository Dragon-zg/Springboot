package com.lnnk.jpa.model.entity.listener;

import com.lnnk.jpa.model.entity.base.BaseEntity;
import com.lnnk.web.util.DateUtils;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * 实体基类监听器,自动设置基类属性
 *
 * @author Lnnk
 * @date 2018/8/10 17:02
 */
public class PersistenceListener {

    @PrePersist
    public void prePersist(BaseEntity baseEntity) {
        Date now = DateUtils.getNowDate();
        if (baseEntity.getCreateTime() == null) {
            baseEntity.setCreateTime(now);
        }
        if (baseEntity.getUpdateTime() == null) {
            baseEntity.setUpdateTime(now);
        }
        if (baseEntity.getDeleteFlag() == null) {
            baseEntity.setDeleteFlag(false);
        }
    }


    @PreUpdate
    public void preUpdate(BaseEntity baseEntity) {
        baseEntity.setUpdateTime(DateUtils.getNowDate());
        if (baseEntity.getDeleteFlag() == null) {
            baseEntity.setDeleteFlag(false);
        }
    }
}
