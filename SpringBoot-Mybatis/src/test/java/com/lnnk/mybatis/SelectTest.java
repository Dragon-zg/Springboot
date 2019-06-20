package com.lnnk.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.lnnk.mybatis.mapper.UserMapper;
import com.lnnk.mybatis.model.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lnnk
 * @date 2019/6/20 9:40
 **/
@RunWith(SpringRunner.class)
@SpringBootTest("spring.profiles.active=development")
public class SelectTest {

    @Autowired
    private UserMapper userMapper;

    /**
     * 姓名中含雨并且年龄小于40
     * name like '%雨%' and age < 40
     */
     @Test
    public void selectByWarpper1() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.like("name", "雨").lt("age", 40);

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 姓名中含雨并且年龄大于等于20且小于等于40并且email不为空
     * name like '%雨%' and age between 20 and 40 and email is not null
     */
     @Test
    public void selectByWarpper2() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.like("name", "雨").between("age", 20, 40).isNotNull("email");

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 姓名为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
     * name like '王%' or age >= 25 order by age desc,id asc
     */
    @Test
    public void selectByWarpper3() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.likeRight("name", "王").or().ge("age", 25)
                .ge("age", 25).orderByDesc("age").orderByAsc("id");

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 创建时间为2019年2月14日并且直属上级名字为王姓
     * date_format(create_time,'%Y-%m-%d') and manager_id in (select id from user where name like '王%')
     */
     @Test
    public void selectByWarpper4() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        // 此处写法存在sql注入问题，不建议使用
        // userQuery.apply("date_format(create_time,'%Y-%m-%d')='2019-02-14' or true or true").inSql("manager_id",
        // "select id from user where name like '王%'");

