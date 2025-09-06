package com.example.employeemanager.service;

import com.example.employeemanager.model.Employee;
import com.example.employeemanager.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Transactional
    public Employee create(Employee e) { return employeeRepository.save(e); }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee không tồn tại"));
    }

    @Transactional
    public Employee update(Long id, Employee updated) {
        Employee e = findById(id);
        e.setFirstName(updated.getFirstName());
        e.setLastName(updated.getLastName());
        e.setEmail(updated.getEmail());
        return employeeRepository.save(e);
    }

    @Transactional
    public void delete(Long id) { employeeRepository.deleteById(id); }
}
