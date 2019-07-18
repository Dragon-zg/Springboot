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
    List<QuartzEntity> getScheduleList();

    /**
     * 创建定时任务 定时任务创建之后默认启动状态
     *
     * @param quartzEntity 定时任务信息类
     */
    void createScheduleJob(QuartzEntity quartzEntity);

    /**
     * 立即运行一次定时任务
     *
     * @param jobName  定时任务名称
     * @param jobGroup 定时任务分组
     */
    void runScheduleJobNow(String jobName, String jobGroup);

    /**
     * 暂停定时任务
     *
     * @param jobName  定时任务名称
     * @param jobGroup 定时任务分组
     */
    void pauseScheduleJob(String jobName, String jobGroup);

    /**
     * 恢复定时任务
     *
     * @param jobName  定时任务名称
     * @param jobGroup 定时任务分组
     */
    void resumeScheduleJob(String jobName, String jobGroup);

    /**
     * 重新设置定时任务cron
     *
     * @param quartzEntity 定时任务信息类
     */
    void resumeScheduleJob(QuartzEntity quartzEntity);

    /**
     * 删除定时任务
     *
     * @param quartzEntity 定时任务信息类
     */
    void deleteScheduleJob(QuartzEntity quartzEntity);
}
