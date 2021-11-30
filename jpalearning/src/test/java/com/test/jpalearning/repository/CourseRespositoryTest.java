package com.test.jpalearning.repository;

import com.test.jpalearning.entity.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseRespositoryTest {
    @Autowired
    public CourseRespository courseRespository;

    @Test
    public void getCourseData(){
        List<Course> courseList = courseRespository.findAll();
        System.out.println(courseList);
    }

    @Test
    public void getPaginitationTesting(){
        Pageable pageWith11Records1 = PageRequest.of(0, 11, Sort.by("courseId").descending());
        Pageable pageWith11Records2 = PageRequest.of(1, 11, Sort.by("courseId").descending());
        List<Course> courseList = courseRespository.findAll(pageWith11Records1).getContent();
        System.out.println("11111111111111111111111=>"+courseList);
         courseList = courseRespository.findAll(pageWith11Records2).getContent();
        System.out.println("=========>>>"+courseList);
    }

}