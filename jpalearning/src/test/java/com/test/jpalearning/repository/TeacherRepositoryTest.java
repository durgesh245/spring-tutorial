package com.test.jpalearning.repository;

import com.test.jpalearning.entity.Course;
import com.test.jpalearning.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private CourseRespository courseRespository;

    @Test
    public void saveTeacherInformation(){
        for (int i=0; i < 100; i++){
            List<Course> courseList = new ArrayList<>();
            Course course1 =  null;//courseRespository.findByCredit(i+1); //not able to save because of detached entity passed to persist.
            if(course1 == null){
                course1 = Course.builder().name("DBA"+i).credit(i+1).build();
            }

            courseList.add(course1);
            Course course2 = Course.builder().name("Java"+i).credit(i+1).build();
            courseList.add(course2);

            Teacher teacher = Teacher.builder().firstName("Teacher"+i).lastName("Lastname"+i).build();

            System.out.println(teacher);
            teacherRepository.save(teacher);
        }
    }
}