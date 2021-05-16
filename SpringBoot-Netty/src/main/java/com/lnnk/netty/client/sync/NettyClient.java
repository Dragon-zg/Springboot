package com.lnnk.netty.client.sync;

import cn.hutool.core.util.StrUtil;
import com.lnnk.netty.codec.MessageBuf;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Lnnk
 * @date 2021/2/28 16:53
 **/
public class NettyClient {
    private final static Logger logger = LoggerFactory.getLogger(NettyClient.class);

    /**
     * 服务器IP地址
     */
    private final String serverHost;
    /**
     * 服务器端口
     */
    private final int serverPort;
    /**
     * 请求所使用的报文协议
     */
    private final MessageBuf.JMTransfer protocol;

    public NettyClient(String serverHost, int serverPort, MessageBuf.JMTransfer protocol) {
        this.serverHost = serverHost;
        this.serverPort = serverPort;
        this.protocol = protocol;
    }

    /**
     * 启动并返回协议
     *
     * @return 协议
     * @throws Exception
     */
    public MessageBuf.JMTransfer start() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ClientHandler clientHandler = new ClientHandler(protocol, countDownLatch);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    // 连接超时时间
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    //添加心跳支持
//                                    .addLast(new IdleStateHandler(3, 0, 0, TimeUnit.SECONDS))
                                    .addLast(new ProtobufVarint32FrameDecoder())
                                    .addLast(new ProtobufDecoder(MessageBuf.JMTransfer.getDefaultInstance()))
                                    .addLast(new ProtobufVarint32LengthFieldPrepender())
                                    .addLast(new ProtobufEncoder())
                                    //自定义handler
                                    .addLast(clientHandler);
                        }
                    });
            // 绑定地址和端口,bind是异步操作
            ChannelFuture future = bootstrap.connect(new InetSocketAddress(this.serverHost, this.serverPort)).sync();
            // 5秒后得到返回结果，否则认为响应失败
            if (future.isSuccess()) {
                countDownLatch.await(5, TimeUnit.SECONDS);
                logger.debug("start netty client successful! address: {}", future.channel().localAddress());
            } else {
                throw new RuntimeException(StrUtil.format("connect server error! host: {}, port: {}", this.serverHost, this.serverPort));
            }
        } finally {
            workerGroup.shutdownGracefully();
        }
        return clientHandler.getBackProtocol();
    }
}



