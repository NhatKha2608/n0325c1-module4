package com.example.exercise.model;

import com.example.exercise.enums.Gender;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    LocalDate dob;
    @Enumerated(EnumType.STRING)
    Gender gender;
    Double salary;
    String phone;
    Integer department_Id;
}

