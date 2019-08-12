package com.lnnk.hystrix.service.impl;

import cn.hutool.json.JSONUtil;
import com.lnnk.hystrix.model.entity.Person;
import com.lnnk.hystrix.service.PersonService;
import com.lnnk.web.enums.ExceptionCode;
import com.lnnk.web.exception.CustomizedException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 1.commandKey: 配置全局唯一标识服务的名称.比如,库存系统有一个获取库存服务,那么就可以为这个服务起一个名字来唯一识别该服务,如果不配置,则默认是@HystrixCommand注解修饰的函数的函数名.
 * <p>
 * 2.groupKey: 一个比较重要的注解,配置全局唯一标识服务分组的名称,比如,库存系统就是一个服务分组.通过设置分组,Hystrix会根据组来组织和统计命令的告、仪表盘等信息.
 * Hystrix命令默认的线程划分也是根据命令组来实现.默认情况下,Hystrix会让相同组名的命令使用同一个线程池,所以我们需要在创建Hystrix命令时为其指定命令组来实现默认的线程池划分.
 * 此外,Hystrix还提供了通过设置threadPoolKey来对线程池进行设置.建议最好设置该参数,使用threadPoolKey来控制线程池组.
 * <p>
 * 3.threadPoolKey: 对线程池进行设定,细粒度的配置,相当于对单个服务的线程池信息进行设置,也可多个服务设置同一个threadPoolKey构成线程组.
 * <p>
 * 4.fallbackMethod: @HystrixCommand注解修饰的函数的回调函数,@HystrixCommand修饰的函数必须和这个回调函数定义在同一个类中,因为定义在了同一个类中,所以fackback method可以是public/private均可.
 * <p>
 * 5.commandProperties: 配置该命令的一些参数,如executionIsolationStrategy配置执行隔离策略,默认是使用线程隔离,此处我们配置为THREAD,即线程池隔离.
 * {@link  com.netflix.hystrix.HystrixCommandProperties}中各个参数的定义.
 * <p>
 * 6.threadPoolProperties: 线程池相关参数设置,具体可以设置哪些参数请见: {@link com.netflix.hystrix.HystrixThreadPoolProperties}
 * <p>
 * 7.ignoreExceptions: 调用服务时,除了HystrixBadRequestException之外,其他@HystrixCommand修饰的函数抛出的异常均会被Hystrix认为命令执行失败而触发服务降级的处理逻辑（调用fallbackMethod指定的回调函数）,
 * 所以当需要在命令执行中抛出不触发降级的异常时来使用它,通过这个参数指定,哪些异常抛出时不触发降级（不去调用fallbackMethod）,而是将异常向上抛出.
 * <p>
 * 8.observableExecutionMode: 定义hystrix observable command的模式；
 * <p>
 * 9.raiseHystrixExceptions: 任何不可忽略的异常都包含在HystrixRuntimeException中；
 * <p>
 * 10.defaultFallback: 默认的回调函数,该函数的函数体不能有入参,返回值类型与@HystrixCommand修饰的函数体的返回值一致.如果指定了fallbackMethod,则fallbackMethod优先级更高.
 *
 * @author wangqiang
 * @date 2019/8/11 16:43
 **/
@Log4j2
@Service
public class PersonServiceImpl implements PersonService {

    @HystrixCommand(groupKey = "hystrixSemaphoreTestGroupKey", commandKey = "hystrixSemaphoreTestCommandKey",
            fallbackMethod = "fallbackMethodSemaphore",
            commandProperties = {
                    //指定多久超时,单位毫秒.超时进fallback
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
                    //判断熔断的最少请求数,默认是10；只有在一个统计窗口内处理的请求数量达到这个阈值,才会进行熔断与否的判断
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    //判断熔断的阈值,默认值50,表示在一个统计窗口内有50%的请求处理失败,会触发熔断
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "80"),
                    //熔断多少毫秒后开始尝试请求 默认5000ms
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
                    //该属性用来设置执行的隔离策略 SEMAPHONE：通过信号量隔离的策略，在调用线程上执行，并且他的并发限制受信号量计数的限制
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
                    // 最大并发请求数,默认10,该参数当使用ExecutionIsolationStrategy.SEMAPHORE策略时才有效.
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "10"),
                    // 如果并发数达到该设置值,请求会被拒绝和抛出异常并且fallback不会被调用.默认10
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "100")
            }
    )
    @Override
    public Person semaphore(String arg) {
        if (StringUtils.isNotBlank(arg)) {
            throw new RuntimeException("抛出运行时异常~");
        }
        Person person = new Person();
        person.setAge(18);
        person.setId(2L);
        person.setName("名称semaphore");
        person.setAddress("地址semaphore");
        log.debug(JSONUtil.toJsonStr(person));
        return person;
    }

    /**
     * 如果fallback方法的参数和原方法参数个数不一致,则会出现FallbackDefinitionException: fallback method wasn't found
     */
    public Person fallbackMethodSemaphore(String arg, Throwable throwable) {
        log.error("熔断降级, arg: {}, throwable: {}", arg, throwable);
        throw new CustomizedException(ExceptionCode.MELT_DOWNGRADE);
    }

    @HystrixCommand(groupKey = "hystrixThreadTestGroupKey", commandKey = "hystrixThreadTestCommandKey",
            fallbackMethod = "fallbackMethodThread",
            commandProperties = {
                    //指定多久超时,单位毫秒.超时进fallback
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    //判断熔断的最少请求数,默认是10；只有在一个统计窗口内处理的请求数量达到这个阈值,才会进行熔断与否的判断
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    //判断熔断的阈值,默认值50,表示在一个统计窗口内有50%的请求处理失败,会触发熔断
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "90"),
                    //熔断多少毫秒后开始尝试请求 默认5000ms
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000"),
                    //该属性用来设置执行的隔离策略 THREAD：通过线程池隔离的策略，在独立线程上执行，并且他的并发限制受线程池中线程数量的限制（默认）
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    // 如果并发数达到该设置值,请求会被拒绝和抛出异常并且fallback不会被调用.默认10
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "10"),
                    //设置rolling percentile window的时间,默认60000
                    @HystrixProperty(name = "metrics.rollingPercentile.timeInMilliseconds", value = "600000")
            },
            threadPoolProperties = {
                    //该属性用来设置执行命令线程池的核心线程数，该值也就是命令执行的最大并发量，默认值 10
                    @HystrixProperty(name = "coreSize", value = "10"),
                    //该属性用来设置线程池的最大队列大小，当设置为 -1 时，线程池将使用 SynchronousQueue 实现的队列，否则使用 LinkedBlockingQueue 实现的队列，默认值 -1
                    @HystrixProperty(name = "maxQueueSize", value = "100"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    //该属性用来设置线程池统计窗口中使用"桶"的数量，默认值 10
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    //该属性用来设置线程池统计的滚动窗口的持续时间，单位：毫秒，默认值 10000
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
            }

    )
    @Override
    public Person thread(String arg) {
        if (StringUtils.isNotBlank(arg)) {
            throw new RuntimeException("抛出运行时异常~");
        }
        Person person = new Person();
        person.setAge(18);
        person.setId(2L);
        person.setName("名称thread");
        person.setAddress("地址thread");
        log.debug(JSONUtil.toJsonStr(person));
        return person;
    }

    /**
     * 如果fallback方法的参数和原方法参数个数不一致,则会出现FallbackDefinitionException: fallback method wasn't found
     */
    public Person fallbackMethodThread(String arg) {
        log.error("熔断降级, arg: {}", arg);
        throw new CustomizedException(ExceptionCode.MELT_DOWNGRADE);
    }
}
