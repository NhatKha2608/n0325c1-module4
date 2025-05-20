package com.example.exercise.dto.employee;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DepartmentSearchRequest {
    String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dobFrom;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dobTo;

}
