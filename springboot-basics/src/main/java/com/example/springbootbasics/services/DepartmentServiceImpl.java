package com.example.springbootbasics.services;

import com.example.springbootbasics.entity.Department;
import com.example.springbootbasics.exceptions.DepartmentExceptionHandeler;
import com.example.springbootbasics.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public Department fetchDepartmentbyId(Long departmentId) throws DepartmentExceptionHandeler {
       // return departmentRepository.findById(departmentId).get(); // It's return the optional
        Optional<Department> department = departmentRepository.findById(departmentId);
        if(!department.isPresent()){
            throw new DepartmentExceptionHandeler("Department Not Available");
        }
       return department.get();
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

    @Override
    public List<Department> getDepartmentByName(String name) {
        List<Department> dept = departmentRepository.findByNameIgnoreCase(name);
        return dept;
    }

    //Implementation using JPQL and Native Query
    //Writting the test case for this method
    @Override
    public List<Department> getDepartmentByCode(String code){
        List<Department> dept = departmentRepository.findByDepartmentCode(code);
        return dept;

    }
}
