package com.lnnk.netty.server;

import com.lnnk.netty.codec.MessageBuf;
import com.lnnk.netty.service.NettyTableService;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

import java.util.concurrent.TimeUnit;

/**
 * @author Lnnk
 * @date 2021/2/28 16:54
 **/
public class NettyInitialzer extends ChannelInitializer<SocketChannel> {

    private final NettyTableService nettyTableService;

    public NettyInitialzer(NettyTableService nettyTableService) {
        this.nettyTableService = nettyTableService;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                // 心跳检测
                .addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS))
                //设置超时时间
                .addLast(new ReadTimeoutHandler(60))
                .addLast(new ProtobufVarint32FrameDecoder())
                //使用protobuf数据转换
                .addLast(new ProtobufDecoder(MessageBuf.JMTransfer.getDefaultInstance()))
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new ProtobufEncoder())
                //自定义handler
                .addLast(new NettyHandler(nettyTableService));


    }
}
