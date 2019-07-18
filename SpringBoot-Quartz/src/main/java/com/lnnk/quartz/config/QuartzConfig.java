package com.lnnk.quartz.config;

import com.lnnk.quartz.job.CustomJob;
import com.lnnk.quartz.job.OtherJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lnnk
 * @date 2019/7/17 19:09
 **/
@Configuration
public class QuartzConfig {

    @Bean("customJobDetail")
    public JobDetail customJobDetail() {
        return JobBuilder.newJob(CustomJob.class).withIdentity("customJobDetail").storeDurably().build();
    }

    @Bean
    public Trigger customTrigger(@Qualifier("customJobDetail") JobDetail jobDetail,
                                 @Value("${job.trigger.cron.customJob}") String customJobCron) {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(customJobCron);
        return TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity("customTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean("otherJobDetail")
    public JobDetail otherJobDetail() {
        return JobBuilder.newJob(OtherJob.class).withIdentity("otherJobDetail").storeDurably().build();
    }

    @Bean
    public Trigger otherTrigger(@Qualifier("otherJobDetail") JobDetail jobDetail,
                                @Value("${job.trigger.cron.otherJob}") String otherJobCron) {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(otherJobCron);
        return TriggerBuilder.newTrigger().forJob(jobDetail)
                .withIdentity("otherTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }
}
