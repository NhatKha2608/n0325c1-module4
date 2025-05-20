package com.example.exercise.controller;

import com.example.exercise.dto.ApiResponse;
import com.example.exercise.dto.employee.EmployeeRequest;
import com.example.exercise.dto.employee.EmployeeResponse;
import com.example.exercise.dto.employee.EmployeeSearchRequest;
import com.example.exercise.dto.page.PageResponse;
import com.example.exercise.mapper.IEmployeeMapper;
import com.example.exercise.model.Employee;

import com.example.exercise.exception.AppException;
import com.example.exercise.exception.ErrorCode;
import com.example.exercise.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/employees")
public class EmployeeController {
    IEmployeeService employeeService;
    IEmployeeMapper employeeMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<Employee>>> getAll(
            @ModelAttribute EmployeeSearchRequest employeeSearchRequest,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return ResponseEntity.ok(ApiResponse.<PageResponse<Employee>>builder()
                .data(new PageResponse<>(employeeService.findByAttributes(employeeSearchRequest, pageable)))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        Employee employee = employeeService.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
        EmployeeResponse response = employeeMapper.employeeToEmployeeResponse(employee);
        return ResponseEntity.ok(ApiResponse.<EmployeeResponse>builder()
                .data(response)
                .build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeMapper.employeeToEmployee(employeeRequest);
        employeeService.save(employee);
        EmployeeResponse employeeResponse = employeeMapper.employeeToEmployeeResponse(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<EmployeeResponse>builder()
                        .data(employeeResponse)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody EmployeeRequest employeeRequest) {
        employeeService.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
        Employee employee = employeeMapper.employeeToEmployee(employeeRequest);
        employee.setId(id);
        employeeService.save(employee);
        EmployeeResponse response = employeeMapper.employeeToEmployeeResponse(employee);
        return ResponseEntity.ok(ApiResponse.<EmployeeResponse>builder()
                .data(response)
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        employeeService.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.EMPLOYEE_NOT_EXIST));
        employeeService.delete(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .message("Employee deleted successfully")
                .build());
    }
}

