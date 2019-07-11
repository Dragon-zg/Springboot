package com.lnnk.web.support.validator.annotation;

import com.lnnk.web.support.validator.LimitedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义校验,指定范围内注解
 *
 * @author Lnnk
 * @date 2019/7/11 13:37
 */

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {LimitedValidator.class})
public @interface Limited {
    String message() default "当前值不在指定值之内";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 指定的可使用的值
     */
    String[] value();
}
