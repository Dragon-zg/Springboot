package com.lnnk.rabbitmq.topic.consumer;

import cn.hutool.json.JSONUtil;
import com.lnnk.rabbitmq.model.support.Message;
import com.lnnk.rabbitmq.topic.config.TopicConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lnnk
 * @date 2019/8/2 11:36
 **/
@Log4j2
@Component
@RabbitListener(queues = TopicConfig.TOPIC_QUEUE_1)
public class TopicConsumer1 {

    @RabbitHandler
    public void handler(Message message) {
        log.info("队列名字: {}, 消费内容: {}", TopicConfig.TOPIC_QUEUE_1, JSONUtil.toJsonStr(message));
    }
}
