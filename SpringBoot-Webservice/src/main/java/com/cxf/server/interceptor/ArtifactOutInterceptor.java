package com.cxf.server.interceptor;

import org.apache.commons.io.IOUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 在流关闭之前拦截器
 *
 * @author wangqiang
 * @date 2019/1/10 18:30
 */
public class ArtifactOutInterceptor extends AbstractPhaseInterceptor<Message> {
    private final static Logger logger = LoggerFactory.getLogger(ArtifactOutInterceptor.class);

    public ArtifactOutInterceptor() {
        super(Phase.PRE_STREAM);
        logger.debug("SOAP服务器端方法在流关闭之前");
    }

    @Override
    public void handleMessage(Message message) {
        try {
            OutputStream os = message.getContent(OutputStream.class);
            CachedStream cs = new CachedStream();
            message.setContent(OutputStream.class, cs);
            message.getInterceptorChain().doIntercept(message);
            CachedOutputStream csnew = (CachedOutputStream) message.getContent(OutputStream.class);
            InputStream in = csnew.getInputStream();

            String result = IOUtils.toString(in, "UTF-8");
            logger.info("返回给客户端值：" + result);
            // 这里可以对result做处理,如可以对result进行加密,把密文返回给客户端处理完后同理,写回流中
            IOUtils.copy(new ByteArrayInputStream(result.getBytes("UTF-8")), os);

            cs.close();
            os.flush();
            message.setContent(OutputStream.class, os);
        } catch (Exception e) {
            logger.error("GatewayOutInterceptor异常", e);
        }
    }

    private class CachedStream extends CachedOutputStream {
        public CachedStream() {
            super();
        }

        @Override
        protected void doFlush() throws IOException {
            currentStream.flush();
        }

        @Override
        protected void doClose() throws IOException {
        }

        @Override
        protected void onWrite() throws IOException {
        }

    }

}