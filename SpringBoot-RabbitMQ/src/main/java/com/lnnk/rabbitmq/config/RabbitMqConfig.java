package com.lnnk.rabbitmq.config;

import cn.hutool.json.JSONUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * RabbitMQ配置
 *
 * @author lnnk
 * @date 2019/8/5 17:18
 **/
@Log4j2
@Configuration
public class RabbitMqConfig {

    /**
     * 序列化采用Jackson
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        // 设置RabbitTemplate的Mandatory属性为true
        rabbitTemplate.setMandatory(connectionFactory.isPublisherReturns());
        // 为RabbitTemplate设置ReturnCallback(发送失败返回)
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.warn("replyCode(回复代码): {}, replyText(回复描述): {}, exchange(交换机): {}, routingKey(路由键): {}, properties(属性): {}, body(内容): {}",
                    replyCode, replyText, exchange, routingKey, JSONUtil.toJsonStr(message.getMessageProperties()), new String(message.getBody(), StandardCharsets.UTF_8)
            );
        });
        // 为RabbitTemplate设置ConfirmCallback(发送确认)
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (correlationData != null) {
                //当correlationData不为空时打印日志,否则没有太大意义
                log.info("correlationData(消息): {}, ack(确认结果): {}, cause(失败原因): {}", JSONUtil.toJsonStr(correlationData), ack, cause);
            }
        });

        return rabbitTemplate;
    }


}
