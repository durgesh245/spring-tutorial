package com.test.jpalearning.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Teacher {

    @Id
    @SequenceGenerator(sequenceName = "teacher_seq", name = "teacher_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_seq")
    private Long teacherId;
    private String firstName;
    private String lastName;

    //Recommend by JPA always to use manyToOne as much as possible
/*    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id" , referencedColumnName = "teacherId")
    private List<Course> courses;*/

    @CreatedDate
    private Date dateCreated;

    @LastModifiedDate
    private Date lastUpdated;
}
