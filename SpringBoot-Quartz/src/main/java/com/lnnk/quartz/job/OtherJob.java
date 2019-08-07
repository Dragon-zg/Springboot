package com.lnnk.quartz.job;

import lombok.extern.log4j.Log4j2;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author lnnk
 * @date 2019/7/17 17:41
 **/
@Log4j2
@DisallowConcurrentExecution
public class OtherJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("OtherJob! @DisallowConcurrentExecution注解后不会并发执行, 当前执行完成后才会执行下次的定时任务!");
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            log.info("OtherJob END!");
        }
    }
}
