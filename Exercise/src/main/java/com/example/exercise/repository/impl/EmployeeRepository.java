package com.example.exercise.repository.impl;

import com.example.exercise.dto.employee.EmployeeSearchRequest;
import com.example.exercise.enums.Gender;
import com.example.exercise.model.Employee;
import com.example.exercise.repository.IEmployeeResository;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository
public class EmployeeRepository implements IEmployeeResository {

    @Override
    public List<Employee> findByAttributes(EmployeeSearchRequest request) {

        Session session = ConnectionUtil.sessionFactory.openSession(); // Bước 1: Mở phiên làm việc (Session) từ ConnectionUtil
        List<Employee> employee = null;
        try {
            employee = session.createQuery("FROM Employee").getResultList(); // Bước 2: Sử dụng HQL để lấy danh sách sinh viên
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close(); // Bước 3: Đóng phiên làm việc sau khi lấy danh sách xong
        }
        return employee;
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        String sql = "SELECT * FROM Employee WHERE id = :id";
        Query<Employee> query = session.createNativeQuery(sql, Employee.class);

        query.setParameter("id", id); // Chuyển đổi UUID thành String

        return query.uniqueResultOptional();
    }

    @Override
    public Employee save(Employee employee) {
        try (Session session = ConnectionUtil.sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {

                session.saveOrUpdate(employee);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback(); // Rollback nếu có lỗi
                }
                throw new RuntimeException(e);
            }
        }
        return employee;
    }

    @Override
    public void delete(Integer id) {
        Session session = ConnectionUtil.sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM Employee e WHERE e.id = :id")
                .setParameter("id", id)
                .executeUpdate();
        transaction.commit();
    }
}
