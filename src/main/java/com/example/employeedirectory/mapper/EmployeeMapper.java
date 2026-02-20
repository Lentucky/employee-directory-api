package com.example.employeedirectory.mapper;

import com.example.employeedirectory.dto.EmployeeRequest;
import com.example.employeedirectory.dto.EmployeeResponse;
import com.example.employeedirectory.model.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPosition(request.getPosition());
        employee.setSalary(request.getSalary());
        return employee;
    }

    public EmployeeResponse toResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setEmail(employee.getEmail());
        response.setPosition(employee.getPosition());
        response.setSalary(employee.getSalary());
        return response;
    }
}