package View;

import Backend.entities.Product;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ProductPanel extends JPanel {
    // Color scheme
    private static final Color PRIMARY_COLOR = new Color(81, 4, 4);        // Dark red
    private static final Color SECONDARY_COLOR = new Color(245, 235, 235); // Light red background
    private static final Color ACCENT_COLOR = new Color(156, 37, 37);      // Red accent
    private static final Color TEXT_COLOR = new Color(50, 50, 50);         // Dark gray text
    private static final Color FIELD_BG_COLOR = new Color(245, 245, 245);  // Light gray field background

    // Fonts
    private static final Font HEADER_FONT = new Font("Segoe UI", Font.BOLD, 20);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 12);
    private static final Font FIELD_FONT = new Font("Segoe UI", Font.PLAIN, 12);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 12);

    // Panel components
    private JPanel headerPanel;
    private JLabel titleLabel;

    // Labels
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel brandLabel;
    private JLabel modelLabel;
    private JLabel descriptionLabel;
    private JLabel priceLabel;
    private JLabel quantityLabel;
    private JLabel availableLabel;

    // Fields
    private JTextField idField;
    private JTextField nameField;
    private JTextField brandField;
    private JTextField modelField;
    private JTextArea descriptionField;
    private JTextField priceField;
    private JTextField quantityField;
    private JCheckBox availableField;

    // Buttons
    private JButton btnSave;
    private JButton btnCancel;
    private JButton btnClear;

    public ProductPanel(String title) {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(SECONDARY_COLOR);

        SwingUtilities.invokeLater(() -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (parentFrame != null) {
                parentFrame.setTitle(title);
            }
        });

        initializeComponents();
        createHeaderPanel("Product Management");
        setupLayout();
        setupListeners();
    }

    private void createHeaderPanel(String title) {
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        titleLabel = new JLabel(title);
        titleLabel.setFont(HEADER_FONT);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        headerPanel.add(titleLabel, BorderLayout.CENTER);

        // Add a bottom border to create separation
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
                headerPanel.getBorder(),
                BorderFactory.createMatteBorder(0, 0, 2, 0, ACCENT_COLOR)
        ));

        add(headerPanel, BorderLayout.NORTH);
    }

    private void initializeComponents() {
        // Initialize labels with styling
        idLabel = new JLabel("ID:");
        idLabel.setFont(LABEL_FONT);
        idLabel.setForeground(TEXT_COLOR);

        nameLabel = new JLabel("Name:");
        nameLabel.setFont(LABEL_FONT);
        nameLabel.setForeground(TEXT_COLOR);

        brandLabel = new JLabel("Brand:");
        brandLabel.setFont(LABEL_FONT);
        brandLabel.setForeground(TEXT_COLOR);

        modelLabel = new JLabel("Model:");
        modelLabel.setFont(LABEL_FONT);
        modelLabel.setForeground(TEXT_COLOR);

        descriptionLabel = new JLabel("Product Description:");
        descriptionLabel.setFont(LABEL_FONT);
        descriptionLabel.setForeground(TEXT_COLOR);

        priceLabel = new JLabel("Price:");
        priceLabel.setFont(LABEL_FONT);
        priceLabel.setForeground(TEXT_COLOR);

        quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(LABEL_FONT);
        quantityLabel.setForeground(TEXT_COLOR);

        availableLabel = new JLabel("Available:");
        availableLabel.setFont(LABEL_FONT);
        availableLabel.setForeground(TEXT_COLOR);

        // Initialize fields with styling
        idField = new JTextField(15);
        idField.setEditable(false); // ID is typically auto-generated
        idField.setFont(FIELD_FONT);
        idField.setBackground(new Color(230, 230, 230)); // Darker background for non-editable field
        idField.setBorder(BorderFactory.createCompoundBorder(
                idField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        nameField = new JTextField(20);
        nameField.setFont(FIELD_FONT);
        nameField.setBackground(FIELD_BG_COLOR);
        nameField.setBorder(BorderFactory.createCompoundBorder(
                nameField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        addFocusEffect(nameField);

        brandField = new JTextField(20);
        brandField.setFont(FIELD_FONT);
        brandField.setBackground(FIELD_BG_COLOR);
        brandField.setBorder(BorderFactory.createCompoundBorder(
                brandField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        addFocusEffect(brandField);

        modelField = new JTextField(20);
        modelField.setFont(FIELD_FONT);
        modelField.setBackground(FIELD_BG_COLOR);
        modelField.setBorder(BorderFactory.createCompoundBorder(
                modelField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        addFocusEffect(modelField);

        descriptionField = new JTextArea(5, 20);
        descriptionField.setFont(FIELD_FONT);
        descriptionField.setBackground(FIELD_BG_COLOR);
        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true);
        descriptionField.setBorder(BorderFactory.createCompoundBorder(
                descriptionField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        // Add focus effect to text area
        addFocusEffect(descriptionField);

        priceField = new JTextField(15);
        priceField.setFont(FIELD_FONT);
        priceField.setBackground(FIELD_BG_COLOR);
        priceField.setBorder(BorderFactory.createCompoundBorder(
                priceField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        addFocusEffect(priceField);

        quantityField = new JTextField(15);
        quantityField.setFont(FIELD_FONT);
        quantityField.setBackground(FIELD_BG_COLOR);
        quantityField.setBorder(BorderFactory.createCompoundBorder(
                quantityField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        addFocusEffect(quantityField);

        availableField = new JCheckBox();
        availableField.setSelected(true); // Default to available
        availableField.setBackground(SECONDARY_COLOR);
        availableField.setForeground(TEXT_COLOR);

        // Initialize buttons with styling
        btnSave = new JButton("Save");
        btnSave.setFont(BUTTON_FONT);
        btnSave.setBackground(ACCENT_COLOR);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.setBorderPainted(false);
        btnSave.setOpaque(true);
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSave.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(BUTTON_FONT);
        btnCancel.setBackground(PRIMARY_COLOR);
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFocusPainted(false);
        btnCancel.setBorderPainted(false);
        btnCancel.setOpaque(true);
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCancel.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        btnClear = new JButton("Clear");
        btnClear.setFont(BUTTON_FONT);
        btnClear.setBackground(new Color(100, 100, 100)); // Gray
        btnClear.setForeground(Color.WHITE);
        btnClear.setFocusPainted(false);
        btnClear.setBorderPainted(false);
        btnClear.setOpaque(true);
        btnClear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnClear.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    }

    private void setupLayout() {
        // Create a main content panel with a nice border
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(SECONDARY_COLOR);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10); // More spacing between components
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Make components fill the horizontal space

        // Create a panel for product info
        JPanel productPanel = new JPanel(new GridBagLayout());
        productPanel.setBackground(SECONDARY_COLOR);
        productPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR),
                "Product Information",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                LABEL_FONT,
                PRIMARY_COLOR
        ));

        GridBagConstraints productGbc = new GridBagConstraints();
        productGbc.insets = new Insets(5, 10, 5, 10);
        productGbc.anchor = GridBagConstraints.WEST;
        productGbc.fill = GridBagConstraints.HORIZONTAL;

        // ID
        productGbc.gridx = 0;
        productGbc.gridy = 0;
        productGbc.weightx = 0.3;
        productPanel.add(idLabel, productGbc);

        productGbc.gridx = 1;
        productGbc.weightx = 0.7;
        productPanel.add(idField, productGbc);

        // Name
        productGbc.gridx = 0;
        productGbc.gridy = 1;
        productGbc.weightx = 0.3;
        productPanel.add(nameLabel, productGbc);

        productGbc.gridx = 1;
        productGbc.weightx = 0.7;
        productPanel.add(nameField, productGbc);

        // Brand
        productGbc.gridx = 0;
        productGbc.gridy = 2;
        productGbc.weightx = 0.3;
        productPanel.add(brandLabel, productGbc);

        productGbc.gridx = 1;
        productGbc.weightx = 0.7;
        productPanel.add(brandField, productGbc);

        // Model
        productGbc.gridx = 0;
        productGbc.gridy = 3;
        productGbc.weightx = 0.3;
        productPanel.add(modelLabel, productGbc);

        productGbc.gridx = 1;
        productGbc.weightx = 0.7;
        productPanel.add(modelField, productGbc);

        // Add product panel to content panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        contentPanel.add(productPanel, gbc);

        // Create a panel for details
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBackground(SECONDARY_COLOR);
        detailsPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR),
                "Product Details",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                LABEL_FONT,
                PRIMARY_COLOR
        ));

        GridBagConstraints detailsGbc = new GridBagConstraints();
        detailsGbc.insets = new Insets(5, 10, 5, 10);
        detailsGbc.anchor = GridBagConstraints.WEST;
        detailsGbc.fill = GridBagConstraints.HORIZONTAL;

        // Description
        detailsGbc.gridx = 0;
        detailsGbc.gridy = 0;
        detailsGbc.weightx = 0.3;
        detailsPanel.add(descriptionLabel, detailsGbc);

        detailsGbc.gridx = 1;
        detailsGbc.weightx = 0.7;
        detailsGbc.fill = GridBagConstraints.BOTH;
        descriptionField.setPreferredSize(new Dimension(300, 100));
        detailsPanel.add(descriptionField, detailsGbc);
        detailsGbc.fill = GridBagConstraints.HORIZONTAL;

        // Add details panel to content panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        contentPanel.add(detailsPanel, gbc);

        // Create a panel for inventory
        JPanel inventoryPanel = new JPanel(new GridBagLayout());
        inventoryPanel.setBackground(SECONDARY_COLOR);
        inventoryPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR),
                "Inventory Information",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                LABEL_FONT,
                PRIMARY_COLOR
        ));

        GridBagConstraints inventoryGbc = new GridBagConstraints();
        inventoryGbc.insets = new Insets(5, 10, 5, 10);
        inventoryGbc.anchor = GridBagConstraints.WEST;
        inventoryGbc.fill = GridBagConstraints.HORIZONTAL;

        // Price
        inventoryGbc.gridx = 0;
        inventoryGbc.gridy = 0;
        inventoryGbc.weightx = 0.3;
        inventoryPanel.add(priceLabel, inventoryGbc);

        inventoryGbc.gridx = 1;
        inventoryGbc.weightx = 0.7;
        inventoryPanel.add(priceField, inventoryGbc);

        // Quantity
        inventoryGbc.gridx = 0;
        inventoryGbc.gridy = 1;
        inventoryGbc.weightx = 0.3;
        inventoryPanel.add(quantityLabel, inventoryGbc);

        inventoryGbc.gridx = 1;
        inventoryGbc.weightx = 0.7;
        inventoryPanel.add(quantityField, inventoryGbc);

        // Available
        inventoryGbc.gridx = 0;
        inventoryGbc.gridy = 2;
        inventoryGbc.weightx = 0.3;
        inventoryPanel.add(availableLabel, inventoryGbc);

        inventoryGbc.gridx = 1;
        inventoryGbc.weightx = 0.7;
        inventoryPanel.add(availableField, inventoryGbc);

        // Add inventory panel to content panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        contentPanel.add(inventoryPanel, gbc);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(SECONDARY_COLOR);
        buttonPanel.add(btnSave);
        buttonPanel.add(btnClear);
        buttonPanel.add(btnCancel);

        // Add button panel to content panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(buttonPanel, gbc);

        // Add the content panel to the main panel
        add(contentPanel, BorderLayout.CENTER);
    }

    private void setupListeners() {
        btnSave.addActionListener(e -> {
            Product product = getProductData();
            if (product != null) {
                try {
                    // If ID is not set, this is a new product
                    if (product.getId() == 0) {
                        product.saveToDatabase();
                        showCustomMessage("Product Saved", "Product saved successfully!", true);
                    } else {
                        // This is an existing product
                        product.updateProductInDatabase();
                        showCustomMessage("Product Updated", "Product updated successfully!", true);
                    }
                    clearFields();
                } catch (Exception ex) {
                    showCustomMessage("Error", "Failed to save product: " + ex.getMessage(), false);
                }
            }
        });

        btnClear.addActionListener(e -> {
            clearFields();
            // Provide subtle feedback
            btnClear.setBackground(new Color(80, 80, 80)); // Darker gray when clicked
            Timer timer = new Timer(200, evt -> btnClear.setBackground(new Color(100, 100, 100)));
            timer.setRepeats(false);
            timer.start();
        });

        btnCancel.addActionListener(e -> {
            // Ask for confirmation before navigating away
            int response = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to cancel? Any unsaved changes will be lost.",
                    "Confirm Cancel",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );

            if (response == JOptionPane.YES_OPTION) {
                // Navigate back to dashboard
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                currentFrame.getContentPane().removeAll();
                currentFrame.getContentPane().add(new DashboardPanel("Dashboard"));
                currentFrame.getContentPane().revalidate();
                currentFrame.getContentPane().repaint();
            }
        });

        // Add hover effects to buttons
        addButtonHoverEffect(btnSave);
        addButtonHoverEffect(btnClear);
        addButtonHoverEffect(btnCancel);
    }

    /**
     * Shows a custom styled message dialog
     * 
     * @param title The dialog title
     * @param message The message to display
     * @param isSuccess Whether this is a success message (true) or error message (false)
     */
    private void showCustomMessage(String title, String message, boolean isSuccess) {
        // Create a custom panel for the message
        JPanel messagePanel = new JPanel(new BorderLayout(10, 10));
        messagePanel.setBackground(Color.WHITE);
        messagePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Add an icon
        JLabel iconLabel = new JLabel(UIManager.getIcon(
                isSuccess ? "OptionPane.informationIcon" : "OptionPane.errorIcon"));
        messagePanel.add(iconLabel, BorderLayout.WEST);

        // Add a styled message
        String color = isSuccess ? "#007700" : "#770000";
        JLabel messageLabel = new JLabel("<html><div style='width: 250px'>" +
                "<p style='font-family: Segoe UI; font-size: 14px; color: " + color + ";'>" +
                title + "</p>" +
                "<p style='font-family: Segoe UI; font-size: 12px; color: #666666; margin-top: 10px;'>" +
                message + "</p>" +
                "</div></html>");
        messagePanel.add(messageLabel, BorderLayout.CENTER);

        // Show the custom dialog
        JOptionPane optionPane = new JOptionPane(
                messagePanel,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.DEFAULT_OPTION,
                null,
                new Object[]{"OK"},
                "OK");

        JDialog dialog = optionPane.createDialog(this, title);
        dialog.setBackground(Color.WHITE);
        dialog.setVisible(true);
    }

    /**
     * Adds hover effect to buttons
     * 
     * @param button The button to enhance with hover effects
     */
    private void addButtonHoverEffect(JButton button) {
        Color originalColor = button.getBackground();

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Brighten the button slightly when hovered
                button.setBackground(brightenColor(originalColor, 0.1f));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(originalColor);
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                // Darken the button slightly when pressed
                button.setBackground(darkenColor(originalColor, 0.1f));
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                if (button.contains(evt.getPoint())) {
                    button.setBackground(brightenColor(originalColor, 0.1f));
                } else {
                    button.setBackground(originalColor);
                }
            }
        });
    }

    /**
     * Brightens a color by the specified factor
     */
    private Color brightenColor(Color color, float factor) {
        int r = Math.min(255, (int)(color.getRed() * (1 + factor)));
        int g = Math.min(255, (int)(color.getGreen() * (1 + factor)));
        int b = Math.min(255, (int)(color.getBlue() * (1 + factor)));
        return new Color(r, g, b);
    }

    /**
     * Darkens a color by the specified factor
     */
    private Color darkenColor(Color color, float factor) {
        int r = Math.max(0, (int)(color.getRed() * (1 - factor)));
        int g = Math.max(0, (int)(color.getGreen() * (1 - factor)));
        int b = Math.max(0, (int)(color.getBlue() * (1 - factor)));
        return new Color(r, g, b);
    }

    // Method to set product data to the form
    public void setProductData(Product product) {
        if (product != null) {
            idField.setText(String.valueOf(product.getId()));
            nameField.setText(product.getName());
            brandField.setText(product.getBrand());
            modelField.setText(product.getModel());
            descriptionField.setText(product.getProductDescription());
            priceField.setText(String.valueOf(product.getPrice()));
            quantityField.setText(String.valueOf(product.getQuantity()));
            availableField.setSelected(product.isAvailable());
        }
    }

    // Method to get product data from the form
    public Product getProductData() {
        Product product = new Product();

        // Only set ID if it's not empty (for existing products)
        if (!idField.getText().trim().isEmpty()) {
            try {
                product.setId(Integer.parseInt(idField.getText().trim()));
            } catch (NumberFormatException e) {
                // Handle invalid ID format
                JOptionPane.showMessageDialog(this, "Invalid ID format", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }

        product.setName(nameField.getText().trim());
        product.setBrand(brandField.getText().trim());
        product.setModel(modelField.getText().trim());
        product.setProductDescription(descriptionField.getText().trim());

        try {
            product.setPrice(Double.parseDouble(priceField.getText().trim()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid price format", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        try {
            product.setQuantity(Integer.parseInt(quantityField.getText().trim()));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid quantity format", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        product.setAvailable(availableField.isSelected());

        return product;
    }

    // Method to clear all fields
    public void clearFields() {
        idField.setText("");
        nameField.setText("");
        brandField.setText("");
        modelField.setText("");
        descriptionField.setText("");
        priceField.setText("");
        quantityField.setText("");
        availableField.setSelected(true);
    }

    /**
     * Adds focus effects to text fields for better visual feedback
     *
     * @param textField The text field to enhance with focus effects
     */
    private void addFocusEffect(JTextField textField) {
        // Store the original border
        final Border originalBorder = textField.getBorder();

        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Change border color and background when focused
                textField.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(ACCENT_COLOR, 2),
                        BorderFactory.createEmptyBorder(4, 4, 4, 4)
                ));
                textField.setBackground(new Color(255, 255, 240)); // Light yellow background
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Restore the original border and background when focus is lost
                textField.setBorder(originalBorder);
                textField.setBackground(FIELD_BG_COLOR);
            }
        });

        // Add hover effect
        textField.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (!textField.hasFocus()) {
                    textField.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(180, 180, 180)),
                            BorderFactory.createEmptyBorder(5, 5, 5, 5)
                    ));
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!textField.hasFocus()) {
                    textField.setBorder(originalBorder);
                }
            }
        });
    }

    /**
     * Overloaded method to add focus effects to text areas
     *
     * @param textArea The text area to enhance with focus effects
     */
    private void addFocusEffect(JTextArea textArea) {
        // Store the original border
        final Border originalBorder = textArea.getBorder();

        textArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Change border color and background when focused
                textArea.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(ACCENT_COLOR, 2),
                        BorderFactory.createEmptyBorder(4, 4, 4, 4)
                ));
                textArea.setBackground(new Color(255, 255, 240)); // Light yellow background
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Restore the original border and background when focus is lost
                textArea.setBorder(originalBorder);
                textArea.setBackground(FIELD_BG_COLOR);
            }
        });

        // Add hover effect
        textArea.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (!textArea.hasFocus()) {
                    textArea.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(180, 180, 180)),
                            BorderFactory.createEmptyBorder(5, 5, 5, 5)
                    ));
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!textArea.hasFocus()) {
                    textArea.setBorder(originalBorder);
                }
            }
        });
    }
}
