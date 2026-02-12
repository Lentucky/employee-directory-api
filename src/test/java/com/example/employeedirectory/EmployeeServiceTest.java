package com.example.employeedirectory;

import com.example.employeedirectory.model.Employee;
import com.example.employeedirectory.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class EmployeeServiceTest {

    @Autowired
    private EmployeeService service;

    @Test
    void createEmployee_shouldSaveEmployee() {
        Employee e = new Employee();
        e.setFirstName("Juan");
        e.setLastName("Dela Cruz");
        e.setEmail("juan@test.com");
        e.setPosition("Developer");
        e.setSalary(BigDecimal.valueOf(50000));

        Employee saved = service.createEmployee(e);

        assertNotNull(saved.getId());
    }
}
