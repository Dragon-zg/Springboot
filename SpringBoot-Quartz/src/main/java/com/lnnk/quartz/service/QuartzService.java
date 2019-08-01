package com.lnnk.quartz.service;

import com.lnnk.quartz.model.vo.QuartzVO;

import java.util.List;

/**
 * @author lnnk
 * @date 2019/7/18 16:45
 **/
public interface QuartzService {

    /**
     * 获取定时任务列表
     *
     * @return java.util.List<QuartzVO>
     */
    List<QuartzVO> getScheduleList();

    /**
     * 创建定时任务 定时任务创建之后默认启动状态
     *
     * @param quartzVO 定时任务信息类
     */
    void createScheduleJob(QuartzVO quartzVO);

    /**
     * 运行定时任务
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
     * @param quartzVO 定时任务信息类
     */
    void updateScheduleJobCron(QuartzVO quartzVO);

    /**
     * 删除定时任务
     *
     * @param quartzVO 定时任务信息类
     */
    void deleteScheduleJob(QuartzVO quartzVO);

    /**
     * 启动所有定时任务
     *
     * @author lnnk
     * @date 2019/7/24 10:47
     */
    void resumeAllJobs();

    /**
     * 暂停所有定时任务
     *
     * @author lnnk
     * @date 2019/7/24 10:48
     */
    void pauseAllJobs();
}
