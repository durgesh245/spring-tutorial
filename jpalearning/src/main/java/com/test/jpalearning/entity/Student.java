package com.test.jpalearning.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_student",
        uniqueConstraints = @UniqueConstraint(name = "email_unique", columnNames = "email_address")
)
public class Student {

    @Id
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "student_seq")
    private Long studentId;
    private String firstName;
    private String lastName;
    @Column(name = "email_address", nullable = false)
    private String emailId;

    @Embedded
    private Guardian guardian;


    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", guardianName='" + guardian.getName() + '\'' +
                ", guardianEmail='" + guardian.getEmail() + '\'' +
                ", guardianMobile='" + guardian.getMobile() + '\'' +
                '}';
    }
}
