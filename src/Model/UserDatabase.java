package Model;

import Backend.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserDatabase {

    // Establish a connection
    private static Connection connection;

//    MyJDBC() throws SQLException {
//        connection = DatabaseConnection.getConnection();
//    }

    // Validate Login
    public static User validateLogin(String username, String password) {
        String query = "SELECT * FROM users WHERE LOWER(username) = LOWER(?) AND password = ?";

        try {
            connection = DatabaseConnection.getConnection();
            if (connection == null) {
                System.out.println("Database connection failed.");
                return null;
            }
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username.trim());
            stmt.setString(2, password.trim()); // match plain password

            System.out.println("Trying to login with: " + username + " | " + password); // Debug cmd command
            // stmt represents a precompiled SQL statement that you can execute
            // multiple times with different parameters.
            ResultSet rs = stmt.executeQuery();
//rs.next(): This method moves the cursor in the ResultSet to the next row.
// It returns true if there is a next row in the ResultSet
            if (rs.next()) {
                int user_id = rs.getInt("user_id");
                String fullName = rs.getString("full_name");
                String phone = rs.getString("phone_number");
                String address = rs.getString("address");
                String email = rs.getString("email");

                System.out.println("Login successful for user: " + fullName);
                return new User(user_id, username, password, fullName, phone, address, email);
            } else {
                System.out.println("No user found with those credentials.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }


    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashedBytes = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hashedBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    // Register a new user
    public static boolean register(String username, String email, String password, String fullName, String phone, String address) {
        try {
            connection = DatabaseConnection.getConnection();
            String sql = "INSERT INTO users (username, email, password, full_name, phone_number, address) VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password); // store plain password
            stmt.setString(4, fullName);
            stmt.setString(5, phone);
            stmt.setString(6, address);

            stmt.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    // Check if the username already exists
    private static boolean checkUser(String username) {
        try {
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            // If the query returns no result, the username is available
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
