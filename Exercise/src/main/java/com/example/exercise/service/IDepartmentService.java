package com.example.exercise.service;

import com.example.exercise.dto.employee.DepartmentSearchRequest;
import com.example.exercise.model.Department;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IDepartmentService {
    List<Department> findByAttributes(DepartmentSearchRequest departmentSearchRequest);
    Optional<Department> findById(UUID id);
    Department save(Department department);
    void delete(UUID id);
}
