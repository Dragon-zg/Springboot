package com.lnnk.rabbitmq.topic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * topic模式:主题交换机,发送到主题交换机上的消息需要携带指定规则的routing_key,主题交换机会根据这个规则将数据发送到对应的(多个)队列上.
 * 主题交换机的routing_key需要有一定的规则:
 * 1.*表示一个单词
 * 2.#表示任意数量（零个或多个）单词
 *
 * @author lnnk
 * @date 2019/8/1 16:30
 **/
@Configuration
public class TopicConfig {
    public static final String TOPIC_QUEUE_1 = "topic.queue_1";
    public static final String TOPIC_KEY_1 = "topic.key.message";
    public static final String TOPIC_QUEUE_2 = "topic.queue_2";
    private static final String TOPIC_KEY_2 = "topic.key.#";
    public static final String TOPIC_KEY_3 = "topic.key.msg";
    public static final String TOPIC_EXCHANGE = "topic.exchange";

    @Bean
    public Queue topicQueue1() {
        return new Queue(TOPIC_QUEUE_1);
    }

    @Bean
    public Queue topicQueue2() {
        return new Queue(TOPIC_QUEUE_2);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding topicBinding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(TOPIC_KEY_1);
    }

    @Bean
    public Binding topicBinding2() {
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(TOPIC_KEY_2);
    }
}
