package com.lnnk.rabbitmq.headers.config;

import com.google.common.collect.Maps;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * headers模式:首部交换机是忽略routing_key的一种路由方式.
 * 路由器和交换机路由的规则是通过Headers信息来交换的,这个有点像HTTP的Headers.
 * 将一个交换机声明成首部交换机，绑定一个队列的时候,定义一个Hash的数据结构,消息发送的时候,
 * 会携带一组hash数据结构的信息，当Hash的内容匹配上的时候，消息就会被写入队列.
 * <p>
 * 绑定交换机和队列的时候,Hash结构中要求携带一个键"x-match"这个键的Value可以是any或者all,
 * 这代表消息携带的Hash是需要全部匹配(all),还是仅匹配一个键(any)就可以了.
 * 相比直连交换机,首部交换机的优势是匹配的规则不被限定为字符串(string).
 * <p>
 * whereAll:当消息生产者传到Exchange中的headers中的键值对所有都符合(所有Map或所有Key)要求时，才启用该队列。
 * whereAny:只要消息生产者传到Exchange中的headers中的键值对有至少一个(至少一个Map或至少一个Key)符合要求时，就启用该队列。
 *
 * @author lnnk
 * @date 2019/8/1 15:35
 **/
@Configuration
public class HeadersConfig {
    public static final String HEADERS_QUEUE_1 = "headers.queue_1";
    public static final String HEADERS_EXCHANGE = "headers.exchange";
    public static final String HEADERS_QUEUE_2 = "headers.queue_2";

    @Bean
    public Queue headersQueue1() {
        return new Queue(HEADERS_QUEUE_1);
    }

    @Bean
    public Queue headersQueue2() {
        return new Queue(HEADERS_QUEUE_2);
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(HEADERS_EXCHANGE);
    }

    @Bean
    public Binding headersBinding1() {
        return BindingBuilder.bind(headersQueue1()).to(headersExchange()).whereAll(getHeaders()).match();
    }

    @Bean
    public Binding headersBinding2() {
        return BindingBuilder.bind(headersQueue2()).to(headersExchange()).whereAny(getHeaders()).match();
    }

    private Map<String, Object> getHeaders() {
        Map<String, Object> headerValues = Maps.newHashMapWithExpectedSize(2);
        headerValues.put("type", "cash");
        headerValues.put("aging", "fast");
        return headerValues;
    }
}
