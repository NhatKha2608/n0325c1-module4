package com.techzen.academy_n0325c1.controller;

import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Student {
    int id;
    String name;
    double score ;

}



