package entities;

import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PaymentOptions {
    private int id;
    private int userId;
    private String cardNumber;
    private String cardHolder;
    private String expiryDate;
    private String cvv;

    public PaymentOptions(int userId, String cardNumber, String cardHolder, String expiryDate, String cvv) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    public void saveToDatabase() {
        try {
            Connection con = DatabaseConnection.getConnection();
            String query = "INSERT INTO payment_options (user_id, card_number, card_holder, expiry_date, cvv) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userId);
            ps.setString(2, cardNumber);
            ps.setString(3, cardHolder);
            ps.setString(4, expiryDate);
            ps.setString(5, cvv);
            ps.executeUpdate();
            System.out.println("Payment method saved!");
        } catch (Exception e) {
            System.out.println("Error saving payment option: " + e.getMessage());
        }
    }
}

