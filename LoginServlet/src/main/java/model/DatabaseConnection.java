package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL Driver
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/logindb", "root", ""
                );
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                return null;  // Trả về null nếu có lỗi
            }
        }
        return connection;
    }
}
