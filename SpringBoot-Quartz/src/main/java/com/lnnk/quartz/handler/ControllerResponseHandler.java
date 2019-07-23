package com.lnnk.quartz.handler;

import com.lnnk.quartz.model.support.ResponseBack;
import com.lnnk.quartz.util.ResponseUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Controller advice for comment result.
 *
 * @author Lnnk
 * @date 2019/6/10 17:10
 **/
@Log4j2
@RestControllerAdvice({"com.lnnk"})
public class ControllerResponseHandler implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> converterType) {
        return AbstractJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter,
                                  MediaType mediaType, Class<? extends HttpMessageConverter<?>> converterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        MappingJacksonValue container = getOrCreateContainer(body);
        // The contain body will never be null
        beforeBodyWriteInternal(container, mediaType, methodParameter, request, response);
        return container;
    }

    /**
     * Wrap the body in a {@link MappingJacksonValue} value container (for providing
     * additional serialization instructions) or simply cast it if already wrapped.
     */
    private MappingJacksonValue getOrCreateContainer(Object body) {
        return (body instanceof MappingJacksonValue ? (MappingJacksonValue) body : new MappingJacksonValue(body));
    }

    private void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType mediaType,
                                         MethodParameter methodParameter,
                                         ServerHttpRequest request, ServerHttpResponse response) {
        // Get return body
        Object returnBody = bodyContainer.getValue();

        if (returnBody instanceof ResponseBack) {
            // If the return body is instance of ResultModel
            response.setStatusCode(HttpStatus.OK);
            return;
        }

        // Wrap the return body
        ResponseBack<?> resultModel = ResponseUtils.success(returnBody);
        bodyContainer.setValue(resultModel);
        response.setStatusCode(HttpStatus.OK);
    }
}
