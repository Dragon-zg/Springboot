package com.lnnk.mybatis;

import com.lnnk.mybatis.mapper.UserMapper;
import com.lnnk.mybatis.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * @author wangqiang
 * @date 2019/6/18 13:11
 **/
@RunWith(SpringRunner.class)
@SpringBootTest("spring.profiles.active=development")
public class MybatisApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insertTest() {
        User user = new User();
        user.setName("first");
        user.setAge(10);
        user.setEmail("first@baomidou.com");
        user.setCreateTime(LocalDateTime.now());
        int rows = userMapper.insert(user);
        System.out.println("影响记录数：" + rows);
        System.out.println("主键：" + user.getId());
    }
}
