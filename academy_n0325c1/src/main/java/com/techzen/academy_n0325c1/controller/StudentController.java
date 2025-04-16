package com.techzen.academy_n0325c1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1, "Thịnh", 9.6),
                    new Student(2, "Điệp", 9.7),
                    new Student(3, "Bảo", 9.5)
            )
    );

    @GetMapping
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
        for (Student student : students) {
            if(student.getId() == id){
                return ResponseEntity.ok(student);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Student> save(@RequestBody Student student) {
        student.setId((int)(Math.random()*1000000)); // sau này id đã tự động tăng
        students.add(student); // thêm vào danh sachs
        return ResponseEntity.status(HttpStatus.CREATED).body(student);// trả về đối tường vừa tạo
    }


}
