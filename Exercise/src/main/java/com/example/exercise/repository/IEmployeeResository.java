package com.example.exercise.repository;

import com.example.exercise.dto.employee.EmployeeSearchRequest;
import com.example.exercise.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface IEmployeeResository extends JpaRepository<Employee, Integer> {
    @Query(value = """
        SELECT e.* FROM employee e
        LEFT JOIN department d ON e.department_id = d.id
        WHERE (:name IS NULL OR e.name LIKE CONCAT('%', :name, '%'))
          AND (:dobFrom IS NULL OR e.dob >= :dobFrom)
          AND (:dobTo IS NULL OR e.dob <= :dobTo)
          AND (:gender IS NULL OR e.gender = :gender)
          AND (:phone IS NULL OR e.phone LIKE CONCAT('%', :phone, '%'))
          AND (:departmentId IS NULL OR d.id = :departmentId)
          AND (
              CASE
                  WHEN :salaryRange = 'lt5' THEN e.salary < 5000000
                  WHEN :salaryRange = '5-10' THEN e.salary BETWEEN 5000000 AND 10000000
                  WHEN :salaryRange = '10-20' THEN e.salary BETWEEN 10000000 AND 20000000
                  WHEN :salaryRange = 'gt20' THEN e.salary > 20000000
                  ELSE TRUE
              END
          )
        """, nativeQuery = true)
    List<Employee> findByAttributes(
            @Param("name") String name,
            @Param("dobFrom") LocalDate dobFrom,
            @Param("dobTo") LocalDate dobTo,
            @Param("gender") String gender,
            @Param("salaryRange") String salaryRange,
            @Param("phone") String phone,
            @Param("departmentId") Integer departmentId
    );
}
