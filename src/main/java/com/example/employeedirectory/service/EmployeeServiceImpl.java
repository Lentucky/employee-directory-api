package com.example.employeedirectory.service;

import com.example.employeedirectory.exception.ResourceNotFoundException;
import com.example.employeedirectory.model.Employee;
import com.example.employeedirectory.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = getEmployeeById(id);
        existing.setFirstName(employee.getFirstName());
        existing.setLastName(employee.getLastName());
        existing.setEmail(employee.getEmail());
        existing.setPosition(employee.getPosition());
        existing.setSalary(employee.getSalary());
        return repository.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        repository.delete(getEmployeeById(id));
    }
}
