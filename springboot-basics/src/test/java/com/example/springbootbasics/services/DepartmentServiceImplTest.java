package com.example.springbootbasics.services;

import com.example.springbootbasics.entity.Department;
import com.example.springbootbasics.repository.DepartmentRepository;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceImplTest {

    @Autowired
    DepartmentService departmentService;  //Get the service

    @MockBean  //Used to get all the beans needs to be mocked
    DepartmentRepository departmentRepository;

    @BeforeEach // It will execute once before each test case execution for this class
   // @BeforeAll  //It will execute once for all the cases
    public void setUp() {
        //Mocking the repository layer with department builder pattern
        System.out.println("============Inside set-up of the department object with mockito.============"+departmentRepository);
        Department dept= Department.builder()
                .departmentId(2l)
                .departmentCode("MX-002")
                .address("Zamania Nagar")
                .name("Mathematics")
                .build();
        List<Department> deptList= new ArrayList<>();
        deptList.add(dept);

        //Calling mocketo method with mocked data
        Mockito
                .when(departmentRepository.findByDepartmentCode(Mockito.anyString()))
                .thenReturn(deptList);
    }

    //Always create the test name with meaning-ful name

    @Test
    @DisplayName("Get department data using department code") //Used to generate the report
    //@Disabled //Used to disable the test case
    void whenValidDepartmentCode_ThenDepartmentShouldFound() {
        System.out.println("=============Inside the main test case execution using department code.===============");
        String departmentName = "Mathematics";
        String departmentCode = "MX-002";
        List<Department> deptList= departmentService.getDepartmentByCode(departmentCode);
        deptList.forEach(department1 -> {
            assertEquals(departmentName, department1.getName());
        });

    }

    @AfterEach  // Will execute after each test case execution. we can write to clean some data if required.
    void executeTestCaseCleanUp(){
        System.out.println("==========test case executed==========");
    }
}