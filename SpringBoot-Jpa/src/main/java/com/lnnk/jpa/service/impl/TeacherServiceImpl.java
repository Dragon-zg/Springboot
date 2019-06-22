package com.lnnk.jpa.service.impl;

import com.google.common.collect.Lists;
import com.lnnk.jpa.model.converter.InputConverter;
import com.lnnk.jpa.model.entity.manttomany.Student;
import com.lnnk.jpa.model.entity.manttomany.Teacher;
import com.lnnk.jpa.repository.TeacherRepository;
import com.lnnk.jpa.service.TeacherService;
import com.lnnk.jpa.service.base.AbstractCurdService;
import com.lnnk.web.enums.ExceptionCode;
import com.lnnk.web.exception.CustomizedException;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author Lnnk
 * @date 2019/4/24 10:39
 **/
@Service
public class TeacherServiceImpl extends AbstractCurdService<Teacher, Long> implements TeacherService {

    private final TeacherRepository teacherRepository;

    protected TeacherServiceImpl(TeacherRepository teacherRepository) {
        super(teacherRepository);
        this.teacherRepository = teacherRepository;
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
    public void init() {
        Student student1 = Student.builder().name("student1").build();
        Student student2 = Student.builder().name("student2").build();
        Teacher teacher1 = Teacher.builder().name("teacher1")
                .students(Lists.newArrayList(student1, student2)).build();
        teacherRepository.save(teacher1);

        Student student3 = Student.builder().name("student3").build();
        Student student4 = Student.builder().name("student4").build();
        Teacher teacher2 = Teacher.builder().name("teacher2")
                .students(Lists.newArrayList(student3, student4)).build();
        teacherRepository.save(teacher2);
    }

    /**
     * 更新
     *
     * @author Lnnk
     * @date 2019/4/25 15:48
     * @params [id, person]
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(final Long id, final InputConverter inputConverter) {
        Optional<Teacher> optional = teacherRepository.findById(id);
        Teacher teacher = optional.orElseThrow(() -> new CustomizedException(ExceptionCode.DATA_NOT_EXIST));
        inputConverter.convertTo(teacher);
        teacherRepository.save(teacher);
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
        Optional<Teacher> optional = teacherRepository.findById(id);
        optional.ifPresent(teacher -> {
            teacher.setDeleteFlag(true);
            List<Student> students = teacher.getStudents();
            if (CollectionUtils.isNotEmpty(students)) {
                students.forEach(student -> student.setDeleteFlag(true));
            }
            teacherRepository.save(teacher);
        });
    }
}