        userQuery.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-02-14").inSql("manager_id",
                "select id from user where name like '王%'");

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 名字为王姓并且（年龄小于40或邮箱不为空）
     * name like '王%' and (age < 40 or email is not null)
     */
    // @Test
    public void selectByWarpper5() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.likeRight("name", "王").and(wq -> wq.lt("age", 40).or().isNotNull("email"));

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 名字为王姓或者（年龄小于40并且大于20并且邮箱不为空）
     * name like '王%' or (age < 40 and age > 20 and email is not null)
     */
    // @Test
    public void selectByWarpper6() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.likeRight("name", "王").or(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * （年龄小于40或邮箱不为空）并且名字为王姓
     * (age < 40 or email is not null) and name like '王%'
     */
    // @Test
    public void selectByWarpper7() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.nested(wq -> wq.lt("age", 40).or().isNotNull("email")).likeRight("name", "王");

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 年龄为30,31,34,35
     * age in (30,31,34,35)
     */
    // @Test
    public void selectByWarpper8() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.in("age", Arrays.asList(30, 31, 34, 35));

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 只返回满足条件的其中一条语句即可
     * limit 1
     */
    // @Test
    public void selectByWarpper9() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.in("age", Arrays.asList(30, 31, 34, 35)).last("limit 1");

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 姓名中含雨并且年龄小于40(只返回id和name两列)
     * name like '%雨%' and age < 40
     */
    // @Test
    public void selectByWarpper10_1() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.select("id", "name").like("name", "雨").lt("age", 40);

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 姓名中含雨并且年龄小于40(只返回id、name、age、email列)
     *      * name like '%雨%' and age < 40
     */
    // @Test
    public void selectByWarpper10_2() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.like("name", "雨").lt("age", 40).select(User.class,
                info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("manager_id"));

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄。并且只取年龄总和小于500的组
     * select avg(age) avg_age, max(age) max_age, min(age) min_age from user
     * group by manager_id having sum(age) < 500
     */
    // @Test
    public void selectByWarpper11() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.select("avg(age) avg_age", "max(age) max_age", "min(age) min_age").groupBy("manager_id")
                .having("sum(age) < {0}", 500);

        List<Map<String, Object>> userList = userMapper.selectMaps(userQuery);
        userList.forEach(System.out::println);
    }

    // @Test
    public void selectByWarpperObjs() {
        // 条件构造器
        QueryWrapper<User> userQuery = new QueryWrapper<>();
        userQuery.select("id", "name").like("name", "雨").lt("age", 40);

        List<Object> userList = userMapper.selectObjs(userQuery);
        userList.forEach(System.out::println);
    }

    // @Test
    public void selectByWarpperCount() {
        // 条件构造器
        QueryWrapper<User> userQuery = new QueryWrapper<>();
        userQuery.like("name", "雨").lt("age", 40);

        Integer count = userMapper.selectCount(userQuery);
        System.out.println("总记录数：" + count);
    }

    // @Test
    public void selectByWarpperOne() {
        // 条件构造器
        QueryWrapper<User> userQuery = new QueryWrapper<>();
        userQuery.like("name", "刘红雨").lt("age", 40);

        User user = userMapper.selectOne(userQuery);
        System.out.println(user);
    }

    // @Test
    public void selectByWarpperEntity() {
        User userWhere = new User();
        userWhere.setName("刘红雨");
        userWhere.setAge(32);

        // 条件构造器
        QueryWrapper<User> userQuery = new QueryWrapper<>(userWhere);
        // userQuery.like("name", "雨").lt("age", 40);

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    // @Test
    public void selectByWarpperAllEq() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "王天风");
        params.put("age", null);

        // 条件构造器
        QueryWrapper<User> userQuery = new QueryWrapper<>();
        // userQuery.allEq(params, false);
        userQuery.allEq((k, v) -> !k.equals("name"), params);

        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }

    // @Test
    public void selectByWarpperMaps() {
        // 条件构造器
        QueryWrapper<User> userQuery = new QueryWrapper<>();
        userQuery.select("name", "age").like("name", "雨").lt("age", 40);

        List<Map<String, Object>> userList = userMapper.selectMaps(userQuery);
        userList.forEach(System.out::println);
    }

    // @Test
    public void selectLambda1() {
        // 条件构造器
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.like(User::getName, "雨").lt(User::getAge, 40);

        List<User> userList = userMapper.selectList(lambdaQueryWrapper);
        userList.forEach(System.out::println);
    }

    // @Test
    public void selectLambda2() {
        // 条件构造器
        LambdaQueryWrapper<User> lambdaQuery = new LambdaQueryWrapper<>();
        lambdaQuery.likeRight(User::getName, "王").and(age -> age.lt(User::getAge, 40).or().isNotNull(User::getEmail));

        List<User> userList = userMapper.selectList(lambdaQuery);
        userList.forEach(System.out::println);
    }

    // @Test
    public void selectLambda3() {
        // 条件构造器
        List<User> userList = new LambdaQueryChainWrapper<User>(userMapper).like(User::getName, "雨")
                .ge(User::getAge, 20).list();
        userList.forEach(System.out::println);
    }

    // @Test
    public void selectListPage() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.ge("age", 26);

        Page<User> page = new Page<>(1, 3);

        IPage<User> userPage = userMapper.selectPage(page, userQuery);
        System.out.println("总页数：" + userPage.getPages());
        System.out.println("总记录数：" + userPage.getTotal());

        List<User> userList = userPage.getRecords();
        userList.forEach(System.out::println);
    }

    // @Test
    public void selectMapsPage() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.ge("age", 26);

        Page<User> page = new Page<>(1, 3);

        IPage<Map<String, Object>> userPage = userMapper.selectMapsPage(page, userQuery);
        System.out.println("总页数：" + userPage.getPages());
        System.out.println("总记录数：" + userPage.getTotal());

        List<Map<String, Object>> userList = userPage.getRecords();
        userList.forEach(System.out::println);
    }

    // @Test
    public void selectPageRecords() {
        // 条件构造器
        QueryWrapper<User> userQuery = Wrappers.query();
        userQuery.ge("age", 26);

        Page<User> page = new Page<>(1, 3, false);

        IPage<Map<String, Object>> userPage = userMapper.selectMapsPage(page, userQuery);
        System.out.println("总页数：" + userPage.getPages());
        System.out.println("总记录数：" + userPage.getTotal());

        List<Map<String, Object>> userList = userPage.getRecords();
        userList.forEach(System.out::println);
    }

    /**
     *
     * 多个查询参数，其中为空则不做条件
     */
    // @Test
    public void testCondition() {
        String name = "王";
        String email = "";
        condition(name, email);
    }

    private void condition(String name, String email) {
        QueryWrapper<User> userQuery = new QueryWrapper<>();

        // if (StringUtils.isNotEmpty(name)) {
        // userQuery.like("name", name);
        // }
        // if (StringUtils.isNotEmpty(email)) {
        // userQuery.like("email", email);
        // }

        userQuery.like(StringUtils.isNotEmpty(name), "name", name).like(StringUtils.isNotEmpty(email), "email", email);
        List<User> userList = userMapper.selectList(userQuery);
        userList.forEach(System.out::println);
    }
}
