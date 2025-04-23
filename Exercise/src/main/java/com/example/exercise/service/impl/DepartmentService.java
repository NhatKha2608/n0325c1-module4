package com.example.exercise.service.impl;

import com.example.exercise.dto.employee.DepartmentSearchRequest;
import com.example.exercise.model.Department;
import com.example.exercise.repository.IDepartmentRepository;
import com.example.exercise.service.IDepartmentService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentService implements IDepartmentService {

    @Autowired
    IDepartmentRepository departmentRepository;

    @Override
    public List<Department> findByAttributes(DepartmentSearchRequest departmentSearchRequest) {
        return departmentRepository.findByAttributes(departmentSearchRequest);
    }

    @Override
    public Optional<Department> findById(UUID id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void delete(UUID id) {
        departmentRepository.delete(id);
    }
}
