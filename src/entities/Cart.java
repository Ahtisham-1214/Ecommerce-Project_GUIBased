package entities;
import utils.DatabaseConnection;

import java.sql.*;

public class Cart {
    private int id;
    private int productId;
    private int quantity;
    private int userId;
    private double totalAmount;  // Instance variable to store the total amount

    // Getter and Setter for totalAmount
    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Cart(int id, int productId, int quantity) {
        this.id=id;
        this.productId=productId;
        this.quantity=quantity;
    }

    public static double getTotalAmount(int userId) {
        double totalAmount = 0.0;

        // SQL query to get all products in the cart for the given user
        String query = "SELECT p.price, c.quantity FROM cart c JOIN products p ON c.product_id = p.id WHERE c.user_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, userId);  // Set the user ID in the query
            ResultSet rs = ps.executeQuery();

            // Loop through the results and calculate the total amount
            while (rs.next()) {
                double price = rs.getDouble("price");  // Product price
                int quantity = rs.getInt("quantity");  // Product quantity in the cart
                totalAmount += price * quantity;  // Add the total cost for the product to the total amount
            }
        } catch (SQLException e) {
            System.out.println("Error calculating total amount: " + e.getMessage());
        }

        return totalAmount;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId=productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity=quantity;
    }

    // Add product to cart in the database
    public static void addToCart(int userId, int productId, int quantity) {
        String sql = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, productId);
            stmt.setInt(3, quantity);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Product added to cart successfully.");
            } else {
                System.out.println("Failed to add product to cart.");
            }

        } catch (SQLException e) {
            System.out.println("Error adding to cart: " + e.getMessage());
        }
    }


    // View all products in the cart
    /*
    This code loops through each product in the cart (from the database query result)
    and prints the product’s details in a formatted way (with spaces for alignment).
     */
    public static void viewCart(int userId) {
        // SQL query to select all products from the cart based on the user ID
        String sql = "SELECT c.id, p.name, p.brand, p.model, p.product_description, p.price, c.quantity " +
                "FROM cart c " +
                "JOIN products p ON c.product_id = p.id " +
                "WHERE c.user_id = ?";  // Join cart with products to get product details

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // Set the userId parameter to the SQL query
            ps.setInt(1, userId);  // Set the user_id parameter

            // Execute the query and get the results
            ResultSet rs = ps.executeQuery();

            // Print a table header for better readability
            System.out.printf("%-10s %-20s %-15s %-15s %-30s %-10s %-10s\n",
                    "ID", "Name", "Brand", "Model", "Description", "Price", "Quantity");
            System.out.println("----------------------------------------------------------------------------------------");

            // Loop through the result set and display the products in the cart
            boolean isEmpty = true;  // Assume the cart is empty initially
            while (rs.next()) {  // While there is a next row in the result set
                isEmpty = false;  // Found at least one product in cart
                System.out.printf("%-10d %-20s %-15s %-15s %-30s $%-10.2f %-10d\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("product_description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"));
            }

            if (isEmpty) {
                System.out.println("Your cart is empty.");
            }

        } catch (SQLException e) {
            System.out.println("Error fetching cart items: " + e.getMessage());
        }
    }
    public static void clearCart(int userId) {
        String sql = "DELETE FROM cart WHERE user_id = ?";  // Delete all items for the given user_id

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);  // Set the user_id parameter to identify the cart

            int rowsAffected = ps.executeUpdate();  // Execute the query

            if (rowsAffected > 0) {
                System.out.println("Cart cleared successfully.");
            } else {
                System.out.println("No items found in the cart for this user.");
            }

        } catch (SQLException e) {
            System.out.println("Error clearing the cart: " + e.getMessage());
        }
    }




    // Remove a product from the cart by cart_item_id
    public static void removeFromCart(int userId, int cartItemId) {
        String sql = "DELETE FROM cart WHERE user_id = ? AND id = ?";  // id is the primary key in the cart

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);  // Set the user ID
            ps.setInt(2, cartItemId);  // Set the cart item ID

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Product removed from cart.");
            } else {
                System.out.println("Product not found in cart.");
            }

        } catch (SQLException e) {
            // Print the full stack trace for debugging
            e.printStackTrace();
            System.out.println("Error removing product from cart: " + e.getMessage());
        }
    }

    public static void reviewCart(int userId) {
        // SQL query to select all products from the cart based on the user ID
        String sql = "SELECT p.id, p.name, p.brand, p.model, p.product_description, p.price, c.quantity " +
                "FROM cart c " +
                "JOIN products p ON c.product_id = p.id " +
                "WHERE c.user_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId); // Set the user ID

            ResultSet rs = ps.executeQuery();

            // Print a table header for better readability
            System.out.printf("%-10s %-20s %-15s %-15s %-30s %-10s %-10s\n",
                    "ID", "Name", "Brand", "Model", "Description", "Price", "Quantity");
            System.out.println("----------------------------------------------------------------------------------------");

            // Loop through the result set and display the products in the cart
            while (rs.next()) {
                System.out.printf("%-10d %-20s %-15s %-15s %-30s $%-10.2f %-10d\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getString("product_description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching cart items: " + e.getMessage());
        }
    }


    // Update the quantity of a product in the cart
    public static void updateCart(int userId, int productId, int newQuantity) {
        String sql = "UPDATE cart SET quantity = ? WHERE user_id = ? AND product_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, newQuantity); // Set the new quantity
            ps.setInt(2, userId); // Set user ID
            ps.setInt(3, productId); // Set product ID

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Cart updated successfully.");
            } else {
                System.out.println("Failed to update cart.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating cart: " + e.getMessage());
        }
    }
}
