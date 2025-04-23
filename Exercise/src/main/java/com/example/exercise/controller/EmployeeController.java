package com.example.exercise.controller;

import com.example.exercise.dto.employee.EmployeeSearchRequest;
import com.example.exercise.model.Employee;
import com.example.exercise.service.IEmployeeService;
import com.example.exercise.util.JsonResponse;
import com.example.exercise.exception.AppException;
import com.example.exercise.exception.ErrorCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;


@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/employees")
public class EmployeeController {
    IEmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAll(EmployeeSearchRequest employeeSearchRequest) {
        return JsonResponse.ok(employeeService.findByAttributes(employeeSearchRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") UUID id) {
        return employeeService.findById(id)
                .map(JsonResponse::ok)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Employee employee) {
        return JsonResponse.created(employeeService.save(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") UUID id, @RequestBody Employee employee) {
        employeeService.findById(id).orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
        employee.setId(id);
        return JsonResponse.ok(employeeService.save(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        employeeService.findById(id).orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
        employeeService.delete(id);
        return JsonResponse.noContent();
    }


}
