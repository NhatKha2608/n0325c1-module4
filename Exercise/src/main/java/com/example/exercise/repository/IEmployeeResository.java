package com.example.exercise.repository;

import com.example.exercise.dto.employee.EmployeeSearchRequest;
import com.example.exercise.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeResository {
    List<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest);
    Optional<Employee> findById(Integer id);
    Employee save(Employee employee);
    void delete(Integer id);
}
