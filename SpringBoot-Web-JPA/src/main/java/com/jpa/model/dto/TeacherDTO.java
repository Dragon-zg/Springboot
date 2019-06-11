package com.jpa.model.dto;

import com.jpa.model.converter.InputConverter;
import com.jpa.model.entity.manttomany.Teacher;
import lombok.Data;

/**
 * Person DTO
 * @author Dragon-zg
 * @date 2019/6/10 13:40
 **/
@Data
public class TeacherDTO implements InputConverter<Teacher> {

    private String name;
}
