package com.example.exercise.repository.impl;

import com.example.exercise.dto.employee.DepartmentSearchRequest;
import com.example.exercise.model.Department;
import com.example.exercise.repository.IDepartmentRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;


@Repository
public class DepartmentRepository implements IDepartmentRepository {

    @Override
    public List<Department> findByAttributes(DepartmentSearchRequest request) {
        List<Department> departmentList = new ArrayList<>();
        String query = "SELECT id, name FROM department ";

        if (request.getName() != null && !request.getName().isEmpty()) {
            query += " AND LOWER(name) LIKE ?";
        }

        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            if (request.getName()!= null && !request.getName().isEmpty()) {
                preparedStatement.setString(1, "%" + request.getName().toLowerCase() + "%");
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Department department = new Department(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
                departmentList.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching departments", e);
        }

        return departmentList;
    }

    @Override
    public Optional<Department> findById(UUID id) {
        String query = "SELECT * FROM department WHERE id = ?";
        try (PreparedStatement preparedStatement = BaseRepository.getConnection().prepareStatement(query)) {
            preparedStatement.setString(1, String.valueOf(id));
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Department department = new Department(
                        resultSet.getInt("id"),
                        resultSet.getString("name")
                );
                return Optional.of(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching department by ID", e);
        }

        return Optional.empty();
    }

    @Override
    public Department save(Department department) {
        String updateQuery = "UPDATE department SET name = ? WHERE id = ?";
        String insertQuery = "INSERT INTO department (name) VALUES (?)";

        try (Connection connection = BaseRepository.getConnection()) {
            Optional<Department> existingDepartment = findById(UUID.fromString(department.getName()));

            if (existingDepartment.isPresent()) {
                // Cập nhật
                try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                    preparedStatement.setString(1, department.getName());
                    preparedStatement.setInt(2, department.getId());
                    preparedStatement.executeUpdate();
                }
            } else {
                // Thêm mới
                try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                    preparedStatement.setString(1, department.getName());
                    preparedStatement.executeUpdate();
                    // Lấy ID được auto-generate
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            department.setId(generatedKeys.getInt(1));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while saving department", e);
        }

        return department;
    }

    @Override
    public void delete(UUID id) {
        String query = "DELETE FROM department WHERE id = ?";

        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, id.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting department from database", e);
        }
    }



}
