package com.test.jpalearning.repository;

import com.test.jpalearning.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRespository extends JpaRepository<Course, Long> {

    public Course findByCredit(Integer id);
}
