package com.lnnk.netty.client;

import cn.hutool.core.util.IdUtil;
import com.google.protobuf.MessageLite;
import com.lnnk.netty.codec.MessageBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Lnnk
 * @date 2021/2/28 10:06
 **/
public class ClientHandler1 extends SimpleChannelInboundHandler<MessageBuf.JMTransfer> {


    /**
     * Is called for each message of type {@link I}.
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageBuf.JMTransfer msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //当channel就绪后，我们首先通过client发送一个数据。
        System.out.println("通道已建立");
        ctx.writeAndFlush(build());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
            throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.READER_IDLE)) {
                System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "    READER_IDLE");
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "    WRITER_IDLE");
            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "    ALL_IDLE");
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    public MessageLite build() {
        return MessageBuf.JMTransfer.newBuilder()
                .setVersion("1").setDeviceId(IdUtil.fastSimpleUUID()).setContent("client1...").build();
    }

}
