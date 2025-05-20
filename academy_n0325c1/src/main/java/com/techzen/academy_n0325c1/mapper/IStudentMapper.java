package com.techzen.academy_n0325c1.mapper;

import com.techzen.academy_n0325c1.dto.student.StudentRequest;
import com.techzen.academy_n0325c1.dto.student.StudentResponse;
import com.techzen.academy_n0325c1.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IStudentMapper {
    Student studentRequestToStudent(StudentRequest studentRequest);

    @Mapping(source = "clazz.name", target = "clazzName")
    StudentResponse studentToStudentResponse(Student student);
}
