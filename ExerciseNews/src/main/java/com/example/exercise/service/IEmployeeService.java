package com.example.exercise.service;

import com.example.exercise.dto.employee.EmployeeSearchRequest;
import com.example.exercise.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IEmployeeService {
    Page<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest, Pageable pageable);
    Optional<Employee> findById(Integer id);
    Employee save(Employee employee);
    void delete(Integer id);
}
