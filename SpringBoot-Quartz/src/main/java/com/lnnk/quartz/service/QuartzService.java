package com.lnnk.quartz.service;

import com.lnnk.quartz.model.entity.QuartzEntity;

import java.util.List;

/**
 * @author lnnk
 * @date 2019/7/18 16:45
 **/
public interface QuartzService {

    /**
     * 获取定时任务列表
     *
     * @return java.util.List<com.lnnk.quartz.model.entity.QuartzEntity>
     */
    List<QuartzEntity> getJobList();
}
