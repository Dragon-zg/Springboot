package com.lnnk.quartz.config;

import com.lnnk.quartz.job.CustomJob;
import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author wangqiang
 * @date 2019/7/17 19:17
 **/
@Log4j2
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class JobInitRunner implements CommandLineRunner {

    private final JobProperties jobProperties;

    private final Scheduler scheduler;

    public JobInitRunner(JobProperties jobProperties, Scheduler scheduler) {
        this.jobProperties = jobProperties;
        this.scheduler = scheduler;
    }

    @Override
    public void run(String... args) throws Exception {
//        buildCustomJob();
        log.info("run.... ");
    }


    /**
     * customJob
     */
    public void buildCustomJob() throws SchedulerException {
        //任务名称
        String name = CustomJob.class.getSimpleName();
        //任务所属分组
        String group = CustomJob.class.getName();
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(CustomJob.class).withIdentity(name, group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group)
                .withSchedule(CronScheduleBuilder.cronSchedule(jobProperties.getCustomJobCron())).build();
        //将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
