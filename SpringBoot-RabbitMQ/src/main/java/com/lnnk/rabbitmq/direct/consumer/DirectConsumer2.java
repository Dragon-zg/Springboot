package com.lnnk.rabbitmq.direct.consumer;

import cn.hutool.json.JSONUtil;
import com.lnnk.rabbitmq.direct.config.DirectConfig;
import com.lnnk.rabbitmq.model.support.AmqpMessage;
import com.rabbitmq.client.Channel;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 队列2的消费者
 * 手动ACK模式(配置文件spring.rabbitmq.listener.simple.acknowledge-mode=manual)
 *
 * @author lnnk
 * @date 2019/8/1 17:30
 **/
@Log4j2
@Component
@RabbitListener(queues = DirectConfig.DIRECT_QUEUE_2)
public class DirectConsumer2 {

    private AtomicInteger flag = new AtomicInteger(0);

    @RabbitHandler
    public void handler(AmqpMessage amqpMessage, Channel channel, Message message) {
        try {
            log.info("队列名字: {}, 消费内容: {}, flag: {}", DirectConfig.DIRECT_QUEUE_2, JSONUtil.toJsonStr(amqpMessage), flag.get());
            // prefetchCount限制每个消费者在收到下一个确认回执前一次可以最大接受多少条消息,通过basic.qos方法设置prefetch_count=1,这样RabbitMQ就会使得每个Consumer在同一个时间点最多处理一个Message
            channel.basicQos(1);
            // deliveryTag：消息传送的次数,发布的每一条消息都会获得一个唯一的deliveryTag，(任何channel上发布的第一条消息的deliveryTag为1，此后的每一条消息都会加1)，deliveryTag在channel范围内是唯一的
            // multiple：批量确认标志。如果值为true，则执行批量确认，此deliveryTag之前收到的消息全部进行确认; 如果值为false，则只对当前收到的消息进行确认
            if (flag.incrementAndGet() < 5) {
                // 拒绝当前消息，并把消息返回原队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            } else {
                // 确认消息已经消费成功
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        } catch (Exception e) {
            log.error("队列2的消费者异常", e);
        }
    }
}
