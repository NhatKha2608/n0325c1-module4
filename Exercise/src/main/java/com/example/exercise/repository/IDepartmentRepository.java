package com.example.exercise.repository;


import com.example.exercise.dto.employee.DepartmentSearchRequest;
import com.example.exercise.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDepartmentRepository extends JpaRepository<Department, Integer> {
    @Query("SELECT d FROM Department d WHERE " +
            "(:#{#departmentSearchRequest.name} IS NULL OR d.name LIKE %:#{#departmentSearchRequest.name}%)")
    List<Department> findByAttributes(DepartmentSearchRequest departmentSearchRequest);
}
