package com.lnnk.rabbitmq.topic.provider;

import com.lnnk.rabbitmq.model.support.Message;
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

    public void sendQueue1(Message message) {
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE, TopicConfig.TOPIC_KEY_1, message);
    }

    public void sendQueue2(Message message) {
        rabbitTemplate.convertAndSend(TopicConfig.TOPIC_EXCHANGE, TopicConfig.TOPIC_KEY_3, message);
    }
}
