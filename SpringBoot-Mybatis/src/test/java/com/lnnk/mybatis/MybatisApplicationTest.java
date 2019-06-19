package com.lnnk.mybatis;

import com.lnnk.mybatis.model.entity.User;
import com.lnnk.mybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author wangqiang
 * @date 2019/6/18 13:11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest("spring.profiles.active=development")
public class MybatisApplicationTest {

    @Autowired
    private UserService userService;

    @Test
    public void insertTest() {
        User user = new User();
        user.setName("first");
        user.setAge(10);
        user.setEmail("first@baomidou.com");
        user.setCreateTime(LocalDateTime.now());
        boolean flag = userService.save(user);
        System.out.println("是否成功：" + flag);
        System.out.println("主键：" + user.getId());
    }

    @Test
    public void batchInsertTest() {
        User first = new User();
        first.setName("first");
        first.setAge(10);
        first.setEmail("first@baomidou.com");
        first.setCreateTime(LocalDateTime.now());

        User second = new User();
        second.setName("second");
        second.setAge(20);
        second.setEmail("second@baomidou.com");
        second.setCreateTime(LocalDateTime.now());
        boolean flag = userService.saveBatch(Arrays.asList(first, second));
        System.out.println("是否成功：" + flag);
        System.out.println("first主键：" + first.getId());
        System.out.println("second主键：" + second.getId());
    }
}
