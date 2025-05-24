import Model.DatabaseConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CartFrame {
    private static JTable table;
    private static JLabel subtotalLabel;
    private static JLabel totalLabel;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = createMainFrame();
            frame.add(createHeaderPanel(), BorderLayout.NORTH);
            frame.add(createTableScrollPane(), BorderLayout.CENTER);
            frame.add(createSouthPanel(1), BorderLayout.SOUTH); // Pass cartId here

            // Load cart data dynamically (replace 1 with the actual cart ID)
            loadCartData(1);

            frame.setVisible(true);
        });
    }

    private static JFrame createMainFrame() {
        JFrame frame = new JFrame("MobiLink");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.getContentPane().setBackground(new Color(0xF5F1EC));
        frame.setLayout(new BorderLayout(20, 20));
        try {
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(CartFrame.class.getResource("/resources/login.png")));
            frame.setIconImage(icon.getImage());
        } catch (Exception e) {
            System.out.println("Failed to load icon: " + e.getMessage());
        }

        return frame;
    }

    private static JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setBackground(new Color(0xF5F1EC));
        headerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Add logo icon
        JLabel logoLabel;
        try {
            ImageIcon logoIcon = new ImageIcon("src/logo.png"); // Replace with your logo path or resource
            logoLabel = new JLabel(logoIcon);
        } catch (Exception e) {
            logoLabel = new JLabel(); // fallback if image not found
        }

        // Add brand name
        JLabel brandLabel = new JLabel("MobiLink");
        brandLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        brandLabel.setForeground(new Color(0x333333)); // Dark gray text

        headerPanel.add(logoLabel);
        headerPanel.add(Box.createHorizontalStrut(10)); // Add spacing between logo and text
        headerPanel.add(brandLabel);

        return headerPanel;
    }

    private static JScrollPane createTableScrollPane() {
        String[] columnNames = {"Product", "Quantity", "Price", "Total"};
        Object[][] data = {}; // Initially empty, will be populated dynamically

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1; // Only the Quantity column is editable
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) return Integer.class;
                if (columnIndex == 2 || columnIndex == 3) return Double.class;
                return String.class;
            }
        };

        table = new JTable(tableModel);
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(0xE0E0E0));
        table.getTableHeader().setForeground(Color.BLACK);

        tableModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                int column = e.getColumn();

                if (column == 1 && row >= 0) { // Quantity column
                    Object quantityObj = table.getValueAt(row, 1);
                    Object priceObj = table.getValueAt(row, 2);
                    if (quantityObj != null && priceObj != null) {
                        int quantity = (quantityObj instanceof Integer) ? (Integer) quantityObj : Integer.parseInt(quantityObj.toString());
                        double price = (priceObj instanceof Double) ? (Double) priceObj : Double.parseDouble(priceObj.toString());
                        table.setValueAt(quantity * price, row, 3); // Update Total Price
                    }
                }

                // Update subtotal
                double subtotal = calculateSubtotal();
                subtotalLabel.setText(String.format("Subtotal: PKR %.2f", subtotal));
                totalLabel.setText(String.format("Order Total: PKR %.2f", subtotal+1000)); // Assuming no postage
            }
        });

        return new JScrollPane(table);
    }

    private static JPanel createSouthPanel(int cartId) {
        JPanel southPanel = new JPanel(new BorderLayout(10, 10));
        southPanel.setBackground(new Color(0xF5F1EC));
        southPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        summaryPanel.setBackground(new Color(0xF5F1EC));

        subtotalLabel = new JLabel("Subtotal: PKR 0.00");
        JLabel postageLabel = new JLabel("Postage & Packaging: PKR 1000.00");
        totalLabel = new JLabel("Order Total: PKR 0.00");

        subtotalLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        postageLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        totalLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        summaryPanel.add(subtotalLabel);
        summaryPanel.add(Box.createVerticalStrut(5)); // Add spacing
        summaryPanel.add(postageLabel);
        summaryPanel.add(Box.createVerticalStrut(5)); // Add spacing
        summaryPanel.add(totalLabel);

        southPanel.add(summaryPanel, BorderLayout.CENTER);

        JButton buyNowButton = new JButton("Buy Now");
        buyNowButton.setBackground(new Color(Color.red.getRGB()));
        buyNowButton.setForeground(Color.WHITE);
        buyNowButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        buyNowButton.setFocusPainted(false);
        buyNowButton.setPreferredSize(new Dimension(100, 40));

        buyNowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearCart(cartId); // Clear the cart using the DELETE query
                JOptionPane.showMessageDialog(null, "Thank you for your purchase! Your cart is now empty.", "Purchase Confirmation", JOptionPane.INFORMATION_MESSAGE);
                loadCartData(cartId); // Reload the cart to reflect the empty state
            }
        });

        southPanel.add(buyNowButton, BorderLayout.SOUTH);
        return southPanel;
    }

    public static void loadCartData(int cartId) {
        List<Object[]> rows = new ArrayList<>();

        try {
            Connection con = DatabaseConnection.getConnection(); // Assuming db.DB contains the getConnection method
            PreparedStatement ps = con.prepareStatement(
                    "SELECT p.name, c.quantity, p.price, (p.price * c.quantity) AS Total " +
                            "FROM cart c JOIN products p ON c.product_id = p.id WHERE c.user_id = ?"
                
            );
            ps.setInt(1, cartId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                double total = rs.getDouble("Total");

                rows.add(new Object[]{name, quantity, price, total});
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Unable to use database: " + e.getMessage());
        }

        // Convert List to Object[][]
        Object[][] data = new Object[rows.size()][4];
        for (int i = 0; i < rows.size(); i++) {
            data[i] = rows.get(i);
        }

        // Set data in table
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setDataVector(data, new String[]{"Product", "Quantity", "Price", "Total"});
    }

    private static void clearCart(int cartId) {
        try {
            Connection con = DatabaseConnection.getConnection(); // Assuming db.DB contains the getConnection method
            PreparedStatement ps = con.prepareStatement("DELETE FROM cart WHERE user_id = ?");
            ps.setInt(1, cartId);
            int rowsAffected = ps.executeUpdate(); // Use rowsAffected to confirm the DELETE query worked

            if (rowsAffected > 0) {
                System.out.println("Cart cleared successfully. Rows affected: " + rowsAffected);
            } else {
                System.out.println("No rows deleted. Check if the cart ID exists.");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Unable to clear the cart: " + e.getMessage());
        }
    }

    private static double calculateSubtotal() {
        double subtotal = 0.0;
        for (int i = 0; i < table.getRowCount(); i++) {
            Object val = table.getValueAt(i, 3);
            if (val instanceof Number) {
                subtotal += ((Number) val).doubleValue();
            } else if (val != null) {
                try {
                    subtotal += Double.parseDouble(val.toString());
                } catch (NumberFormatException ignored) {}
            }
        }
        return subtotal;
    }
}