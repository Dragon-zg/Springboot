package com.lnnk.netty.server;

import com.lnnk.netty.codec.MessageBuf;
import com.lnnk.netty.entity.NettyTable;
import com.lnnk.netty.service.NettyTableService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Lnnk
 * @date 2021/2/28 18:38
 **/
@Component
public class NettyHandler extends SimpleChannelInboundHandler<MessageBuf.JMTransfer> {
    private final static Logger logger = LoggerFactory.getLogger(NettyHandler.class);


    private final NettyTableService nettyTableService;

    public NettyHandler(NettyTableService nettyTableService) {
        this.nettyTableService = nettyTableService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageBuf.JMTransfer msg) throws Exception {
        if (null != msg) {
            logger.debug("version: {}, deviceId: {}", msg.getVersion(), msg.getDeviceId());
            NettyTable nettyTable = new NettyTable();
            nettyTable.setContent(msg.getContent());
            nettyTableService.save(nettyTable);
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        // 通道注册
        System.out.println("server --------------> channelRegistered");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        //通道取消注册
        System.out.println("server --------------> channelUnregistered");
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //通道处于活动状态
        System.out.println("server --------------> channelActive");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //通道不活跃状态
        System.out.println("server --------------> channelInactive");
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //读取完成
        System.out.println("server --------------> channelReadComplete");
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 用户事件触发器
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state().equals(IdleState.READER_IDLE)) {
                System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "    No data was received for a while.");
            } else if (event.state().equals(IdleState.WRITER_IDLE)) {
                System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "    No data was sent for a while.");
            } else if (event.state().equals(IdleState.ALL_IDLE)) {
                System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "    No data was either received or sent for a while.");
            }
        }
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 异常捕获
        System.out.println("server --------------> exceptionCaught");
        logger.error("异常信息", cause);
        ctx.channel().close();
    }
}
