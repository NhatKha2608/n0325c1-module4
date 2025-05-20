package com.example.exercise.mapper;

import com.example.exercise.dto.employee.EmployeeRequest;
import com.example.exercise.dto.employee.EmployeeResponse;
import com.example.exercise.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IEmployeeMapper {
    Employee employeeToEmployee(EmployeeRequest employeeRequest);

    @Mapping(source = "department.name", target = "departmentName")
    EmployeeResponse employeeToEmployeeResponse(Employee employee);
}
