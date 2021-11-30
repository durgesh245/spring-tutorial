package com.test.jpalearning.repository;

import com.test.jpalearning.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Note-: Follow the tutorials on https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
 */

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    public List<Student> findByFirstName(String firstName);

    //This will be used for like based queries
    public List<Student> findByFirstNameContaining(String firstName);

    //This will give the result with not null values
    public List<Student> findByLastNameNotNull();

    //Using embeded parameters of a class
    public List<Student> findByGuardianName(String guardianName);

    //Using embeded parameters of a class by ignoring the cases and performing the like query
    public List<Student> findByGuardianNameContainingIgnoreCase(String guardianName);

    //using two fields or more then two fields
    public List<Student> findByFirstNameAndLastName(String firstName, String lastName);


    //JPQL Based Query Implementation
    //JPQL always work on class and class fields not on actual database table and fields.
    @Query("select s from Student s where s.emailId = ?1")
    public List<Student> getStudentByEmailAddress(String email);

    //Fetch selected data using JPQL
    //It Will not return the results row-wise. It will combine all the results in a single list.
    @Query("select s.firstName, s.emailId from Student s where s.emailId like %?1%")
    public List<String> getStudentFirstNameByEmailAddress(String email);

    //Fetch selected data using JPQL for rowwise results
    //It Will return the results row-wise.
    //todo
    @Query("select s.firstName, s.emailId from Student s where s.emailId like %?1%")
    public List<String> getStudentFirstNameByEmailAddressRowWise(String email);

    //Native Query Implementation
    //It will not work for selected columns. for selected columns we have to use different projection mechanism
    @Query(value = "select * from tbl_student s where s.email_address like %?1%", nativeQuery = true)
    public List<Student> getNativeQueryDataByEmailAddress(String email);

    //Native Query with named parameters. It can run witj JPQL as well in same way.
    @Query(value = "select * from tbl_student s where s.email_address like %:email%", nativeQuery = true)
    public List<Student> getNativeQueryNamedParamDataByEmailAddress(@Param("email") String emailId); //Can give comma separated multiple params here.

    //Transactional and data modification
    //Using Native or JPQl

    //todo Explore how to updated using Using Derived Queries. Also custom modification and best practices

    @Transactional
    @Modifying
    @Query(value = "update tbl_student SET first_name = :firstName WHERE email_address = :email", nativeQuery = true)
    public int setFirstNameByEmailid(@Param("firstName") String firstName, @Param("email") String emailId);

    //todo same way delete implementation can be done using both dervived and query based delete. like deleteById.

    //Relation Ship mapping


}
