package com.example.springbootbasics.services;

import com.example.springbootbasics.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department saveDepartment(Department department);

    List<Department> fetchDepartmentList();

    Department fetchDepartmentbyId(Long departmentId);

    void deleteDepartmentById(Long departmentId);

    Department updateDepartmentById(Long departmentId, Department department);
}
