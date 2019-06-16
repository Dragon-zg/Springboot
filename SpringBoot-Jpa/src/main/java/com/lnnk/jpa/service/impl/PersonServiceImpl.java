package com.lnnk.jpa.service.impl;

import com.lnnk.jpa.model.converter.InputConverter;
import com.lnnk.jpa.model.entity.onetoone.IDCard;
import com.lnnk.jpa.model.entity.onetoone.Person;
import com.lnnk.jpa.repository.PersonRepository;
import com.lnnk.jpa.service.PersonService;
import com.lnnk.jpa.service.base.AbstractCurdService;
import com.lnnk.web.enums.ExceptionCode;
import com.lnnk.web.exception.CustomizedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author Lnnk
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
     * @author Lnnk
     * @date 2019/4/24 17:25
     * @params []
     */
    @Override
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

    /**
     * 更新
     *
     * @return void
     * @author Lnnk
     * @date 2019/4/25 15:48
     * @params [id, person]
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(final Long id, final InputConverter inputConverter) {
        Optional<Person> optional = personRepository.findById(id);
        Person update = optional.orElseThrow(() -> new CustomizedException(ExceptionCode.DATA_NOT_EXIST));
        inputConverter.convertTo(update);
        personRepository.save(update);
    }

    /**
     * 删除
     *
     * @return void
     * @author Lnnk
     * @date 2019/4/25 15:48
     * @params [id, person]
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(final Long id) {
        Optional<Person> optional = personRepository.findById(id);
        optional.ifPresent((person) -> {
            person.setDeleteFlag(true);
            person.getIdcard().setDeleteFlag(true);
            personRepository.save(person);
        });
    }
}
