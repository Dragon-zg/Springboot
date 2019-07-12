package com.lnnk.example.support.validator;

import com.lnnk.example.model.param.CustomParam;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author Lnnk
 * @date 2019/7/11 17:15
 **/
@Log4j2
public class CustomValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomParam.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (null == target) {
            errors.rejectValue("customParam", "customParam", "自定义参数不能为空");
        }
        CustomParam customParam = (CustomParam) target;

        String userName = customParam.getUserName();
        if (StringUtils.isNotBlank(userName) && userName.length() > 20) {
            errors.rejectValue("userName", "customParam.userName.tooLong", "用户名不能超过{20}个字符");
        } else {
            errors.rejectValue("userName", "customParam.userName.required", "用户名不能为空");
        }

        String password = customParam.getPassword();
        if (StringUtils.isNotBlank(password)) {
            if (password.length() < 6) {
                errors.rejectValue("password", "customParam.password.tooShort", "密码太短,不能少于{6}个字符");
            }
        } else {
            errors.rejectValue("password", "customParam.password.required", "密码不能为空");
        }

        String email = customParam.getEmail();
        if (StringUtils.isNotBlank(email) && email.indexOf("@") == -1) {
            errors.rejectValue("email", "customParam.email.invalidEmail", "邮箱格式错误");
        } else {
            errors.rejectValue("email", "customParam.email.required", "邮箱不能为空");
        }
    }
}
