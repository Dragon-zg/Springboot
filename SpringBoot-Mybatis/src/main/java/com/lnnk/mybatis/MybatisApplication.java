package com.lnnk.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * MybatisApplication
 *
 * @author Lnnk
 * @date 2019/6/13 16:52
 */
@SpringBootApplication
@MapperScan("com.lnnk.mybatis.mapper")
@PropertySource("classpath:env/${spring.profiles.active}/application.properties")
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }
}
