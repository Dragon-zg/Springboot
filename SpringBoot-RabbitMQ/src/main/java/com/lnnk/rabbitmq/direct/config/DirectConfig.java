package com.lnnk.rabbitmq.direct.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * direct模式, 路由键与队列名完全匹配
 * 消息中的路由键(routing key)如果和Binding中的binding key一致, 交换器就将消息发到对应的队列中。
 *
 * @author lnnk
 * @date 2019/8/1 15:35
 **/
@Configuration
public class DirectConfig {
    public static final String DIRECT_QUEUE_1 = "direct.queue_1";
    public static final String DIRECT_QUEUE_2 = "direct.queue_2";
    public static final String DIRECT_KEY_1 = "direct.routingKey_1";
    public static final String DIRECT_KEY_2 = "direct.routingKey_2";
    public static final String DIRECT_EXCHANGE = "direct.exchange";

    @Bean
    public Queue directQueue1() {
        return new Queue(DIRECT_QUEUE_1);
    }

    @Bean
    public Queue directQueue2() {
        return new Queue(DIRECT_QUEUE_2);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    @Bean
    public Binding directBinding1() {
        return BindingBuilder.bind(directQueue1()).to(directExchange()).with(DIRECT_KEY_1);
    }

    @Bean
    public Binding directBinding2() {
        return BindingBuilder.bind(directQueue2()).to(directExchange()).with(DIRECT_KEY_2);
    }
}
