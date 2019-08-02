package com.lnnk.rabbitmq.direct.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * direct模式:直连交换机是一种带路由功能的交换机,一个队列会和一个交换机绑定，除此之外再绑定一个routing_key,
 * 当消息被发送的时候,需要指定一个binding_key,这个消息被送达交换机的时候，就会被这个交换机送到指定的队列里面去.
 * 同样的一个binding_key也是支持应用到多个队列中的.
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
