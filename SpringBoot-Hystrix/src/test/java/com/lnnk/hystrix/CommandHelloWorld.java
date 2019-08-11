package com.lnnk.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author lnnk
 * @date 2019/8/11 15:59
 **/
public class CommandHelloWorld extends HystrixCommand<String> {
    private String name;

    public CommandHelloWorld(HystrixCommandGroupKey group, String name) {
        super(group);
        this.name = name;
    }

    public CommandHelloWorld(Setter setter, String name) {
        super(setter);
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        if ("Alice".equals(name)) {
            throw new RuntimeException("出错了");
        }
        return "Hello, " + name;
    }

    @Override
    protected String getFallback() {
        return "Failure, " + name;
    }
}
