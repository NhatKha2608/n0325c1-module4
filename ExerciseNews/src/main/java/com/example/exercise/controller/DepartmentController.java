package com.example.exercise.controller;

import com.example.exercise.dto.employee.DepartmentSearchRequest;
import com.example.exercise.exception.AppException;
import com.example.exercise.exception.ErrorCode;
import com.example.exercise.model.Department;
import com.example.exercise.service.IDepartmentService;
import com.example.exercise.util.JsonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final IDepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) String name) {
        DepartmentSearchRequest request = new DepartmentSearchRequest();
        request.setName(name);
        List<Department> result = departmentService.findByAttributes(request);
        return JsonResponse.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return departmentService.findById(id)
                .map(JsonResponse::ok)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXISTED));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Department department) {
        // Tự tạo ID giả nếu cần, hoặc generate UUID nếu hệ thống dùng UUID
        if (department.getId() == null) {
            department.setId((int) (Math.random() * 1000000)); // giả định dùng Integer
        }
        Department saved = departmentService.save(department);
        return JsonResponse.created(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Department department) {
        Department updated = departmentService.findById(id)
                .map(d -> {
                    d.setName(department.getName());
                    return departmentService.save(d);
                })
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXISTED));
        return JsonResponse.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        departmentService.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DEPARTMENT_NOT_EXISTED));
        departmentService.delete(id);
        return JsonResponse.noContent();
    }
}
