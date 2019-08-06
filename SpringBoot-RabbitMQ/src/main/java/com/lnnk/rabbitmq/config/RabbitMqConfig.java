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
     * 序列化采用Jackson, 传输实体就可以不用实现序列化
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
        rabbitTemplate.setMandatory(true);
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

    /**
     * 全局配置ACK
     */
    /*@Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory, DirectConfig directConfig) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        // 设置ACK处理的队列
        container.setQueues(directConfig.directQueue1(), directConfig.directQueue2());
        //开启ACK  手动确认机制
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //将channel暴露给listener才能手动确认,AcknowledgeMode.MANUAL时必须为ture
        container.setExposeListenerChannel(true);
        //消费者的最大数量,并发消费的时候需要设置,且>=concurrentConsumers
        container.setMaxConcurrentConsumers(10);
        //消费者的最小数量
        container.setConcurrentConsumers(10);
        //在单个请求中处理的消息个数，他应该大于等于事务数量
        container.setPrefetchCount(1);
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            try {
                // prefetchCount限制每个消费者在收到下一个确认回执前一次可以最大接受多少条消息,通过basic.qos方法设置prefetch_count=1,这样RabbitMQ就会使得每个Consumer在同一个时间点最多处理一个Message
                channel.basicQos(1);
                log.info("ACK消费端接收到消息:" + message.getMessageProperties() + ":" + new String(message.getBody()));
                log.info("当前使用路由key:" + message.getMessageProperties().getReceivedRoutingKey());
                // deliveryTag：消息传送的次数,发布的每一条消息都会获得一个唯一的deliveryTag，(任何channel上发布的第一条消息的deliveryTag为1，此后的每一条消息都会加1)，deliveryTag在channel范围内是唯一的
                // multiple：批量确认标志。如果值为true，则执行批量确认，此deliveryTag之前收到的消息全部进行确认; 如果值为false，则只对当前收到的消息进行确认
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (Exception e) {
                e.printStackTrace();
                if (message.getMessageProperties().getRedelivered()) {
                    log.info("消息已重复处理失败,拒绝再次接收...");
                    // deliveryTag：消息传送的次数,发布的每一条消息都会获得一个唯一的deliveryTag，deliveryTag在channel范围内是唯一的
                    // multiple：批量确认标志。如果值为true，包含本条消息在内的、所有比该消息deliveryTag值小的 消息都被拒绝了（除了已经被 ack 的以外）;如果值为false，只拒绝三本条消息
                    // requeue：如果值为true，则重新放入RabbitMQ的发送队列，如果值为false，则通知RabbitMQ销毁这条消息
                    channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
                } else {
                    log.info("消息即将再次返回队列处理...");
                    // deliveryTag：消息传送的次数,发布的每一条消息都会获得一个唯一的deliveryTag，deliveryTag在channel范围内是唯一的
                    // requeue：如果值为true，则重新放入RabbitMQ的发送队列，如果值为false，则通知RabbitMQ销毁这条消息
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                }
            }
        });
        return container;
    }*/
}
