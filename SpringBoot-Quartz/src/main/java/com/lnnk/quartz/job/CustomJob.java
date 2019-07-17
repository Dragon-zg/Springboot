package com.lnnk.quartz.job;

import lombok.extern.log4j.Log4j2;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author lnnk
 * @date 2019/7/17 17:41
 **/
@Log4j2
public class CustomJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("CustomJob!");
    }
}
