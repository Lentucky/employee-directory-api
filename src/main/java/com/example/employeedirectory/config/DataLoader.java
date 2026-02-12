package com.example.employeedirectory.config;

import com.example.employeedirectory.model.Employee;
import com.example.employeedirectory.service.EmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataLoader implements CommandLineRunner {

    private final EmployeeService service;

    public DataLoader(EmployeeService service) {
        this.service = service;
    }

    @Override
    public void run(String... args) {
        Employee e = new Employee();
        e.setFirstName("Juan");
        e.setLastName("Dela Cruz");
        e.setEmail("juan@test.com");
        e.setPosition("Developer");
        e.setSalary(BigDecimal.valueOf(50000));

        service.createEmployee(e);
    }
}

