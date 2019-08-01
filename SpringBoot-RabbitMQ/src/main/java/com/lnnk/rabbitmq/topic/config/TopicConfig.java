package com.lnnk.rabbitmq.topic.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * topic模式
 *
 * @author lnnk
 * @date 2019/8/1 16:30
 **/
@Configuration
public class TopicConfig {
    static final String TOPIC_QUEUE_1 = "topic.queue_1";
    static final String TOPIC_KEY_1 = "topic.key.message";
    static final String TOPIC_QUEUE_2 = "topic.queue_2";
    static final String TOPIC_KEY_2 = "topic.key.#";
    static final String TOPIC_EXCHANGE = "topic.exchange";

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
