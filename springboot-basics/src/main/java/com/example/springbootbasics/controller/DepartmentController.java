package com.example.springbootbasics.controller;

import com.example.springbootbasics.entity.Department;
import com.example.springbootbasics.exceptions.DepartmentExceptionHandeler;
import com.example.springbootbasics.repository.DepartmentRepository;
import com.example.springbootbasics.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/department")
    public Department saveDepartment(@Valid @RequestBody Department department){ // It will automatically convert the JSON to Department otherwise we have to convert JSOPN to Object via lib like Jackson
        return departmentService.saveDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> fetcAllDepartments(){
        return departmentService.fetchDepartmentList();
    }

    @GetMapping("/department/{id}")
    public Department fetchDepartmentById(@PathVariable("id") Long departmentId) throws DepartmentExceptionHandeler {
        return departmentService.fetchDepartmentbyId(departmentId);
    }
    @DeleteMapping("/department/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartmentById(departmentId);
        return "Department "+departmentId+" deleted successfully";
    }

    @PutMapping(value = "/department/{id}")
    public Department updateDepartment(@PathVariable("id") Long departmentId, @Valid @RequestBody Department department){
        return departmentService.updateDepartmentById(departmentId, department);
    }

    //Search by any field defined for a particular controller.
    @GetMapping("department/name/{name}")
    public List<Department> getDepartmentByName(@PathVariable("name") String name){
        return departmentService.getDepartmentByName(name);
    }

    @GetMapping("department/code/{code}")
    public List<Department> getDepartmentByCode(@PathVariable("code") String code){
        return departmentService.getDepartmentByCode(code);
    }
}
