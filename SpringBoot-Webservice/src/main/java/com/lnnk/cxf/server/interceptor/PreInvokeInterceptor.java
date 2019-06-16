package com.lnnk.cxf.server.interceptor;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Context;
import javax.xml.ws.handler.MessageContext;

/**
 * @author Lnnk
 * @description: 请求方法前置拦截器
 */
public class PreInvokeInterceptor extends AbstractPhaseInterceptor<Message> {
    private final static Logger logger = LoggerFactory.getLogger(PreInvokeInterceptor.class);

    @Context
    private MessageContext messageContext;




    public PreInvokeInterceptor() {
        //调用之前拦截soap消息
        super(Phase.PRE_INVOKE);
        logger.debug("SOAP服务器端方法前置拦截器初始化");
    }

    @Override
    public void handleMessage(Message message) throws Fault {
        logger.debug("前置处理!");
    }
}
