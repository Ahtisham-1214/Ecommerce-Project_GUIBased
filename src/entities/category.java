package entities;

import utils.DatabaseConnection;

import java.sql.*;

public class category {
    private int category_id;
    private String name;
    private String type; // should be either "new" or "old"
    private String description;

    public category() {
    }

    public category(String name, String type, String description) {
        this.name=name;
        this.type=type;
        this.description=description;
    }

    public category(int id, String name, String description, String type) {
        this.category_id=id;
        this.name=name;
        this.description=description;
        this.type=type;
    }

    public int getId() {
        return category_id;
    }

    public void setId(int id) {
        this.category_id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (!type.equalsIgnoreCase("new") && !type.equalsIgnoreCase("old")) {
            throw new IllegalArgumentException("Type must be 'new' or 'old'");
        }
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description=description;
    }
    // === Save to Database ===
    public void saveToDatabase() {
        String sql = "INSERT INTO categories (name, type, description) VALUES (?, ?, ?)";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, name);
            ps.setString(2, type);
            ps.setString(3, description);

            int rows = ps.executeUpdate();
            if (rows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    this.category_id = rs.getInt(1);
                    System.out.println("Category saved with ID: " + category_id);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error saving category: " + e.getMessage());
        }
    }

    // === Display Method ===
    public void display() {
        System.out.println("Category ID: " + category_id);
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Description: " + description);
        System.out.println("-----------------------------");
    }

}
/*
class name
category
Method Names:
getId
setId
getName
setName
getType
setType
getDescription
setDescription
getConnection (private method)
saveToDatabase
display
 */