package com.lnnk.jpa.model.entity.manttomany;

import com.lnnk.jpa.model.entity.base.BaseEntity;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * 老师实体
 *
 * @author Lnnk
 * @date 2019/6/11 14:06
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "jpa_teacher")
@Where(clause = "delete_flag = 0")
public class Teacher extends BaseEntity {

    /**
     * 主键ID
     */
    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 老师姓名
     */
    @Basic
    @Column(name = "name", columnDefinition = "varchar(40) not null comment '老师姓名'")
    private String name;

    @ManyToMany(targetEntity = Student.class, fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    @JoinTable(name = "jpa_tea_stu",
            joinColumns = {@JoinColumn(name = "teacher_id")}, inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    private List<Student> students;
}
