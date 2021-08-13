package com.example.springbootbasics.controller;

import com.example.springbootbasics.entity.Department;
import com.example.springbootbasics.repository.DepartmentRepository;
import com.example.springbootbasics.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/department")
    public Department saveDepartment(@RequestBody Department department){ // It will automatically convert the JSON to Department otherwise we have to convert JSOPN to Object via lib like Jackson
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> fetcAllDepartments(){
        return departmentService.fetchDepartmentList();
    }

    @GetMapping("/department/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId){
        return departmentService.fetchDepartmentbyId(departmentId);
    }
    @DeleteMapping("/department/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
        return "Department "+departmentId+" deleted successfully";
    }

    @PutMapping("/department/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @RequestBody Department department){
        return departmentService.updateDepartmentById(departmentId, department);
    }
}
