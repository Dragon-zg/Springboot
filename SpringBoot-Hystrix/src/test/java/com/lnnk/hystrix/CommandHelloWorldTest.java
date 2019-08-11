package com.lnnk.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import org.junit.Test;
import rx.Observable;
import rx.Observer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.Assert.assertEquals;

/**
 * @author lnnk
 * @date 2019/8/11 16:00
 **/
public class CommandHelloWorldTest {
    @Test
    public void testSync() {
        HystrixCommandGroupKey hystrixCommandGroupKey = HystrixCommandGroupKey.Factory.asKey("ExampleGroup");
        CommandHelloWorld command = new CommandHelloWorld(hystrixCommandGroupKey, "World");
        String result = command.execute();
        assertEquals("Hello, World", result);
    }

    @Test
    public void testAsync() throws ExecutionException, InterruptedException {
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("ExampleGroup");
        assertEquals("Hello, Jack", new CommandHelloWorld(groupKey, "Jack").queue().get());
        assertEquals("Hello, Rose", new CommandHelloWorld(groupKey, "Rose").queue().get());

        CommandHelloWorld command = new CommandHelloWorld(groupKey, "Cheng");
        Future<String> future = command.queue();
        String result = future.get();
        assertEquals("Hello, Cheng", result);

        //  blocking
        Observable<String> observable = new CommandHelloWorld(groupKey, "Lucy").observe();
        assertEquals("Hello, Lucy", observable.toBlocking().single());

        //  non-blocking
        Observable<String> observable2 = new CommandHelloWorld(groupKey, "Jerry").observe();
        observable2.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext: " + s);
            }
        });
    }

    @Test
    public void testFail() throws ExecutionException, InterruptedException {
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("Group2");
        assertEquals("Failure, Alice", new CommandHelloWorld(groupKey, "Alice").execute());
        assertEquals("Failure, Alice", new CommandHelloWorld(groupKey, "Alice").queue().get());
    }

    @Test
    public void testProp() {
        HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory.asKey("Group3");

        HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties.Setter()
                .withCoreSize(10)
                .withMaximumSize(10);

        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter()
                .withCircuitBreakerEnabled(true)
                .withExecutionTimeoutInMilliseconds(100);

        HystrixCommand.Setter setter = HystrixCommand.Setter.withGroupKey(groupKey);
        setter.andThreadPoolPropertiesDefaults(threadPoolProperties);
        setter.andCommandPropertiesDefaults(commandProperties);

        assertEquals("Hello, Cheng", new CommandHelloWorld(setter, "Cheng").execute());

    }
}
