package com.example.exercise.dto.employee;

import com.example.exercise.dto.department.DepartmentRequest;
import com.example.exercise.enums.Gender;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponse {
    Integer id;
    String name;
    LocalDate dob;
    Gender gender;
    Double salary;
    String phone;
    String departmentName;
}
