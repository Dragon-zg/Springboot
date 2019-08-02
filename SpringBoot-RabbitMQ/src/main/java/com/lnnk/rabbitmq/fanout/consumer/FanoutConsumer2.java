package com.lnnk.rabbitmq.fanout.consumer;

import cn.hutool.json.JSONUtil;
import com.lnnk.rabbitmq.fanout.config.FanoutConfig;
import com.lnnk.rabbitmq.model.support.Message;
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
@RabbitListener(queues = FanoutConfig.FANOUT_QUEUE_2)
public class FanoutConsumer2 {
    @RabbitHandler
    public void handler(Message message) {
        log.info("队列名字: {}, 消费内容: {}", FanoutConfig.FANOUT_QUEUE_2, JSONUtil.toJsonStr(message));
    }
}
