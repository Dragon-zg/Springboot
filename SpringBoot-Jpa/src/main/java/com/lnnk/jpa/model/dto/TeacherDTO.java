package com.lnnk.jpa.model.dto;

import com.lnnk.jpa.model.converter.InputConverter;
import com.lnnk.jpa.model.entity.manttomany.Teacher;
import lombok.Data;

/**
 * Person DTO
 * @author Lnnk
 * @date 2019/6/10 13:40
 **/
@Data
public class TeacherDTO implements InputConverter<Teacher> {

    private String name;
}
