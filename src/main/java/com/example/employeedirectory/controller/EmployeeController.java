package com.example.employeedirectory.controller;

import com.example.employeedirectory.dto.EmployeeRequest;
import com.example.employeedirectory.dto.EmployeeResponse;
import com.example.employeedirectory.mapper.EmployeeMapper;
import com.example.employeedirectory.model.Employee;
import com.example.employeedirectory.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;

@Tag(name = "Employees", description = "Employee management endpoints")
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService service;
    private final EmployeeMapper mapper;

    public EmployeeController(EmployeeService service, EmployeeMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    //GET ALL
    @Operation(summary = "Get all employees")
    @GetMapping
    public List<EmployeeResponse> getAll() {
        return service.getAllEmployees()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    //GET BY ID
    @Operation(summary = "Get employee by ID")
    @GetMapping("/{id}")
    public EmployeeResponse getById(@PathVariable Long id) {
        return mapper.toResponse(service.getEmployeeById(id));
    }

    //CREATE
    @Operation(summary = "Create a new Employee")
    @PostMapping
    public EmployeeResponse create(@Valid @RequestBody EmployeeRequest request) {
        return mapper.toResponse(
                service.createEmployee(mapper.toEntity(request))
        );
    }

    //UPDATE
    @Operation(summary = "Update all Employees")
    @PutMapping("/{id}")
    public EmployeeResponse update(@PathVariable Long id,
                                   @Valid @RequestBody EmployeeRequest request) {

        return mapper.toResponse(
                service.updateEmployee(id, mapper.toEntity(request))
        );
    }

    //DELETE
    @Operation(summary = "Delete an employee based on ID")
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
