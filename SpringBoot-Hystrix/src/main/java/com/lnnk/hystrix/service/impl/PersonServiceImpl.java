package com.lnnk.hystrix.service.impl;

import cn.hutool.json.JSONUtil;
import com.lnnk.hystrix.model.entity.Person;
import com.lnnk.hystrix.service.PersonService;
import com.lnnk.web.enums.ExceptionCode;
import com.lnnk.web.exception.CustomizedException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author wangqiang
 * @date 2019/8/11 16:43
 **/
@Log4j2
@Service
public class PersonServiceImpl implements PersonService {

    @HystrixCommand(groupKey = "hystrixSemaphoreTestGroupKey", commandKey = "hystrixSemaphoreTestCommandKey",
            fallbackMethod = "fallbackMethodSemaphore",
            commandProperties = {
                    //指定多久超时，单位毫秒。超时进fallback
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
                    //判断熔断的最少请求数，默认是10；只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    //判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "80"),
                    //熔断多少毫秒后开始尝试请求 默认5000ms
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
                    // 最大并发请求数，默认10，该参数当使用ExecutionIsolationStrategy.SEMAPHORE策略时才有效。
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "10"),
                    // 如果并发数达到该设置值，请求会被拒绝和抛出异常并且fallback不会被调用。默认10
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "100")
            }
    )
    @Override
    public Person semaphore(String arg) {
        Person person = new Person();
        person.setAge(18);
        person.setId(2L);
        person.setName("名称semaphore");
        person.setAddress("地址semaphore");
        log.debug(JSONUtil.toJsonStr(person));
        return person;
    }

    public Person fallbackMethodSemaphore(String arg, Throwable throwable) {
        log.info("熔断降级, arg: {}, throwable: {}", arg, throwable);
        throw new CustomizedException(ExceptionCode.MELT_DOWNGRADE);
    }

    @HystrixCommand(groupKey = "hystrixThreadTestGroupKey", commandKey = "hystrixThreadTestCommandKey",
            fallbackMethod = "fallbackMethodThread",
            commandProperties = {
                    //指定多久超时，单位毫秒。超时进fallback
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
                    //判断熔断的最少请求数，默认是10；只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    //判断熔断的阈值，默认值50，表示在一个统计窗口内有50%的请求处理失败，会触发熔断
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "90"),
                    //熔断多少毫秒后开始尝试请求 默认5000ms
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000"),
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    // 如果并发数达到该设置值，请求会被拒绝和抛出异常并且fallback不会被调用。默认10
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "100"),
                    //设置rolling percentile window的时间，默认60000
                    @HystrixProperty(name = "metrics.rollingPercentile.timeInMilliseconds", value = "600000")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "10"),
                    @HystrixProperty(name = "maxQueueSize", value = "100"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
            }

    )
    @Override
    public Person thread(String arg) {
        Person person = new Person();
        person.setAge(18);
        person.setId(2L);
        person.setName("名称thread");
        person.setAddress("地址thread");
        log.debug(JSONUtil.toJsonStr(person));
        return person;
    }

    public Person fallbackMethodThread(String arg) {
        log.info("熔断降级, arg: {}", arg);
        throw new CustomizedException(ExceptionCode.MELT_DOWNGRADE);
    }
}
