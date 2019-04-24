package com.cxf.server.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description 请求方法后置拦截器
 * @Author Dragon-zg
 * @Date 2018/6/5 9:28
 **/
public class PostInvokeInterceptor extends AbstractPhaseInterceptor<Message> {
    private final static Logger logger = LoggerFactory.getLogger(PostInvokeInterceptor.class);

    public PostInvokeInterceptor() {
        //调用之后
        super(Phase.POST_INVOKE);
        logger.debug("SOAP服务器端方法后置拦截器初始化");
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        logger.debug("后置处理!");
    }
}
