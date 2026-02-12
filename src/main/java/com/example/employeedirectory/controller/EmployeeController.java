package com.example.employeedirectory.controller;

import com.example.employeedirectory.dto.EmployeeRequest;
import com.example.employeedirectory.dto.EmployeeResponse;
import com.example.employeedirectory.model.Employee;
import com.example.employeedirectory.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    //GET ALL
    @GetMapping
    public List<EmployeeResponse> getAll() {
        return service.getAllEmployees()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    //GET BY ID
    @GetMapping("/{id}")
    public EmployeeResponse getById(@PathVariable Long id) {
        Employee employee = service.getEmployeeById(id);
        return mapToResponse(employee);
    }

    //CREATE
    @PostMapping
    public EmployeeResponse create(@Valid @RequestBody EmployeeRequest request) {

        Employee employee = mapToEntity(request);
        Employee saved = service.createEmployee(employee);

        return mapToResponse(saved);
    }

    //UPDATE
    @PutMapping("/{id}")
    public EmployeeResponse update(@PathVariable Long id,
                                   @Valid @RequestBody EmployeeRequest request) {

        Employee employee = mapToEntity(request);
        Employee updated = service.updateEmployee(id, employee);

        return mapToResponse(updated);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteEmployee(id);
    }

    //Mapping Methods
    private EmployeeResponse mapToResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setEmail(employee.getEmail());
        response.setPosition(employee.getPosition());
        response.setSalary(employee.getSalary());
        return response;
    }

    private Employee mapToEntity(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPosition(request.getPosition());
        employee.setSalary(request.getSalary());
        return employee;
    }
}
