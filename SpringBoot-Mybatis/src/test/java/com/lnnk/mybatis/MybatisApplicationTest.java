package com.lnnk.mybatis;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lnnk.mybatis.model.entity.User;
import com.lnnk.mybatis.service.UserService;
import com.lnnk.web.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author lnnk
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
        user.setCreateTime(DateUtils.getNowDate());
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
        first.setCreateTime(DateUtils.getNowDate());

        User second = new User();
        second.setName("second");
        second.setAge(20);
        second.setEmail("second@baomidou.com");
        second.setCreateTime(DateUtils.getNowDate());
        boolean flag = userService.saveBatch(Arrays.asList(first, second));
        System.out.println("是否成功：" + flag);
        System.out.println("first主键：" + first.getId());
        System.out.println("second主键：" + second.getId());
    }

    @Test
    public void getOne() {
        //删除
        userService.lambdaUpdate().eq(User::getAge, 400).remove();
        //新增
        User insert = new User();
        insert.setName("final");
        insert.setAge(400);
        insert.setEmail("final@baomidou.com");
        insert.setCreateTime(DateUtils.getNowDate());
        boolean flag = userService.save(insert);
        if (flag) {
            Wrapper<User> queryWrapper = Wrappers.<User>lambdaQuery()
                    .eq(User::getAge, 400);
            User user = userService.getOne(queryWrapper);
            System.out.println(user);
        }
    }

    @Test
    public void list() {
        Wrapper<User> queryWrapper = Wrappers.<User>lambdaQuery()
                .ge(User::getAge, 0).eq(User::getName, "first");
        List users = userService.list(queryWrapper);
        users.forEach(System.out::println);
        System.out.println("---------------------------");
        List userList = userService.lambdaQuery()
                .gt(User::getAge, 0).eq(User::getName, "first").list();
        userList.forEach(System.out::println);
    }
}
