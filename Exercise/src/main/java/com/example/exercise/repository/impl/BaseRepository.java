package com.example.exercise.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * BaseRepository cung cấp kết nối tới cơ sở dữ liệu.
 * Mỗi khi gọi getConnection() sẽ tạo và trả về một Connection mới.
 */
public class BaseRepository {
    private static final String JDBC_URL      = "jdbc:mysql://localhost:3306/employee_management";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "123123";

    static {
        try {
            // Tải driver JDBC cho MySQL (nếu cần)
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Cannot load JDBC driver", e);
        }
    }

    /**
     * Tạo và trả về Connection mới mỗi lần được gọi.
     * Người gọi chịu trách nhiệm đóng Connection sau khi sử dụng.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
    }

    /**
     * Không cần thiết nếu bạn luôn đóng Connection bằng try-with-resources.
     * Tuy nhiên, có thể giữ phiên bản này nếu muốn đóng thủ công.
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // Log hoặc xử lý theo nhu cầu
                e.printStackTrace();
            }
        }
    }
}