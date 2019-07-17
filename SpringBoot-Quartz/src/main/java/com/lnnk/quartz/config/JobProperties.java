package com.lnnk.quartz.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangqiang
 * @date 2019/7/17 19:09
 **/
@Data
@Configuration
@ConfigurationProperties("job.cron")
public class JobProperties {

    private String customJobCron;
}
