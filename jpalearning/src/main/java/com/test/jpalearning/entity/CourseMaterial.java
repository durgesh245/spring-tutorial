package com.test.jpalearning.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@ToString(exclude = "course")
public class CourseMaterial {
    @Id
    @SequenceGenerator(name = "course_material_seq", sequenceName = "course_material_seq", allocationSize = 10)
    @GeneratedValue(generator = "course_material_seq", strategy = GenerationType.SEQUENCE)
    private Long courseMaterialId;
    private String url;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)   //because of LAZY we have to exclude toString of course
    @JoinColumn(
            name = "course_id",
            referencedColumnName = "courseId"
    )
    private Course course;

    @CreatedDate
    private Date createdDate;

    @LastModifiedDate
    private Date updatedDate;

}
