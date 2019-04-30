package com.web.config;

import com.web.constants.EnvConstant;
import com.web.i18n.I18nMessageResource;
import com.web.interceptor.IpInterceptor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import java.util.Locale;

/**
 * MVC基础配置
 *
 * @author Dragon-zg
 * @date 2018/8/13 16:53
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public MvcConfig(Environment environment) {
        this.environment = environment;
    }

    private final Environment environment;

    /**
     * 静态资源处理
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        //静态资源可访问
        configurer.enable();
    }

    /**
     * 拦截器配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (environment.acceptsProfiles(Profiles.of(EnvConstant.DEVELOPMENT, EnvConstant.PRODUCTION))) {
            //IP访问限制拦截器
            registry.addInterceptor(new IpInterceptor(environment)).addPathPatterns("/api/**");
        }
    }

    /**
     * 打印请求相关日志信息
     */
    @Profile({EnvConstant.DEVELOPMENT, EnvConstant.PRODUCTION})
    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        //是否打印请求参数
        filter.setIncludeQueryString(true);
        //是否打印client、session、user
        filter.setIncludeClientInfo(false);
        //是否打印headers
        filter.setIncludeHeaders(false);
        //先判断request的content的长度，如果超过设置maxPayload的长度，则按照maxPayload进行截取，如果出现异常，则payload=[unknown]
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setAfterMessagePrefix("REQUEST : ");
        //指明该bean运行环境
        filter.setEnvironment(environment);
        return filter;
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
