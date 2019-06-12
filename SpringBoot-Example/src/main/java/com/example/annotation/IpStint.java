package com.example.annotation;

import org.springframework.context.annotation.Configuration;

import java.lang.annotation.*;

/**
 * IP访问拦截注解类,当没有配置时,默认使用properties中的配置信息
 *
 * @author Dragon-zg
 * @date 2018/10/25 14:21
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Configuration
public @interface IpStint {
    /**
     * 访问ip白名单
     */
    String[] allowIp() default {};

    /**
     * 访问ip黑名单
     */
    String[] denyIp() default {};
}
