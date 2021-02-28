package com.lnnk.netty.server;

import com.lnnk.netty.config.NettyConfig;
import com.lnnk.netty.service.NettyTableService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Lnnk
 * @date 2021/2/28 16:53
 **/
@Component
public class NettyServer {
    private final static Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private static final int BIZ_GROUP_SIZE = 2;
    private static final int BIZ_THREAD_SIZE = 20;

    private final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZ_GROUP_SIZE);
    private final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZ_THREAD_SIZE);

    private final NettyConfig nettyConfig;

    private final NettyTableService nettyTableService;

    public NettyServer(NettyConfig nettyConfig, NettyTableService nettyTableService) {
        this.nettyConfig = nettyConfig;
        this.nettyTableService = nettyTableService;
    }

    public void start() {
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // 日志级别 INFO
                    .handler(new LoggingHandler(LogLevel.INFO))
                    //SO_KEEPALIVE=true的时候,服务端可以探测客户端的连接是否还存活着,如果客户端因为断电或者网络问题或者客户端挂掉了等,那么服务端的连接可以关闭掉,释放资源
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new NettyInitialzer(nettyTableService));
            bootstrap.bind(nettyConfig.getInetHost(), nettyConfig.getInetPort())
                    // 同步返回
                    .sync()
                    // 当通道关闭时继续向后执行,这是一个阻塞方法
                    .channel().closeFuture().sync();
        } catch (Exception e) {
            logger.error("Server start fail!", e);
        }

    }

    public void shutdown() {
        logger.info("shutdown tcp server ...");
        // 释放线程池资源
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        logger.info("shutdown tcp server end.");
    }
}



