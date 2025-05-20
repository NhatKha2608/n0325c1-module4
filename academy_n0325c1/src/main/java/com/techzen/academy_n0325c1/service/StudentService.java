package com.techzen.academy_n0325c1.service;

import com.techzen.academy_n0325c1.model.Student;
import com.techzen.academy_n0325c1.repository.IStudentRespository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class StudentService implements IStudentService {

    IStudentRespository studentRespository;

    public List<Student> findAll() {
        return studentRespository.findAll();
    }

    public Student save(Student student) {
        return studentRespository.save(student);
    }

    public Student findById(Integer id) {
        return studentRespository.findById(id).orElse(null);
    }

    public Page<Student> findByAttr(String  name , Double fromScore , Double toScore, Pageable pageable) {
        return studentRespository.findByAttr(name,fromScore,toScore,pageable);
    }

    ;
}
