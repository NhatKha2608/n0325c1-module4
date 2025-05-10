package com.example.exercise.service.impl;

import com.example.exercise.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.exercise.repository.IDepartmentRepository;
import com.example.exercise.model.Department;
import com.example.exercise.dto.employee.DepartmentSearchRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Override
    public List<Department> findByAttributes(DepartmentSearchRequest departmentSearchRequest) {
        return departmentRepository.findByAttributes(departmentSearchRequest);
    }

    @Override
    public Optional<Department> findById(Integer id) {
        return departmentRepository.findById(id);
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void delete(Integer id) {
        departmentRepository.deleteById(id); // Sửa lại gọi phương thức đúng
    }
}
