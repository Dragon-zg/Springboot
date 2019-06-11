package com.jpa.model.entity.manttomany;

import com.jpa.model.entity.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * 老师实体
 *
 * @author wangqiang
 * @date 2019/6/11 14:06
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("老师实体")
@Entity
@Table(name = "jpa_teacher")
@Where(clause = "delete_flag = 0")
public class Teacher extends BaseEntity {

    @Id
    @Column(name = "id", columnDefinition = "int(11) comment '主键ID'")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键ID")
    private Long id;

    @Basic
    @Column(name = "name", columnDefinition = "varchar(40) not null comment '老师姓名'")
    @ApiModelProperty("老师姓名")
    private String name;

    @ManyToMany(targetEntity = Student.class, fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
    @JoinTable(name = "jpa_tea_stu",
            joinColumns = {@JoinColumn(name = "teacher_id")}, inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    private List<Student> students;
}
