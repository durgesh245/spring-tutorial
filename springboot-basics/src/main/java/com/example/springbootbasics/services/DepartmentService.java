package com.example.springbootbasics.services;

import com.example.springbootbasics.entity.Department;
import com.example.springbootbasics.exceptions.DepartmentExceptionHandeler;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> fetchDepartmentList();

    Department fetchDepartmentbyId(Long departmentId) throws DepartmentExceptionHandeler;

    void deleteDepartmentById(Long departmentId);

    Department updateDepartmentById(Long departmentId, Department department);

    List<Department> getDepartmentByName(String name);
    List<Department> getDepartmentByCode(String code);
}
