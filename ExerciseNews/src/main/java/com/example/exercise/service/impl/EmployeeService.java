package com.example.exercise.service.impl;

import com.example.exercise.dto.employee.EmployeeSearchRequest;
import com.example.exercise.model.Employee;
import com.example.exercise.repository.IEmployeeResository;
import com.example.exercise.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmployeeService implements IEmployeeService {
    IEmployeeResository employeeRepository;

    @Override
    public Page<Employee> findByAttributes(EmployeeSearchRequest employeeSearchRequest, Pageable pageable) {
        return employeeRepository.findByAttributes(
                employeeSearchRequest.getName(),
                employeeSearchRequest.getDobFrom(),
                employeeSearchRequest.getDobTo(),
                employeeSearchRequest.getGender() != null ? employeeSearchRequest.getGender().toString() : null,
                employeeSearchRequest.getSalaryRange(),
                employeeSearchRequest.getPhone(),
                employeeSearchRequest.getDepartmentId(),
                pageable

        );
    }


    @Override
    public Optional<Employee> findById(Integer id) {
        return employeeRepository.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Integer id) {
        employeeRepository.deleteById(id);
    }
}
