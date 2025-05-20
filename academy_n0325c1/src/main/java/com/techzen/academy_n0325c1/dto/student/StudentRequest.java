package com.techzen.academy_n0325c1.dto.student;

import com.techzen.academy_n0325c1.constraint.DobConstraint;
import com.techzen.academy_n0325c1.dto.clazz.ClazzRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentRequest {
    @NotBlank(message = "Tên không được để trống")
            @Pattern(regexp = "[a-zA-ZÀ-ỹ]+",message = "Không được ký tự đặc biệt")
            @Length(min = 3, message = "Tên phải lớn hơn 3 kí tự")
    String name;

    @NotNull(message = "Điểm không được để trống")
            @Min(value = 0, message = "Điểm phải lớn hơn ")
            @Max(value = 10, message = "Điểm phải nhỏ hơn ")
    Double score ;

    @Valid
    @NotNull(message = "bắt buộc phải chọn cho sinh viên")
    ClazzRequest clazz;

    @NotNull(message = "Ngay sinh nhat la bat buoc")
    @DobConstraint(min = 18, message = "Ban khong du tuoi")
    LocalDate dateOfBirth;
}
