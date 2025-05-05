package com.example.exercise.repository.impl;

import com.example.exercise.dto.employee.EmployeeSearchRequest;
import com.example.exercise.enums.Gender;
import com.example.exercise.model.Employee;
import com.example.exercise.repository.IEmployeeResository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

@Repository
public class EmployeeRepository implements IEmployeeResository {

    @Override
    public List<Employee> findByAttributes(EmployeeSearchRequest request) {
        List<Employee> employeeList = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT id, name, dob, gender, salary, phone, departmentId FROM employee WHERE 1=1"
        );
        List<Object> params = new ArrayList<>();

        if (request.getName() != null && !request.getName().isBlank()) {
            sql.append(" AND LOWER(name) LIKE ?");
            params.add("%" + request.getName().toLowerCase() + "%");
        }
        if (request.getDepartmentId() != null) {
            sql.append(" AND departmentId = ?");
            params.add(request.getDepartmentId());
        }
        if (request.getSalaryRange() != null) {
            switch (request.getSalaryRange()) {
                case "lt5":  sql.append(" AND salary < 5000000"); break;
                case "5-10": sql.append(" AND salary >= 5000000 AND salary < 10000000"); break;
                case "10-20": sql.append(" AND salary >= 10000000 AND salary <= 20000000"); break;
                case "gt20": sql.append(" AND salary > 20000000"); break;
            }
        }

        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    String name = rs.getString("name");
                    LocalDate dob = rs.getDate("dob").toLocalDate();
                    Gender gender = Gender.valueOf(rs.getString("gender").toUpperCase());
                    Double salary = rs.getDouble("salary");
                    String phone = rs.getString("phone");
                    Integer departmentId = rs.getInt("departmentId");

                    employeeList.add(new Employee(id, name, dob, gender, salary, phone, departmentId));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching employees", e);
        }
        return employeeList;
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        String query = "SELECT id, name, dob, gender, salary, phone, departmentId FROM employee WHERE id = ?";

        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Employee emp = new Employee(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDate("dob").toLocalDate(),
                            Gender.valueOf(rs.getString("gender").toUpperCase()),
                            rs.getDouble("salary"),
                            rs.getString("phone"),
                            rs.getInt("departmentId")
                    );
                    return Optional.of(emp);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching employee by ID", e);
        }
        return Optional.empty();
    }

    @Override
    public Employee save(Employee employee) {
        String updateSql = "UPDATE employee SET name = ?, dob = ?, gender = ?, salary = ?, phone = ?, departmentId = ? WHERE id = ?";
        String insertSql = "INSERT INTO employee (name, dob, gender, salary, phone, departmentId) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = BaseRepository.getConnection()) {
            if (employee.getId() != null && findById(employee.getId()).isPresent()) {
                try (PreparedStatement ps = conn.prepareStatement(updateSql)) {
                    ps.setString(1, employee.getName());
                    ps.setDate(2, Date.valueOf(employee.getDob()));
                    ps.setString(3, employee.getGender().name());
                    ps.setDouble(4, employee.getSalary());
                    ps.setString(5, employee.getPhone());
                    ps.setInt(6, employee.getDepartmentId());
                    ps.setInt(7, employee.getId());
                    ps.executeUpdate();
                }
            } else {
                try (PreparedStatement ps = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                    ps.setString(1, employee.getName());
                    ps.setDate(2, Date.valueOf(employee.getDob()));
                    ps.setString(3, employee.getGender().name());
                    ps.setDouble(4, employee.getSalary());
                    ps.setString(5, employee.getPhone());
                    ps.setInt(6, employee.getDepartmentId());

                    ps.executeUpdate();
                    try (ResultSet keys = ps.getGeneratedKeys()) {
                        if (keys.next()) {
                            employee.setId(keys.getInt(1));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while saving employee", e);
        }
        return employee;
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE FROM employee WHERE id = ?";
        try (Connection conn = BaseRepository.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting employee", e);
        }
    }
}
