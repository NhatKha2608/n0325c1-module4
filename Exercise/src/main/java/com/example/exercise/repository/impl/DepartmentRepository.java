package com.example.exercise.repository.impl;

import com.example.exercise.dto.employee.DepartmentSearchRequest;
import com.example.exercise.model.Department;
import com.example.exercise.model.Employee;
import com.example.exercise.repository.IDepartmentRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.*;


@Repository
public class DepartmentRepository implements IDepartmentRepository {

    @Override
    public List<Department> findByAttributes(DepartmentSearchRequest request) {
        Session session = ConnectionUtil.sessionFactory.openSession(); // Bước 1: Mở phiên làm việc (Session) từ ConnectionUtil
        List<Department> department = null;
        try {
            department = session.createQuery("FROM Department").getResultList(); // Bước 2: Sử dụng HQL để lấy danh sách sinh viên
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close(); // Bước 3: Đóng phiên làm việc sau khi lấy danh sách xong
        }
        return department;
    }

    @Override
    public Optional<Department> findById(UUID id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        String sql = "SELECT * FROM Department WHERE id = :id";
        Query<Department> query = session.createNativeQuery(sql, Department.class);

        query.setParameter("id", id); // Chuyển đổi UUID thành String

        return query.uniqueResultOptional();
    }

    @Override
    public Department save(Department department) {
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {

                session.saveOrUpdate(department);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback(); // Rollback nếu có lỗi
                }
                throw new RuntimeException(e);
            }
        }
        return department;
    }

    @Override
    public void delete(UUID id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Department d WHERE d.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        transaction.commit();
    }
}
