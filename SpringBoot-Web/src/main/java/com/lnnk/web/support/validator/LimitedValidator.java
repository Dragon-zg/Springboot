package com.lnnk.web.support.validator;

import com.google.common.collect.Lists;
import com.lnnk.web.support.validator.annotation.Limited;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.Serializable;
import java.util.List;

/**
 * 自定义校验,指定范围内校验器
 *
 * @author Lnnk
 * @date 2019/7/11 13:35
 **/
public class LimitedValidator implements ConstraintValidator<Limited, Serializable> {

    private List<String> limits;

    @Override
    public void initialize(Limited limited) {
        limits = Lists.newArrayList(limited.value());
    }

    @Override
    public boolean isValid(Serializable value, ConstraintValidatorContext context) {
        if (null != value && limits.contains(String.valueOf(value))) {
            return true;
        }
        return false;
    }
}
