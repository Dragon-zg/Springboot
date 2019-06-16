package com.lnnk.example.config;

import com.lnnk.example.i18n.I18nMessageResource;
import com.lnnk.example.interceptor.IpInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

/**
 * MVC基础配置
 *
 * @author Lnnk
 * @date 2018/8/13 16:53
 */
@Configuration
public class ExampleMvcConfig implements WebMvcConfigurer {

    public ExampleMvcConfig(Environment environment) {
        this.environment = environment;
    }

    private final Environment environment;

    /**
     * 拦截器配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //IP访问限制拦截器
        registry.addInterceptor(new IpInterceptor(environment)).addPathPatterns("/api/**");
    }

    /**
     * 自定义多语言处理实现类
     */
    @Bean
    public MessageSource messageSource() {
        MessageSource messageSource = new I18nMessageResource();
        return messageSource;
    }

    /**
     * 多语言使用的转换器,此处使用cookie<br/>
     * AcceptHeaderLocaleResolver:其实没有任何具体实现，是通过浏览器头部的语言信息来进行多语言选择<br/>
     * FixedLocaleResolver:设置固定的语言信息，这样整个系统的语言是一成不变的，用处不大<br/>
     * CookieLocaleResolver:将语言信息设置到Cookie中，这样整个系统就可以获得语言信息<br/>
     * SessionLocaleResolver：与CookieLocaleResolver类似将语言信息放到Session中，这样整个系统就可以从Session中获得语言信息
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.CHINESE);
        localeResolver.setCookieName("language");
        return localeResolver;
    }
}
