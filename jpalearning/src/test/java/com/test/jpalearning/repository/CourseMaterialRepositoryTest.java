package com.test.jpalearning.repository;

import com.test.jpalearning.entity.Course;
import com.test.jpalearning.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    public CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterialWithCourseData(){
        for (int i=0; i < 50; i++){
            Course course = Course.builder().name("DSA"+1).credit(i+1).build();
            CourseMaterial courseMaterial = CourseMaterial.builder().url("www.test"+i+".com").course(course).build();
            courseMaterialRepository.save(courseMaterial);
        }

        //For update one record to check lastModified date.
/*        CourseMaterial courseMaterial = courseMaterialRepository.findById(62l).get();
        courseMaterial.setUrl("www.equigate.com");
        courseMaterialRepository.save(courseMaterial);*/
    }

    @Test
    public void printAllCourseMaterials(){
        List<CourseMaterial> courseMaterials = courseMaterialRepository.findAll();
        System.out.println(courseMaterials);
    }
}