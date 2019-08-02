package com.lnnk.rabbitmq.fanout.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fanout模式:扇形交换机是最基本的交换机类型,它所能做的事情非常简单-->广播消息.
 * 扇形交换机会把能接收到的消息全部发送给绑定在自己身上的队列.
 * 因为广播不需要“思考,所以扇形交换机处理消息的速度也是所有的交换机类型里面最快的。
 *
 * @author lnnk
 * @date 2019/8/1 16:24
 **/
@Configuration
public class FanoutConfig {
    public static final String FANOUT_QUEUE_1 = "fanout.queue_1";
    public static final String FANOUT_QUEUE_2 = "fanout.queue_2";
    public static final String FANOUT_EXCHANGE = "fanout.exchange";

    @Bean
    public Queue fanoutQueue1() {
        return new Queue(FANOUT_QUEUE_1);
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue(FANOUT_QUEUE_2);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding fanoutBinding1() {
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding2() {
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
}
