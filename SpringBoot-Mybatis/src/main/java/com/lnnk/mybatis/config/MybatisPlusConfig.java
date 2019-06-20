package com.lnnk.mybatis.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
