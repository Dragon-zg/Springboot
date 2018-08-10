package com.jpa.model.listener;

import com.common.utils.DateUtil;
import com.jpa.model.base.AbstractEntity;
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
    public void prePersist(AbstractEntity abstractEntity) {
        Date now = DateUtil.getNowDate();
        if (abstractEntity.getCreateTime() == null) {
            abstractEntity.setCreateTime(now);
        }
        if (abstractEntity.getUpdateTime() == null) {
            abstractEntity.setUpdateTime(now);
        }
        if (abstractEntity.getDeleteFlag() == null) {
            abstractEntity.setDeleteFlag(false);
        }
    }


    @PreUpdate
    public void preUpdate(AbstractEntity abstractEntity) {
        if (abstractEntity.getUpdateTime() == null) {
            abstractEntity.setUpdateTime(DateUtil.getNowDate());
        }
        if (abstractEntity.getDeleteFlag() == null) {
            abstractEntity.setDeleteFlag(false);
        }
    }
}
