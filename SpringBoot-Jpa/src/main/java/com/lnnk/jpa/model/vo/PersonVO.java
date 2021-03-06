package com.lnnk.jpa.model.vo;

import com.lnnk.jpa.model.converter.OutputConverter;
import com.lnnk.jpa.model.entity.onetoone.IDCard;
import com.lnnk.jpa.model.entity.onetoone.Person;
import lombok.Data;
import org.springframework.util.Assert;

/**
 * Person VO
 *
 * @author Lnnk
 * @date 2019/6/10 11:36
 **/
@Data
public class PersonVO implements OutputConverter<PersonVO, Person> {

    private String name;

    private String cardno;

    /**
     * Convert from entity.(shallow)
     *
     * @param person entity data
     * @return converted dto data
     */
    @Override
    public <T extends PersonVO> T convertFrom(Person person) {
        Assert.notNull(person, "person object must not be null");

        PersonVO dto = OutputConverter.super.convertFrom(person);
        IDCard idCard = person.getIdcard();
        if (idCard != null) {
            dto.setCardno(idCard.getCardno());
        }
        return (T) dto;
    }
}
