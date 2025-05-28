package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDatabase {
    public void saveToDatabase() {
        String sql = "INSERT INTO customers (name, email, phone_number, address, customer_type) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

//            ps.setString(1, this.name);
//            ps.setString(2, this.email);
//            ps.setString(3, this.phoneNumber);
//            ps.setString(4, this.address);
//            ps.setString(5, this.customerType);  // Set customerType here 0 for known 1 for Walk in

            ps.executeUpdate();
            System.out.println("User saved successfully.");

        } catch (SQLException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }


    public static int getCustomerIdByCustomerName(String username) {
        String sql = "SELECT customer_id FROM customers WHERE name = ?";
        int userId = -1;  // Default to -1 if the user is not found

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);  // Set the username in the query

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    userId = rs.getInt("id");  // Get the user ID from the result
                }
            }

        } catch (SQLException e) {
            System.out.println("Error fetching user ID: " + e.getMessage());
        }

        return userId;  // Return the user ID
    }
}
