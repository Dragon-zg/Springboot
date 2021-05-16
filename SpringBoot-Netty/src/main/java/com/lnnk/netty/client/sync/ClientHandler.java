package com.lnnk.netty.client.sync;

import com.lnnk.netty.codec.MessageBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * @author Lnnk
 * @date 2021/2/28 18:38
 **/
public class ClientHandler extends SimpleChannelInboundHandler<MessageBuf.JMTransfer> {
    private final static Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    /**
     * 请求所使用的报文协议
     */
    private MessageBuf.JMTransfer protocol;

    private CountDownLatch countDownLatch;

    private MessageBuf.JMTransfer backProtocol;

    public ClientHandler(MessageBuf.JMTransfer protocol, CountDownLatch countDownLatch) {
        this.protocol = protocol;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 通道激活时, 发出协议报文
        ctx.channel().writeAndFlush(this.protocol);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageBuf.JMTransfer protocol) throws Exception {
        this.backProtocol = protocol;
        countDownLatch.countDown();//消息收取完毕后释放同步锁 计数器减去1
        //处理完结果后关闭连接
        ctx.channel().eventLoop().shutdownGracefully();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 异常捕获
        logger.error("remoteAddress: {}, client exception", ctx.channel().remoteAddress(), cause);
        ctx.channel().eventLoop().shutdownGracefully();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            // 3秒内未收到消息时，尝试重发送报文消息
            if (IdleState.READER_IDLE.equals(state)) {
                // 通道激活时, 发出协议报文
                ctx.channel().writeAndFlush(this.protocol);
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    public MessageBuf.JMTransfer getBackProtocol() {
        return backProtocol;
    }

    public void setBackProtocol(MessageBuf.JMTransfer backProtocol) {
        this.backProtocol = backProtocol;
    }
}
