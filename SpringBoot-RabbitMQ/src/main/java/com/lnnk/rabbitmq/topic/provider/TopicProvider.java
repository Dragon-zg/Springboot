package com.lnnk.rabbitmq.topic.provider;

import com.lnnk.rabbitmq.model.support.AmqpMessage;
import com.lnnk.rabbitmq.topic.config.TopicConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author lnnk
 * @date 2019/8/2 8:53
 **/
@Service
public class TopicProvider {

    private final RabbitTemplate rabbitTemplate;

    public TopicProvider(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendQueue1(AmqpMessage amqpMessage) {
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE, TopicConfig.TOPIC_KEY_1, amqpMessage);
    }

    public void sendQueue2(AmqpMessage amqpMessage) {
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE, TopicConfig.TOPIC_KEY_3, amqpMessage);
    }
}
