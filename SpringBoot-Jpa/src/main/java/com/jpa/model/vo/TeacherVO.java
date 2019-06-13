package com.jpa.model.vo;

import com.jpa.model.converter.OutputConverter;
import com.jpa.model.entity.manttomany.Teacher;
import lombok.Data;

/**
 * Person VO
 *
 * @author Dragon-zg
 * @date 2019/6/10 11:36
 **/
@Data
public class TeacherVO implements OutputConverter<TeacherVO, Teacher> {

    private String name;
}
