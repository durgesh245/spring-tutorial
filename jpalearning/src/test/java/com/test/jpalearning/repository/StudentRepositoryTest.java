package com.test.jpalearning.repository;

import com.test.jpalearning.entity.Guardian;
import com.test.jpalearning.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
//@DataJpaTest  // It will help you test the data and flush later once testing completed.
class StudentRepositoryTest {
    @Autowired
    StudentRepository studentRepository;

    @Test
    public void saveStudents(){
        //going to create Student object using builder pattern;
        for(int i = 0; i < 100; i++) {
            Student student = Student.builder()
                    .emailId("dkp"+i+"@gamil.com")
                    .firstName("durgesh"+i)
                    .lastName("pandey")
                    //.guardianEmail("dkpGur"+i+"@gmail.com")
                   // .guardianMobile("92768635"+i)
                    //.guardianName("Ravikant"+i)
                    .build();
            studentRepository.save(student);
        }
    }

    @Test
    public void printStudents(){
        List<Student> studentList = studentRepository.findAll();
        studentList.stream().forEach(student -> System.out.println(student));
    }

    @Test
    public void testSaveWithGuardian(){
        for(int i = 0; i < 100; i++) {
            Guardian guardian = Guardian.builder().name("Ravikant"+i).email("dkpGur"+i+"@gmail.com")
                    .mobile("92768635"+i).build();

            Student student = Student.builder()
                    .emailId("dkp"+i+"@gamil.com")
                    .firstName("durgesh"+i)
                    .lastName("pandey")
                    .guardian(guardian)
                    .build();
            studentRepository.save(student);
        }
    }

    @Test
    public void findStudentByName(){
        List<Student> studentList = studentRepository.findByFirstName("durgesh1");
        System.out.println(studentList);
    }

    @Test
    public void findStudentByNameWithLike(){
        List<Student> studentList = studentRepository.findByFirstNameContaining("durgesh1");
        System.out.println(studentList);
    }

    @Test
    public void findStudentByGuardianName(){
        List<Student> studentList = studentRepository.findByGuardianName("Ravikant1");
        System.out.println(studentList);
    }

    @Test
    public void findStudentByGuardianNameIgnorecase(){
        List<Student> studentList = studentRepository.findByGuardianNameContainingIgnoreCase("ravikant1");
        System.out.println(studentList);
    }

    @Test
    public void findStudentFirstAndLastName(){
        List<Student> studentList = studentRepository.findByFirstNameAndLastName("durgesh0", "pandey");
        System.out.println(studentList);
    }

    @Test
    public void findStudentByEmailJpql(){
        List<Student> studentList = studentRepository.getStudentByEmailAddress("dkp0@gamil.com");
        System.out.println(studentList);
    }

    @Test
    public void findStudentNameByEmailJpql(){
        List<String> studentList = studentRepository.getStudentFirstNameByEmailAddress("dkp1");
        System.out.println(studentList);
    }

    @Test
    public void findStudentNameByEmailNativeQuery(){
        List<Student> studentList = studentRepository.getNativeQueryDataByEmailAddress("dkp1");
        System.out.println(studentList);
    }

    @Test
    public void findStudentNameByEmailNativeQueryNamedParam(){
        List<Student> studentList = studentRepository.getNativeQueryNamedParamDataByEmailAddress("dkp1");
        System.out.println(studentList);
    }

    @Test
    public void setFirstNameViaEmailid(){
        int updated = studentRepository.setFirstNameByEmailid("durgesh1_updated","dkp1@gamil.com");
        System.out.println(updated);
    }
}