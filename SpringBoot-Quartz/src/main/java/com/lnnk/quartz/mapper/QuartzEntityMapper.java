package com.lnnk.quartz.mapper;

import com.lnnk.quartz.model.entity.QuartzEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lnnk
 * @date 2019/7/18 15:48
 **/
@Repository
public interface QuartzEntityMapper {

    /**
     * 获取定时任务列表
     *
     * @return java.util.List<com.lnnk.quartz.model.entity.QuartzEntity>
     */
    List<QuartzEntity> getJobList();
}
