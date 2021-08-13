package com.example.springbootbasics.services;

import com.example.springbootbasics.entity.Department;
import com.example.springbootbasics.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department fetchDepartmentbyId(Long departmentId) {
        return departmentRepository.findById(departmentId).get(); // It's return the optional
    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    @Override
    public Department updateDepartmentById(Long departmentId, Department department) {
        Department fromDb = departmentRepository.findById(departmentId).get();
        if(Objects.nonNull(department.getName()) && !"".equalsIgnoreCase(department.getName())){
            fromDb.setName(department.getName());
        }
        return departmentRepository.save(fromDb);
    }
}
