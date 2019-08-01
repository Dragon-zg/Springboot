package com.lnnk;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RabbitMqApplication
 *
 * @author lnnk
 * @date 2019/7/29 9:37
 **/
@SpringBootApplication
@EnableRabbit
public class RabbitMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqApplication.class, args);
    }
}
