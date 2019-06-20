package com.lnnk.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.lnnk.mybatis.mapper.UserMapper;
import com.lnnk.mybatis.model.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author lnnk
 * @date 2019/6/20 9:28
 **/
@RunWith(SpringRunner.class)
@SpringBootTest("spring.profiles.active=development")
public class CurdtTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setName("张无忌");
        user.setAge(30);
        user.setCreateTime(new Date());
        int rows = userMapper.insert(user);
        System.out.println("影响记录数：" + rows);
    }

    // @Test
    public void updateById() {
        User user = new User();
        user.setId(1134013564424658946L);
        user.setEmail("zwj@baomidou.com");
        int rows = userMapper.updateById(user);
        System.out.println("影响记录数：" + rows);
    }

    // @Test
    public void updateByWrapper1() {
        UpdateWrapper<User> userWrapper = Wrappers.update();
        userWrapper.eq("name", "李艺伟").eq("age", 28);

        User user = new User();
        user.setEmail("lyw2019@baomidou.com");
        user.setAge(29);

        int rows = userMapper.update(user, userWrapper);
        System.out.println("影响记录数：" + rows);
    }

    // 构造器参数和实体对象参数会重复出现
    // @Test
    public void updateByWrapper2() {
        User userWhere = new User();
        userWhere.setName("李艺伟");

        UpdateWrapper<User> userWrapper = Wrappers.update(userWhere);
        userWrapper.eq("name", "李艺伟").eq("age", 28);

        User user = new User();
        user.setAge(29);

        int rows = userMapper.update(user, userWrapper);
        System.out.println("影响记录数：" + rows);
    }

    // @Test
    public void updateByWrapper3() {
        UpdateWrapper<User> userWrapper = Wrappers.update();
        userWrapper.eq("name", "李艺伟").eq("age", 29).set("age", 30);

        User user = new User();
        user.setAge(29);

        int rows = userMapper.update(null, userWrapper);
        System.out.println("影响记录数：" + rows);
    }

    // @Test
    public void updateByWrapperLambda() {
        LambdaUpdateWrapper<User> lambdaUpdate = Wrappers.lambdaUpdate();
        lambdaUpdate.eq(User::getName, "李艺伟").eq(User::getAge, 30).set(User::getAge, 31);

        int rows = userMapper.update(null, lambdaUpdate);
        System.out.println("影响记录数：" + rows);
    }

    // @Test
    public void updateByWrapperLambdaChain() {
        boolean flag = new LambdaUpdateChainWrapper<User>(userMapper).eq(User::getName, "李艺伟").eq(User::getAge, 31)
                .set(User::getAge, 32).update();

        System.out.println("是否成功：" + flag);
    }

    // @Test
    public void deleteById() {
        int rows = userMapper.deleteById(1134013564424658946L);
        System.out.println("删除条数：" + rows);
    }

    // @Test
    public void deleteByMap() {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "张无忌1");
        columnMap.put("age", 31);

        int rows = userMapper.deleteByMap(columnMap);
        System.out.println("删除条数：" + rows);
    }

    // @Test
    public void deleteBatchByIds() {
        int rows = userMapper.deleteBatchIds(Arrays.asList(1134345992649379841L, 1134346040569274369L));
        System.out.println("删除条数：" + rows);
    }

    @Test
    public void deleteByWrapper() {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(User::getAge, 27).or().gt(User::getAge, 41);
        int rows = userMapper.delete(lambdaQuery);
        System.out.println("删除条数：" + rows);
    }

    // @Test
    public void selectById() {
        long id = 1094590409767661570L;
        User user = userMapper.selectById(id);
        System.out.println(user);
    }

    // @Test
    public void selectByIds() {
        List<Long> idList = Arrays.asList(1088248166370832385L, 1088250446457389058L, 1094590409767661570L);
        List<User> userList = userMapper.selectBatchIds(idList);
        userList.forEach(System.out::println);
    }

    // @Test
    public void selectByMap() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", "张无忌");
        paramMap.put("age", 31);
        List<User> userList = userMapper.selectByMap(paramMap);
        userList.forEach(System.out::println);
    }

}
