package com.lnnk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Lnnk
 * @date 2019/6/12 16:01
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EntityScan(basePackages = {"com.lnnk.example.model.entity"})
@EnableJpaRepositories(basePackages = "com.lnnk.example.repository")
public class ExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleApplication.class, args);
    }
}
