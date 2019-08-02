package com.lnnk.rabbitmq.direct.consumer;

import cn.hutool.json.JSONUtil;
import com.lnnk.rabbitmq.direct.config.DirectConfig;
import com.lnnk.rabbitmq.model.support.AmqpMessage;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 队列2的消费者
 *
 * @author lnnk
 * @date 2019/8/1 17:30
 **/
@Log4j2
@Component
@RabbitListener(queues = DirectConfig.DIRECT_QUEUE_2)
public class DirectConsumer2 {

    @RabbitHandler
    public void handler(AmqpMessage amqpMessage) {
        log.info("队列名字: {}, 消费内容: {}", DirectConfig.DIRECT_QUEUE_2, JSONUtil.toJsonStr(amqpMessage));
    }
}
