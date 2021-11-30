package com.test.jpalearning.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)  // to auto populate created and last updated date
public class Course {
    @Id
    @SequenceGenerator(name = "course_gen", sequenceName = "course_gen" ,allocationSize = 10)
    @GeneratedValue(generator = "course_gen", strategy = GenerationType.SEQUENCE)
    private Long courseId;
    private String name;
    @Column(length = 10)
    private Integer credit;

    //For bidirectional fetch we will defined the one-2-one mapping here. It will not created any new column but just for mapping to
    //data dynamically,

    @OneToOne(mappedBy = "course", fetch = FetchType.EAGER)
    private CourseMaterial courseMaterial;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "teacherId")
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "student_course_map",
    joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "courseId"),
    inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "studentId"))
    private List<Student> students;

    @CreatedDate
    private Date createdDate;
    @LastModifiedDate
    private Date modifiedDate;
}
