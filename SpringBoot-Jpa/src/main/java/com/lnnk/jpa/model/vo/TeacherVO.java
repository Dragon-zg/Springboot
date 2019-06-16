package com.lnnk.jpa.model.vo;

import com.lnnk.jpa.model.converter.OutputConverter;
import com.lnnk.jpa.model.entity.manttomany.Teacher;
import lombok.Data;

/**
 * Person VO
 *
 * @author Lnnk
 * @date 2019/6/10 11:36
 **/
@Data
public class TeacherVO implements OutputConverter<TeacherVO, Teacher> {

    private String name;
}
