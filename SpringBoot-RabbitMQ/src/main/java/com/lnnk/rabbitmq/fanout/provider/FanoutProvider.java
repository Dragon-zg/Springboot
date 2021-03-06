package com.lnnk.rabbitmq.fanout.provider;

import com.lnnk.rabbitmq.fanout.config.FanoutConfig;
import com.lnnk.rabbitmq.model.support.AmqpMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author lnnk
 * @date 2019/8/2 8:53
 **/
@Service
public class FanoutProvider {

    private final RabbitTemplate rabbitTemplate;

    public FanoutProvider(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendQueue(AmqpMessage amqpMessage) {
        rabbitTemplate.convertAndSend(FanoutConfig.FANOUT_EXCHANGE, StringUtils.EMPTY, amqpMessage);
    }
}
