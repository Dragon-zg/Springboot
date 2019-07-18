package com.lnnk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Lnnk
 * @date 2019/6/12 16:01
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.lnnk.quartz.mapper")
public class QuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }
}
