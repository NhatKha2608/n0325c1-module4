package com.techzen.academy_n0325c1.dto.student;

import com.techzen.academy_n0325c1.dto.clazz.ClazzResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentResponse {
    int id;
    String name;
    double score ;
    String clazzName;
    LocalDate dateOfBirth;
}
