package com.web.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.web.i18n.I18nMessageResource;
import com.web.utils.Profiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.SortHandlerMethodArgumentResolver;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import javax.servlet.MultipartConfigElement;
import java.util.List;
import java.util.Locale;

/**
 * MVC基础配置
 *
 * @author Dragon-zg
 * @date 2018/8/13 16:53
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    private final static Logger logger = LoggerFactory.getLogger(MvcConfig.class);

    public MvcConfig(Environment environment) {
        this.environment = environment;
    }

    private final Environment environment;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        logger.info("addArgumentResolvers: sortArgumentResolver, pageableArgumentResolver");
        SortHandlerMethodArgumentResolver sortArgumentResolver = new SortHandlerMethodArgumentResolver();
        sortArgumentResolver.setPropertyDelimiter(",");
        argumentResolvers.add(sortArgumentResolver);
        PageableHandlerMethodArgumentResolver pageableArgumentResolver = new PageableHandlerMethodArgumentResolver(sortArgumentResolver);
        pageableArgumentResolver.setPageParameterName("page");
        pageableArgumentResolver.setSizeParameterName("size");
        pageableArgumentResolver.setOneIndexedParameters(true);
        argumentResolvers.add(pageableArgumentResolver);
        super.addArgumentResolvers(argumentResolvers);
    }

    /**
     * 处理返回的json
     */
    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        return new Jackson2ObjectMapperBuilder()
                //遇到未知属性不做处理
                .failOnUnknownProperties(false)
                //清除EMPTY的字段
                .serializationInclusion(JsonInclude.Include.NON_EMPTY);
                //分页json处理
//                .serializerByType(ResultModel.class, new JsonPageSerializer());
    }

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
        if (environment.acceptsProfiles(Profiles.DEVELOPMENT, Profiles.PRODUCTION)) {
            //添加拦截器
        }
    }

    /**
     * 打印请求相关日志信息
     */
    @Profile({Profiles.DEVELOPMENT})
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
        //指明该bean运行环境
        filter.setEnvironment(environment);
        return filter;
    }

    /**
     * 跨域CORS配置<br/>
     * 或者使用@CrossOrigin(origins = "*")注解
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        registry.addMapping("/cors/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*");
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

    /**
     * 上传文件解析器使用
     */
    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }

    /**
     * 上传文件相关配置信息
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件上传目录
        //factory.setLocation();
        //最大支持文件大小,不设置大小时使用-1L
        factory.setMaxFileSize("50MB");
        //最大支持请求大小,不设置大小时使用-1L
        factory.setMaxRequestSize("50MB");
        // 设置缓存大小
        factory.setFileSizeThreshold(0);
        return factory.createMultipartConfig();
    }
}
