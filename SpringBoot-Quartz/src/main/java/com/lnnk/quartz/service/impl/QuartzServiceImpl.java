package com.lnnk.quartz.service.impl;

import com.lnnk.quartz.mapper.QuartzEntityMapper;
import com.lnnk.quartz.model.vo.QuartzVO;
import com.lnnk.quartz.service.QuartzService;
import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author lnnk
 * @date 2019/7/18 16:44
 **/
@Service
@Log4j2
public class QuartzServiceImpl implements QuartzService {

    private final QuartzEntityMapper quartzEntityMapper;

    private final Scheduler scheduler;

    public QuartzServiceImpl(QuartzEntityMapper quartzEntityMapper, Scheduler scheduler) {
        this.quartzEntityMapper = quartzEntityMapper;
        this.scheduler = scheduler;
    }

    @Override
    public List<QuartzVO> getScheduleList() {
        return quartzEntityMapper.getScheduleList();
    }

    @Override
    public void createScheduleJob(QuartzVO quartzVO) {
        try {
            //获取到定时任务的执行类  必须是类的绝对路径名称
            Class<? extends Job> jobClass = (Class<? extends Job>) Class.forName(quartzVO.getJobClassName());
            // 构建定时任务信息
            JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(quartzVO.getJobName(), quartzVO.getJobGroup()).build();
            // 构建触发器trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(quartzVO.getTriggerName(), quartzVO.getTriggerGroup())
                    .withSchedule(CronScheduleBuilder.cronSchedule(quartzVO.getCronExpression())).build();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (ClassNotFoundException e) {
            log.error("定时任务类路径错误! className: {}", quartzVO.getJobClassName(), e);
        } catch (SchedulerException e) {
            log.error("创建定时任务失败!", e);
        }
    }

    @Override
    public void runScheduleJobNow(String jobName, String jobGroup) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        Assert.notNull(jobKey, "jobKey must is not null!");
        try {
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            log.error("立即运行一次定时任务失败!", e);
        }
    }

    @Override
    public void pauseScheduleJob(String jobName, String jobGroup) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        Assert.notNull(jobKey, "jobKey must is not null!");
        try {
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            log.error("暂停定时任务失败!", e);
        }
    }

    @Override
    public void resumeScheduleJob(String jobName, String jobGroup) {
        JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
        Assert.notNull(jobKey, "jobKey must is not null!");
        try {
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            log.error("恢复定时任务失败!", e);
        }
    }

    @Override
    public void updateScheduleJobCron(QuartzVO quartzVO) {
        //获取到对应任务的触发器
        TriggerKey triggerKey = TriggerKey.triggerKey(quartzVO.getTriggerName(), quartzVO.getTriggerGroup());
        Assert.notNull(triggerKey, "triggerKey must is not null!");
        try {
            //重新构建任务的触发器trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(quartzVO.getCronExpression())).build();
            //重置对应的job
            scheduler.rescheduleJob(triggerKey, trigger);
        } catch (SchedulerException e) {
            log.error("重新设置定时任务失败!", e);
        }
    }

    @Override
    public void deleteScheduleJob(QuartzVO quartzVO) {
        JobKey jobKey = JobKey.jobKey(quartzVO.getJobName(), quartzVO.getJobGroup());
        Assert.notNull(jobKey, "jobKey must is not null!");
        TriggerKey triggerKey = TriggerKey.triggerKey(quartzVO.getJobName());
        try {
            //暂停触发器
            scheduler.pauseTrigger(triggerKey);
            //移除触发器
            scheduler.unscheduleJob(triggerKey);
            //删除任务
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            log.error("删除定时任务失败!", e);
        }
    }

    @Override
    public void resumeAllJobs() {
        try {
            scheduler.resumeAll();
        } catch (SchedulerException e) {
            log.error("恢复所有定时任务失败!", e);
        }
    }

    @Override
    public void pauseAllJobs() {
        try {
            scheduler.pauseAll();
        } catch (SchedulerException e) {
            log.error("暂停所有定时任务失败!", e);
        }

    }
}
