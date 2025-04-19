package com.example.exercise.controller;

import com.example.exercise.dto.JsonResponse;
import com.example.exercise.exception.AppException;
import com.example.exercise.exception.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final List<Department> departments = new ArrayList<>(
            Arrays.asList(
                    new Department(1, "Quản lý"),
                    new Department(2, "Kế toán"),
                    new Department(3, "Sale-Marketing"),
                    new Department(4, "Sản xuất")
            )
    );

    @GetMapping
    public ResponseEntity<?> getAll() {
        return JsonResponse.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        return departments.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .map(JsonResponse::ok)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXISTED));
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody Department department) {
        department.setId((int) (Math.random() * 1000000000));
        departments.add(department);
        return JsonResponse.created(department);
    }
    @PutMapping("/w/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Department department) {
        return departments.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .map(d -> {
                    d.setName(department.getName());
                    return JsonResponse.ok(d);
                })
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXISTED));
    }

    @DeleteMapping("/w/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        return departments.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .map(d -> {
                    departments.remove(d);
                    return JsonResponse.noContent();
                })
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXISTED));
    }
}