package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Dragon-zg
 * @date 2018/8/28 19:44
 */
@SpringBootApplication
@PropertySource("classpath:env/${spring.profiles.active}/application.properties")
public class JpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }
}
