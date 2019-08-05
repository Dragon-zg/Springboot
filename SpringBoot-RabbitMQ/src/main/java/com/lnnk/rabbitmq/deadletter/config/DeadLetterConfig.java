package com.lnnk.rabbitmq.deadletter.config;

import com.lnnk.rabbitmq.direct.config.DirectConfig;
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
    private static final String DL_QUEUE = "DL_QUEUE";

    /**
     * 死信队列 交换机标识符
     */
    private static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";

    /**
     * 死信队列交换机绑定键标识符
     */
    private static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";

    /**
     * 设置消息的过期时间,单位是毫秒
     */
    private static final String X_MESSAGE_TTL = "x-message-ttl";


    /**
     * 死信队列跟交换机类型没有关系 不一定为directExchange  不影响该类型交换机的特性.
     */
    @Bean
    public Exchange deadLetterExchange() {
        return ExchangeBuilder.directExchange(DL_EXCHANGE).build();
    }

    /**
     * 声明一个死信队列.
     * x-dead-letter-exchange   声明死信后转发的交换机
     * x-dead-letter-routing-key  DirectConfig
     * x-message-ttl 设置消息的过期时间
     */
    @Bean
    public Queue deadLetterQueue() {
        Map<String, Object> args = new HashMap<>(2);
        // x-dead-letter-exchange 声明死信后转发的交换机
        args.put(X_DEAD_LETTER_EXCHANGE, DirectConfig.DIRECT_EXCHANGE);
        // x-dead-letter-routing-key  声明死信后转发的路由键
        args.put(X_DEAD_LETTER_ROUTING_KEY, DirectConfig.DIRECT_KEY_1);
        // 设置消息的过期时间,单位是毫秒
        args.put(X_MESSAGE_TTL, 5000);
        return QueueBuilder.durable(DL_QUEUE).withArguments(args).build();
    }

    /**
     * 死信路由通过 DL_KEY 绑定键绑定到死信队列上.
     */
    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DL_KEY).noargs();

    }
}
