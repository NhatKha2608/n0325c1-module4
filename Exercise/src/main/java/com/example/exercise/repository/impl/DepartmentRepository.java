package com.example.exercise.repository.impl;

import com.example.exercise.dto.employee.DepartmentSearchRequest;
import com.example.exercise.model.Department;
import com.example.exercise.repository.IDepartmentRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class DepartmentRepository implements IDepartmentRepository {

    private final List<Department> departments = new ArrayList<>(Arrays.asList(
            new Department(1, "Quản lý"),
            new Department(2, "Kế toán"),
            new Department(3, "Sale-Marketing"),
            new Department(4, "Sản xuất")
    ));

    @Override
    public List<Department> findByAttributes(DepartmentSearchRequest request) {
        return departments.stream()
                .filter(d -> request.getName() == null || d.getName().toLowerCase().contains(request.getName().toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Department> findById(UUID id) {
        try {
            int intId = Integer.parseInt(id.toString());
            return departments.stream()
                    .filter(d -> d.getId() == intId)
                    .findFirst();
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    @Override
    public Department save(Department department) {
        Optional<Department> existing = departments.stream()
                .filter(d -> d.getId() == department.getId())
                .findFirst();

        if (existing.isPresent()) {
            Department d = existing.get();
            d.setName(department.getName());
            return d;
        } else {
            if (department.getId() == null || department.getId() == 0) {
                department.setId((int) (Math.random() * 1000000));
            }
            departments.add(department);
            return department;
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            int intId = Integer.parseInt(id.toString());
            departments.removeIf(d -> d.getId() == intId);
        } catch (NumberFormatException ignored) {
        }
    }
}
