package com.example.springbootbasics.repository;

import com.example.springbootbasics.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
        List<Department> findByNameIgnoreCase(String name);
        //@Query(value = "select * from department where department_code like ?1", nativeQuery = true)
        @Query("select d from Department d where d.departmentCode = ?1")  // JPQL Query, Need to use the alias of the table and exact fieldname from the class
        List<Department> findByDepartmentCode(String code);

}
