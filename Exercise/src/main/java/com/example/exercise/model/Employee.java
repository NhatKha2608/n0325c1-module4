package com.example.exercise.model;

import com.example.exercise.enums.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    UUID id;
    String name;
    LocalDate dob;
    Gender gender;
    Double salary;
    String phone;
    Integer departmentId;
}

