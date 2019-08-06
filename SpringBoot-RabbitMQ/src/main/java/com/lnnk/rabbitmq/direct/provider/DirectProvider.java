package com.lnnk.rabbitmq.direct.provider;

import com.lnnk.rabbitmq.direct.config.DirectConfig;
import com.lnnk.rabbitmq.model.support.AmqpMessage;
import com.lnnk.web.util.UUIDUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
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
        rabbitTemplate.convertAndSend(DirectConfig.DIRECT_EXCHANGE, DirectConfig.DIRECT_KEY_1, message, new CorrelationData(UUIDUtils.getUUID()));
    }

    public void sendQueue2(AmqpMessage amqpMessage) {
        rabbitTemplate.convertAndSend(DirectConfig.DIRECT_EXCHANGE, DirectConfig.DIRECT_KEY_2, amqpMessage);
    }

    public void returnCallback() {
        rabbitTemplate.convertAndSend("", DirectConfig.DIRECT_KEY_2, "");
    }
}
