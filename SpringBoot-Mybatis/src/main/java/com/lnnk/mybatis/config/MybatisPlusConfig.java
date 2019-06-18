package com.lnnk.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import com.lnnk.web.constant.EnvConsts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * MybatisPlus配置类
 *
 * @author Lnnk
 * @date 2019-06-16 20:28
 **/
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件物理分页
     */
    @Bean
    public PaginationInterceptor getPaginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * SQL执行效率插件,测试环境
     */
    @Bean
    @Profile({EnvConsts.DEVELOPMENT})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }
}
