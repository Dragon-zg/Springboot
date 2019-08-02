package com.lnnk.rabbitmq.headers.provider;

import com.lnnk.rabbitmq.headers.config.HeadersConfig;
import com.lnnk.rabbitmq.model.support.AmqpMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author lnnk
 * @date 2019/8/2 8:53
 **/
@Service
public class HeadersProvider {

    private final RabbitTemplate rabbitTemplate;

    public HeadersProvider(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    private Message getMessage(Map<String, Object> headers, Object msg) {
        MessageProperties messageProperties = new MessageProperties();
        for (Map.Entry<String, Object> entry : headers.entrySet()) {
            messageProperties.setHeader(entry.getKey(), entry.getValue());
        }
        MessageConverter messageConverter = new SimpleMessageConverter();
        return messageConverter.toMessage(msg, messageProperties);
    }

    public void sendQueue1(Map<String, Object> headers, AmqpMessage amqpMessage) {
        rabbitTemplate.convertAndSend(HeadersConfig.HEADERS_EXCHANGE_1, StringUtils.EMPTY, getMessage(headers, amqpMessage));
    }

    public void sendQueue2(Map<String, Object> headers, AmqpMessage amqpMessage) {
        rabbitTemplate.convertAndSend(HeadersConfig.HEADERS_EXCHANGE_1, StringUtils.EMPTY, getMessage(headers, amqpMessage));
    }
}
