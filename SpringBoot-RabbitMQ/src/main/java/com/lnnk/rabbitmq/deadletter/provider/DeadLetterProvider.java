package com.lnnk.rabbitmq.deadletter.provider;

import com.lnnk.rabbitmq.deadletter.config.DeadLetterConfig;
import com.lnnk.rabbitmq.model.support.AmqpMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author lnnk
 * @date 2019/8/5 13:23
 **/
@Service
public class DeadLetterProvider {

    private final RabbitTemplate rabbitTemplate;

    public DeadLetterProvider(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void deadLetter(AmqpMessage message) {
        rabbitTemplate.convertAndSend(DeadLetterConfig.DL_EXCHANGE, DeadLetterConfig.DL_KEY, message);
    }
}
