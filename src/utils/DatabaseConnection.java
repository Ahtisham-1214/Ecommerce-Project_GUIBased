package utils;
import java.sql.*;

public class DatabaseConnection {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ecommerce_db", "root", "Root"
            );
        } catch (SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
        return null;
    }
}
