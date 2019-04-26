package com.jpa.entity.listener;

import com.jpa.entity.base.BaseEntity;
import com.web.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * 实体基类监听器,自动设置基类属性
 * @author Dragon-zg
 * @date 2018/8/10 17:02
 */
public class PersistenceListener {
    private final static Logger logger = LoggerFactory.getLogger(PersistenceListener.class);

    @PrePersist
    public void prePersist(BaseEntity baseEntity) {
        Date now = DateUtil.getNowDate();
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
        baseEntity.setUpdateTime(DateUtil.getNowDate());
        if (baseEntity.getDeleteFlag() == null) {
            baseEntity.setDeleteFlag(false);
        }
    }
}
