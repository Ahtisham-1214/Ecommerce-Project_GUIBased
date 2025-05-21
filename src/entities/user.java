package entities;
import utils.DatabaseConnection;


import java.sql.*;


public class user {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String userType;

    public boolean isBuyer() {
        return "buyer".equalsIgnoreCase(this.userType);
    }
    public boolean isSeller() {
        return "seller".equalsIgnoreCase(this.userType);
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId=userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username=username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email=email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password=password;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName=fullName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber=phoneNumber;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address=address;
    }
    public user(String username, String email, String password, String userType, String fullName, String phoneNumber, String address) {
        this.username = username;      // Assigning the parameter to the class field
        this.email = email;
        this.password = password;
        this.userType = userType;      // 'buyer' or 'seller'
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public user(String username, String email, String password, String fullName, String phoneNumber, String address) {
        this.username=username;
        this.email=email;
        this.password=password;
        this.fullName=fullName;
        this.phoneNumber=phoneNumber;
        this.address=address;
    }
    public void display() {
        System.out.println("------ User Details ------");
        System.out.println("User ID: " + userId);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Full Name: " + fullName);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Address: " + address);
        System.out.println("--------------------------");
    }
    public void saveToDatabase() {
        String sql = "INSERT INTO users (username, email, acc_password, user_type, full_name, phone_number, address) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, this.username);
            ps.setString(2, this.email);
            ps.setString(3, this.password);  // Hash the password in real-world scenarios
            ps.setString(4, this.userType);  // Set userType here
            ps.setString(5, this.fullName);
            ps.setString(6, this.phoneNumber);
            ps.setString(7, this.address);

            ps.executeUpdate();
            System.out.println("User saved successfully.");

        } catch (SQLException e) {
            System.out.println("Error saving user: " + e.getMessage());
        }
    }


    public static int getUserIdByUsername(String username) {
        String sql = "SELECT id FROM users WHERE username = ?";
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
//    public static user authenticateUserId(String username, String password, String userType) {
//        String sql = "SELECT * FROM users WHERE username = ? AND acc_password = ? AND user_type = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//            stmt.setString(3, userType);
//
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                int userId = rs.getInt("id");  // Get the user ID from the database
//                String fullName = rs.getString("full_name");
//
//                // Create a new user object
//                user loggedInUser = new user(
//                        username,
//                        rs.getString("email"),
//                        password,
//                        fullName,
//                        rs.getString("phone_number"),
//                        rs.getString("address"),
//                        userType
//                );
//
//                loggedInUser.setUserId(userId);  // Set the user ID in the user object
//
//                // Set the user session with the logged-in user's details
//                UserSession.setUserSession(username, userId);  // Save the user session
//
//                return loggedInUser;
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Error fetching user: " + e.getMessage());
//        }
//
//        return null;
//    }
    //authentication of login
    // Authentication method
//public static boolean authenticate(String username, String password, String userType) {
//    String sql = "SELECT * FROM users WHERE username = ? AND acc_password = ? AND user_type = ?";
//
//    try (Connection conn = DatabaseConnection.getConnection();
//         PreparedStatement stmt = conn.prepareStatement(sql)) {
//        stmt.setString(1, username);
//        stmt.setString(2, password);
//        stmt.setString(3, userType);
//        ResultSet rs = stmt.executeQuery();
//
//        if (rs.next()) {
//            int userId = rs.getInt("user_id");
//            String fetchedUsername = rs.getString("username");
//
//
//            //UserSession.setUserSession(username);
//
//            return true; // Authentication successful
//        }
//
//        return false; // User not found
//
//    } catch (SQLException e) {
//        System.out.println("Database error: " + e.getMessage());
//        return false;
//    }

}





/*
user
Method Names:
getUserId
setUserId
getUsername
setUsername
getEmail
setEmail
getPassword
setPassword
getFullName
setFullName
getPhoneNumber
setPhoneNumber
getAddress
setAddress
display
getConnection (static method)
saveToDatabase
authenticate (static method)
 */