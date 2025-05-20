package com.techzen.academy_n0325c1.controller;

import com.techzen.academy_n0325c1.dto.ApiResponse;
import com.techzen.academy_n0325c1.dto.clazz.ClazzResponse;
import com.techzen.academy_n0325c1.dto.page.PageResponse;
import com.techzen.academy_n0325c1.dto.student.StudentRequest;
import com.techzen.academy_n0325c1.dto.student.StudentResponse;
import com.techzen.academy_n0325c1.exception.AppException;
import com.techzen.academy_n0325c1.exception.ErrorCode;
import com.techzen.academy_n0325c1.mapper.IStudentMapper;
import com.techzen.academy_n0325c1.model.Clazz;
import com.techzen.academy_n0325c1.model.Student;
import com.techzen.academy_n0325c1.repository.IClazzRespository;
import com.techzen.academy_n0325c1.service.IClazzService;
import com.techzen.academy_n0325c1.service.IStudentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentController {
    IStudentService studentService;
    IStudentMapper studentMapper;
    IClazzService clazzService;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<StudentResponse>>> getStudents(
            @RequestParam(defaultValue = "") String name,
            Double fromScore, Double toScore, Pageable pageable
    ) {
        return ResponseEntity.ok(ApiResponse.<PageResponse<StudentResponse>>builder()
                .data(new PageResponse<>(studentService.findByAttr(name, fromScore, toScore, pageable)
                        .map(studentMapper::studentToStudentResponse)))
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentResponse>> getStudent(@PathVariable("id") int id) {
        Student student = studentService.findById(id);
        if (student == null) {
            throw new AppException(ErrorCode.STUDENT_NOT_EXIST);
        }

        return ResponseEntity.ok(ApiResponse.<StudentResponse>builder()
                .data(studentMapper.studentToStudentResponse(student))
                .build()
        );

    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody StudentRequest studentRequest) {


        Student student = studentMapper.studentRequestToStudent(studentRequest);

        student = studentService.save(student);

        if(studentRequest.getClazz() != null) {
            student.setClazz(clazzService.findById(studentRequest.getClazz().getId()));
        }

        StudentResponse studentResponse = studentMapper.studentToStudentResponse(student);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.<StudentResponse>builder()
                        .data(studentResponse)
                        .build()
        );
    }
}
