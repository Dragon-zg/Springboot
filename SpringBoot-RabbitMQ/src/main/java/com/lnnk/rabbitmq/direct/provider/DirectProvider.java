package com.lnnk.rabbitmq.direct.provider;

import com.lnnk.rabbitmq.direct.config.DirectConfig;
import com.lnnk.rabbitmq.model.support.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author lnnk
 * @date 2019/8/2 8:53
 **/
@Service
public class DirectProvider {

    private final RabbitTemplate rabbitTemplate;

    public DirectProvider(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendQueue1(Object message) {
        rabbitTemplate.convertAndSend(DirectConfig.DIRECT_EXCHANGE, DirectConfig.DIRECT_KEY_1, message);
    }

    public void sendQueue2(Message message) {
        rabbitTemplate.convertAndSend(DirectConfig.DIRECT_EXCHANGE, DirectConfig.DIRECT_KEY_2, message);
    }
}
