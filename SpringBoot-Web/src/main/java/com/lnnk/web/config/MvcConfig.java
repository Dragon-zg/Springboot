package com.lnnk.web.config;

import com.lnnk.web.support.UniversalEnumConverterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC基础配置
 *
 * @author Lnnk
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
     * 打印请求相关日志信息
     */
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
     * Add {@link org.springframework.core.convert.converter.Converter Converters}
     * and {@link org.springframework.format.Formatter Formatters} in addition to the ones
     * registered by default.
     *
     * @param registry 注册器
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 添加枚举全局转换工厂
        registry.addConverterFactory(new UniversalEnumConverterFactory());
    }
}
