package com.techzen.academy_n0325c1.dto.clazz;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ClazzResponse {
    Integer id;
    String name;
}
