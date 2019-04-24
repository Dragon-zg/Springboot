package com.jpa.service;

import com.jpa.entity.onetoone.IDCard;
import com.jpa.entity.onetoone.Person;
import com.jpa.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Dragon-zg
 * @date 2019/4/24 10:39
 **/
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * 初始化公民数据
     *
     * @return void
     * @author Dragon-zg
     * @date 2019/4/24 17:25
     * @params []
     */
    @Transactional(rollbackFor = Exception.class)
    public void initPerson() {
        IDCard zidCard = IDCard.builder().cardno("11010219821216xx54").build();
        Person zhangsan = Person.builder().name("张三").idcard(zidCard).build();
        personRepository.save(zhangsan);

        IDCard lidCard = IDCard.builder().cardno("110102198902117846").build();
        Person lisi = Person.builder().name("李四").idcard(lidCard).build();
        personRepository.save(lisi);

        IDCard widCard = IDCard.builder().cardno("110102198710148549").build();
        Person wangwu = Person.builder().name("王五").idcard(widCard).build();
        personRepository.save(wangwu);
    }

}
