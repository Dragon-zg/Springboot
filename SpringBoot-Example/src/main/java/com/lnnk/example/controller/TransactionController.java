package com.lnnk.example.controller;


import com.lnnk.example.exception.RollbackException;
import com.lnnk.example.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 1.PROPAGATION_REQUIRED – 支持当前事务,如果当前没有事务,就新建一个事务.这是最常见的选择.
 * <p>
 * 2.PROPAGATION_SUPPORTS – 支持当前事务,如果当前没有事务,就以非事务方式执行.
 * <p>
 * 3.PROPAGATION_MANDATORY – 支持当前事务,如果当前没有事务,就抛出异常.
 * <p>
 * 4.PROPAGATION_REQUIRES_NEW – 新建事务,如果当前存在事务,把当前事务挂起.
 * <p>
 * 5.PROPAGATION_NOT_SUPPORTED – 以非事务方式执行操作,如果当前存在事务,就把当前事务挂起.
 * <p>
 * 6.PROPAGATION_NEVER – 以非事务方式执行,如果当前存在事务,则抛出异常.
 * <p>
 * 7.PROPAGATION_NESTED – 如果当前存在事务,则在嵌套事务内执行.如果当前没有事务,则进行与PROPAGATION_REQUIRED类似的操作.<br/>
 * 注意: jpa Hibernate不支持PROPAGATION_NESTED
 * (1)如果子事务回滚，会发生什么？
 * 父事务会回滚到进入子事务前建立的save point，然后尝试其他的事务或者其他的业务逻辑，父事务之前的操作不会受到影响，更不会自动回滚
 * (2)如果父事务回滚，会发生什么？
 * 父事务回滚，子事务也会跟着回滚！为什么呢，因为父事务结束之前，子事务是不会提交的，我们说子事务是父事务的一部分，正是这个道理。
 *
 * @author Lnnk
 * @date 2019/7/15 14:00
 **/
@Api(tags = "事务传播性演示接口")
@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

    private final ProductService productService;

    public TransactionController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation("Propagation.REQUIRED,该方法事务不会回滚")
    @PostMapping("/invokeInsertThenRollback1")
    public void invokeInsertThenRollback1() throws RollbackException {
        productService.invokeInsertThenRollback1();
    }

    @ApiOperation("方式一: Propagation.REQUIRED,该方法事务会回滚")
    @PostMapping("/invokeInsertThenRollback2")
    public void invokeInsertThenRollback2() throws RollbackException {
        productService.invokeInsertThenRollback2();
    }

    @ApiOperation("方式二: Propagation.REQUIRED,该方法事务会回滚")
    @PostMapping("/invokeInsertThenRollback3")
    public void invokeInsertThenRollback3() throws RollbackException {
        productService.invokeInsertThenRollback3();
    }

    @ApiOperation("Propagation.REQUIRES_NEW")
    @PostMapping("/requiresNew")
    public void requiresNew() throws RollbackException {
        productService.requiresNew();
    }
}
