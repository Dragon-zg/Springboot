package com.jpa.service.impl;

import com.jpa.entity.onetoone.IDCard;
import com.jpa.entity.onetoone.Person;
import com.jpa.repository.PersonRepository;
import com.jpa.service.PersonService;
import com.jpa.service.base.AbstractCurdService;
import com.web.enums.ExceptionCode;
import com.web.exception.CustomizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Dragon-zg
 * @date 2019/4/24 10:39
 **/
@Service
public class PersonServiceImpl extends AbstractCurdService<Person, Long> implements PersonService {

    private final PersonRepository personRepository;

    protected PersonServiceImpl(PersonRepository personRepository) {
        super(personRepository);
        this.personRepository = personRepository;
    }

    /**
     * 初始化公民数据
     *
     * @return void
     * @author Dragon-zg
     * @date 2019/4/24 17:25
     * @params []
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
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

    /**
     * 详情
     *
     * @return com.jpa.entity.onetoone.Person
     * @author Dragon-zg
     * @date 2019/4/25 10:56
     * @params [id]
     */
    @Override
    public Person detail(Long id) {
        Optional<Person> optional = personRepository.findById(id);
        return optional.orElseThrow(() -> new CustomizedException(ExceptionCode.DATA_NOT_EXIST));
    }

    /**
     * 更新
     *
     * @return void
     * @author Dragon-zg
     * @date 2019/4/25 15:48
     * @params [id, person]
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(final Long id, final Person person) {
        Optional<Person> optional = personRepository.findById(id);
        Person update = optional.orElseThrow(() -> new CustomizedException(ExceptionCode.DATA_NOT_EXIST));
        update.setName(person.getName());
        update.getIdcard().setCardno(person.getIdcard().getCardno());
        personRepository.save(update);
    }

    /**
     * 删除
     *
     * @return void
     * @author Dragon-zg
     * @date 2019/4/25 15:48
     * @params [id, person]
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(final Long id) {
        Optional<Person> optional = personRepository.findById(id);
        Person delete = optional.orElseThrow(() -> new CustomizedException(ExceptionCode.DATA_NOT_EXIST));
        delete.setDeleteFlag(true);
        delete.getIdcard().setDeleteFlag(true);
        personRepository.save(delete);
    }
}
