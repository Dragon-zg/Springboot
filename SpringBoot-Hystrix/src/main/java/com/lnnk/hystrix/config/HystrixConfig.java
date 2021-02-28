package com.lnnk.hystrix.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.aop.aspectj.HystrixCommandAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lnnk
 * @date 2019/8/11 16:16
 **/
@Configuration
public class HystrixConfig {

    /**
     * AspectJ aspect to process methods which annotated with {@link HystrixCommand} annotation.
     * <p>
     * {@link HystrixCommand} annotation used to specify some methods which should be processes as hystrix commands.
     * 用来拦截处理HystrixCommand注解
     */
    @Bean
    public HystrixCommandAspect hystrixCommandAspect() {
        return new HystrixCommandAspect();
    }
}
