package com.lnnk.rabbitmq.deadletter.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 死信队列配置
 *
 * @author lnnk
 * @date 2019/8/3 14:40
 **/
@Configuration
public class DeadLetterConfig {

    public static final String DL_EXCHANGE = "DL_EXCHANGE";
    public static final String DL_KEY = "DL_KEY";
    public static final String DL_QUEUE = "DL_QUEUE";
    public static final String REDIRECT_QUEUE = "REDIRECT_QUEUE";
    public static final String REDIRECT_KEY = "REDIRECT_KEY";

    /**
     * 死信队列 交换机标识符
     */
    private static final String DEAD_LETTER_QUEUE_KEY = "x-dead-letter-exchange";

    /**
     * 死信队列交换机绑定键标识符
     */
    private static final String DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";


    /**
     * 死信队列跟交换机类型没有关系 不一定为directExchange  不影响该类型交换机的特性.
     */
    @Bean
    public Exchange deadLetterExchange() {
        return ExchangeBuilder.directExchange(DL_EXCHANGE).build();
    }

    /**
     * 声明一个死信队列.
     * x-dead-letter-exchange   对应  死信交换机
     * x-dead-letter-routing-key  对应 死信队列
     * x-message-ttl 设置消息的过期时间
     */
    @Bean
    public Queue deadLetterQueue() {
        Map<String, Object> args = new HashMap<>(2);
        // x-dead-letter-exchange 声明  死信交换机
        args.put(DEAD_LETTER_QUEUE_KEY, DL_EXCHANGE);
        // x-dead-letter-routing-key  声明 死信路由键
        args.put(DEAD_LETTER_ROUTING_KEY, REDIRECT_KEY);
        // 设置消息的过期时间,单位是毫秒
        args.put("x-message-ttl", 5000);
        return QueueBuilder.durable(DL_QUEUE).withArguments(args).build();
    }

    /**
     * 定义死信队列转发队列.
     */
    @Bean
    public Queue redirectQueue() {
        return QueueBuilder.durable(REDIRECT_QUEUE).build();
    }

    /**
     * 死信路由通过 DL_KEY 绑定键绑定到死信队列上.
     */
    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DL_KEY).noargs();

    }

    /**
     * 死信路由通过 REDIRECT_KEY 绑定键绑定到死信队列上.
     *
     * @return the binding
     */
    @Bean
    public Binding redirectBinding() {
        return BindingBuilder.bind(redirectQueue()).to(deadLetterExchange()).with(REDIRECT_KEY).noargs();
    }
}
