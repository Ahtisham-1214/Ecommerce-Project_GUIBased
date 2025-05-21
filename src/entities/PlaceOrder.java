package entities;

import utils.DatabaseConnection;

import java.sql.*;
import java.util.Scanner;

public class PlaceOrder {

    static Scanner sc=new Scanner(System.in);
    public static boolean userExists(int userId) {
        String query = "SELECT 1 FROM users WHERE user_id = ?";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            return rs.next();  // If user exists, it will return true
        } catch (SQLException e) {
            System.out.println("Error checking user existence: " + e.getMessage());
        }
        return false;
    }
    // Method to place an order
    public static void placeOrder(int userId) {
        if (!userExists(userId)) {
            System.out.println("Invalid user ID. Please ensure the user is registered and logged in.");
            return;
        }
                double totalAmount = Cart.getTotalAmount(userId); // Calculate the total amount from the cart
        if (totalAmount == 0) {
            System.out.println("Your cart is empty. Add products to the cart first.");
            return;
        }

        // Set the order status and payment status
        String status = "pending";
        String paymentStatus = "pending"; // You could implement payment gateway logic here

        // Create an Order object
        Orders order = new Orders(userId, totalAmount, status, paymentStatus);

        // Save the order to the database
        order.saveToDatabase();

        // If order was successfully saved, it will have a valid ID
        if (order.getId() > 0) {
            // Add the products to the order_products table
            addProductsToOrder(userId, order.getId());

            // Optionally, clear the cart after placing the order
            Cart.clearCart(userId);

            // Display order details to the user
            order.display();
        } else {
            System.out.println("Error placing the order. Please try again.");
        }
    }


    // Method to add products to the order_products table
    private static void addProductsToOrder(int userId, int orderId) {
        // Retrieve all products in the user's cart
        String query="SELECT product_id, quantity FROM cart WHERE user_id = ?";

        try (Connection con=DatabaseConnection.getConnection();
             PreparedStatement ps=con.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs=ps.executeQuery();

            // Iterate over the cart items and insert them into the order_products table
            while (rs.next()) {
                int productId=rs.getInt("product_id");
                int quantity=rs.getInt("quantity");

                // Insert into order_products table
                String insertQuery="INSERT INTO order_products (order_id, product_id, quantity) VALUES (?, ?, ?)";
                try (PreparedStatement insertStmt=con.prepareStatement(insertQuery)) {
                    insertStmt.setInt(1, orderId);
                    insertStmt.setInt(2, productId);
                    insertStmt.setInt(3, quantity);

                    insertStmt.executeUpdate();
                }

                // Optionally, update the product stock (subtract the ordered quantity from the product stock)
                updateProductStock(productId, quantity);
            }
        } catch (SQLException e) {
            System.out.println("Error adding products to the order: " + e.getMessage());
        }
    }

    // Method to update product stock after order placement
    private static void updateProductStock(int productId, int quantityOrdered) {
        String updateQuery="UPDATE products SET quantity = quantity - ? WHERE id = ?";

        try (Connection con=DatabaseConnection.getConnection();
             PreparedStatement ps=con.prepareStatement(updateQuery)) {
            ps.setInt(1, quantityOrdered);
            ps.setInt(2, productId);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating product stock: " + e.getMessage());
        }
    }



}