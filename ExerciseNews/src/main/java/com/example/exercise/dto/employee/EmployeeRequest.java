package com.example.exercise.dto.employee;

import com.example.exercise.dto.department.DepartmentRequest;
import com.example.exercise.enums.Gender;

import java.time.LocalDate;

public class EmployeeRequest {
    String name;
    LocalDate dob;
    Gender gender;
    Double salary;
    String phone;
    DepartmentRequest department;
}
