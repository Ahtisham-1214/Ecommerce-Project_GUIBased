package View;

import Backend.User;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class UserPanel extends JPanel {
    private User currentUser;

    // Color scheme
    private static final Color PRIMARY_COLOR = new Color(81, 4, 4);        // Dark red
    private static final Color SECONDARY_COLOR = new Color(245, 235, 235); // Light red background
    private static final Color ACCENT_COLOR = new Color(156, 37, 37);        // Red accent
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
    private JLabel userIdLabel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel emailLabel;
    private JLabel fullNameLabel;
    private JLabel phoneNumberLabel;
    private JLabel addressLabel;

    // Text fields
    private JTextField userIdField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField newPasswordField;
    private JPasswordField reNewPasswordField;
    private JTextField emailField;
    private JTextField fullNameField;
    private JTextField phoneNumberField;
    private JTextField addressField;

    // Buttons
    private JButton updateButton;
    private JButton updatePasswordButton;
    private JButton backButton;

    public UserPanel(String title) {
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
        createHeaderPanel(title);
        layoutComponents();
    }

    private void createHeaderPanel(String title) {
        headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(PRIMARY_COLOR);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        titleLabel = new JLabel("User Profile Management");
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

    public void setUser(User user) {
        this.currentUser = user;
        updateFields();
    }

    private void initializeComponents() {
        // Initialize labels with styling
        userIdLabel = new JLabel("User ID:");
        userIdLabel.setFont(LABEL_FONT);
        userIdLabel.setForeground(TEXT_COLOR);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(LABEL_FONT);
        usernameLabel.setForeground(TEXT_COLOR);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(LABEL_FONT);
        passwordLabel.setForeground(TEXT_COLOR);

        emailLabel = new JLabel("Email:");
        emailLabel.setFont(LABEL_FONT);
        emailLabel.setForeground(TEXT_COLOR);

        fullNameLabel = new JLabel("Full Name:");
        fullNameLabel.setFont(LABEL_FONT);
        fullNameLabel.setForeground(TEXT_COLOR);

        phoneNumberLabel = new JLabel("Phone Number:");
        phoneNumberLabel.setFont(LABEL_FONT);
        phoneNumberLabel.setForeground(TEXT_COLOR);

        addressLabel = new JLabel("Address:");
        addressLabel.setFont(LABEL_FONT);
        addressLabel.setForeground(TEXT_COLOR);

        // Initialize text fields with styling
        userIdField = new JTextField(15);
        userIdField.setEditable(false); // User ID should not be editable
        userIdField.setFont(FIELD_FONT);
        userIdField.setBackground(new Color(230, 230, 230)); // Darker background for non-editable field
        userIdField.setBorder(BorderFactory.createCompoundBorder(
                userIdField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        usernameField = new JTextField(15);
        usernameField.setFont(FIELD_FONT);
        usernameField.setBackground(FIELD_BG_COLOR);
        usernameField.setBorder(BorderFactory.createCompoundBorder(
                usernameField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        // Add focus listeners for visual feedback
        addFocusEffect(usernameField);

        passwordField = new JPasswordField(15);
        passwordField.setFont(FIELD_FONT);
        passwordField.setBackground(FIELD_BG_COLOR);
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                passwordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        addFocusEffect(passwordField);

        newPasswordField = new JPasswordField(15);
        newPasswordField.setFont(FIELD_FONT);
        newPasswordField.setBackground(FIELD_BG_COLOR);
        newPasswordField.setBorder(BorderFactory.createCompoundBorder(
                newPasswordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        addFocusEffect(newPasswordField);

        reNewPasswordField = new JPasswordField(15);
        reNewPasswordField.setFont(FIELD_FONT);
        reNewPasswordField.setBackground(FIELD_BG_COLOR);
        reNewPasswordField.setBorder(BorderFactory.createCompoundBorder(
                reNewPasswordField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        addFocusEffect(reNewPasswordField);

        emailField = new JTextField(15);
        emailField.setFont(FIELD_FONT);
        emailField.setBackground(FIELD_BG_COLOR);
        emailField.setBorder(BorderFactory.createCompoundBorder(
                emailField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        addFocusEffect(emailField);

        fullNameField = new JTextField(15);
        fullNameField.setFont(FIELD_FONT);
        fullNameField.setBackground(FIELD_BG_COLOR);
        fullNameField.setBorder(BorderFactory.createCompoundBorder(
                fullNameField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        addFocusEffect(fullNameField);

        phoneNumberField = new JTextField(15);
        phoneNumberField.setFont(FIELD_FONT);
        phoneNumberField.setBackground(FIELD_BG_COLOR);
        phoneNumberField.setBorder(BorderFactory.createCompoundBorder(
                phoneNumberField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        addFocusEffect(phoneNumberField);

        addressField = new JTextField(15);
        addressField.setFont(FIELD_FONT);
        addressField.setBackground(FIELD_BG_COLOR);
        addressField.setBorder(BorderFactory.createCompoundBorder(
                addressField.getBorder(),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        addFocusEffect(addressField);

        // Initialize buttons with styling
        updateButton = new JButton("Update Profile");
        updateButton.setFont(BUTTON_FONT);
        updateButton.setBackground(ACCENT_COLOR);
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);
        updateButton.setBorderPainted(false);
        updateButton.setOpaque(true);
        updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updateButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        updatePasswordButton = new JButton("Update Password");
        updatePasswordButton.setFont(BUTTON_FONT);
        updatePasswordButton.setBackground(PRIMARY_COLOR);
        updatePasswordButton.setForeground(Color.WHITE);
        updatePasswordButton.setFocusPainted(false);
        updatePasswordButton.setBorderPainted(false);
        updatePasswordButton.setOpaque(true);
        updatePasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        updatePasswordButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        // Add action listeners
        updateButton.addActionListener(e -> updateUserProfile());

        updatePasswordButton.addActionListener(e -> updateUserPassword());

        // Initialize back button with styling
        backButton = new JButton("Back");
        backButton.setFont(BUTTON_FONT);
        backButton.setBackground(ACCENT_COLOR);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setOpaque(true);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        // Add action listener for the back button
        backButton.addActionListener(e -> {
            // Go back to the previous screen
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(UserPanel.this);
            if (currentFrame != null) {
                currentFrame.getContentPane().removeAll();
                DashboardPanel dashboardPanel = new DashboardPanel("Dashboard", currentUser);
                currentFrame.getContentPane().add(dashboardPanel);
                currentFrame.getContentPane().revalidate();
                currentFrame.getContentPane().repaint();
            }
        });
    }

    private void layoutComponents() {
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

        // Create a panel for account info
        JPanel accountPanel = new JPanel(new GridBagLayout());
        accountPanel.setBackground(SECONDARY_COLOR);
        accountPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR),
                "Account Information",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                LABEL_FONT,
                PRIMARY_COLOR
        ));

        GridBagConstraints accountGbc = new GridBagConstraints();
        accountGbc.insets = new Insets(5, 10, 5, 10);
        accountGbc.anchor = GridBagConstraints.WEST;
        accountGbc.fill = GridBagConstraints.HORIZONTAL;

        // User ID
        accountGbc.gridx = 0;
        accountGbc.gridy = 0;
        accountGbc.weightx = 0.3;
        accountPanel.add(userIdLabel, accountGbc);

        accountGbc.gridx = 1;
        accountGbc.weightx = 0.7;
        accountPanel.add(userIdField, accountGbc);

        // Username
        accountGbc.gridx = 0;
        accountGbc.gridy = 1;
        accountGbc.weightx = 0.3;
        accountPanel.add(usernameLabel, accountGbc);

        accountGbc.gridx = 1;
        accountGbc.weightx = 0.7;
        accountPanel.add(usernameField, accountGbc);

        // Add an account panel to a content panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        contentPanel.add(accountPanel, gbc);

        // Create a panel for password management
        JPanel passwordPanel = new JPanel(new GridBagLayout());
        passwordPanel.setBackground(SECONDARY_COLOR);
        passwordPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR),
                "Password Management",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                LABEL_FONT,
                PRIMARY_COLOR
        ));

        GridBagConstraints passwordGbc = new GridBagConstraints();
        passwordGbc.insets = new Insets(5, 10, 5, 10);
        passwordGbc.anchor = GridBagConstraints.WEST;
        passwordGbc.fill = GridBagConstraints.HORIZONTAL;

        // Current Password
        passwordGbc.gridx = 0;
        passwordGbc.gridy = 0;
        passwordGbc.weightx = 0.3;
        passwordPanel.add(passwordLabel, passwordGbc);

        passwordGbc.gridx = 1;
        passwordGbc.weightx = 0.7;
        passwordPanel.add(passwordField, passwordGbc);

        // New Password
        passwordGbc.gridx = 0;
        passwordGbc.gridy = 1;
        passwordGbc.weightx = 0.3;
        JLabel newPasswordLabel = new JLabel("New Password:");
        newPasswordLabel.setFont(LABEL_FONT);
        newPasswordLabel.setForeground(TEXT_COLOR);
        passwordPanel.add(newPasswordLabel, passwordGbc);

        passwordGbc.gridx = 1;
        passwordGbc.weightx = 0.7;
        passwordPanel.add(newPasswordField, passwordGbc);

        // Re type new Password
        passwordGbc.gridx = 0;
        passwordGbc.gridy = 2;
        passwordGbc.weightx = 0.7;
        JLabel reNewPasswordLabel = new JLabel("Re-type New Password:");
        reNewPasswordLabel.setFont(LABEL_FONT);
        reNewPasswordLabel.setForeground(TEXT_COLOR);
        passwordPanel.add(reNewPasswordLabel, passwordGbc);

        passwordGbc.gridx = 1;
        passwordGbc.weightx = 0.7;
        passwordPanel.add(reNewPasswordField, passwordGbc);


        // Update Password Button
        passwordGbc.gridx = 1;
        passwordGbc.gridy = 3;
        passwordGbc.anchor = GridBagConstraints.EAST;
        passwordGbc.fill = GridBagConstraints.NONE;
        passwordPanel.add(updatePasswordButton, passwordGbc);

        // Add password panel to content panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        contentPanel.add(passwordPanel, gbc);

        // Create a panel for personal info
        JPanel personalPanel = new JPanel(new GridBagLayout());
        personalPanel.setBackground(SECONDARY_COLOR);
        personalPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(PRIMARY_COLOR),
                "Personal Information",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                LABEL_FONT,
                PRIMARY_COLOR
        ));

        GridBagConstraints personalGbc = new GridBagConstraints();
        personalGbc.insets = new Insets(5, 10, 5, 10);
        personalGbc.anchor = GridBagConstraints.WEST;
        personalGbc.fill = GridBagConstraints.HORIZONTAL;

        // Email
        personalGbc.gridx = 0;
        personalGbc.gridy = 0;
        personalGbc.weightx = 0.3;
        personalPanel.add(emailLabel, personalGbc);

        personalGbc.gridx = 1;
        personalGbc.weightx = 0.7;
        personalPanel.add(emailField, personalGbc);

        // Full Name
        personalGbc.gridx = 0;
        personalGbc.gridy = 1;
        personalGbc.weightx = 0.3;
        personalPanel.add(fullNameLabel, personalGbc);

        personalGbc.gridx = 1;
        personalGbc.weightx = 0.7;
        personalPanel.add(fullNameField, personalGbc);

        // Phone Number
        personalGbc.gridx = 0;
        personalGbc.gridy = 2;
        personalGbc.weightx = 0.3;
        personalPanel.add(phoneNumberLabel, personalGbc);

        personalGbc.gridx = 1;
        personalGbc.weightx = 0.7;
        personalPanel.add(phoneNumberField, personalGbc);

        // Address
        personalGbc.gridx = 0;
        personalGbc.gridy = 3;
        personalGbc.weightx = 0.3;
        personalPanel.add(addressLabel, personalGbc);

        personalGbc.gridx = 1;
        personalGbc.weightx = 0.7;
        personalPanel.add(addressField, personalGbc);

        // Add a personal panel to a content panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        contentPanel.add(personalPanel, gbc);

        // Update Profile Button and Back Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(SECONDARY_COLOR);
        buttonPanel.add(backButton);
        buttonPanel.add(updateButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(buttonPanel, gbc);

        // Add the content panel to the main panel
        add(contentPanel, BorderLayout.CENTER);
    }

    private void updateFields() {
        if (currentUser != null) {
            userIdField.setText(String.valueOf(currentUser.getId()));
            usernameField.setText(currentUser.getUsername());
            passwordField.setText(currentUser.getPassword());
            emailField.setText(currentUser.getEmail());
            fullNameField.setText(currentUser.getFullName());
            phoneNumberField.setText(currentUser.getPhone());
            addressField.setText(currentUser.getAddress());
        }
    }

    private void updateUserProfile() {
        try {
            currentUser.setUsername(usernameField.getText());
            currentUser.setFullName(fullNameField.getText());
            currentUser.setPhone(phoneNumberField.getText());
            currentUser.setAddress(addressField.getText());
            currentUser.setEmail(emailField.getText());

            currentUser.updateUser();


            // Create a custom panel for the message
            JPanel messagePanel = new JPanel(new BorderLayout(10, 10));
            messagePanel.setBackground(Color.WHITE);
            messagePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

            // Add an icon
            JLabel iconLabel = new JLabel(UIManager.getIcon("OptionPane.informationIcon"));
            messagePanel.add(iconLabel, BorderLayout.WEST);

            // Add a styled message
            JLabel messageLabel = new JLabel("<html><div style='width: 250px'>" +
                    "<p style='font-family: Segoe UI; font-size: 14px; color: #333333;'>" +
                    "Profile updated Successfully</p>" +
                    "<p style='font-family: Segoe UI; font-size: 12px; color: #666666; margin-top: 10px;'>" +
                    "Your profile information is updated in Database</p>" +
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

            JDialog dialog = optionPane.createDialog(this, "Update Profile");
            dialog.setBackground(Color.WHITE);
            dialog.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to Update profile " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void updateUserPassword() {
            try {
                String newPassword = newPasswordField.getText();
                String reNewPassword = reNewPasswordField.getText();

                // Create a custom panel for the message
                JPanel messagePanel = new JPanel(new BorderLayout(10, 10));
                messagePanel.setBackground(Color.WHITE);
                messagePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

                // Add an icon
                JLabel iconLabel = new JLabel(UIManager.getIcon("OptionPane.informationIcon"));
                messagePanel.add(iconLabel, BorderLayout.WEST);

                String message;
                if (newPassword.equals(reNewPassword)) {
                    message = "<html><div style='width: 250px'>" +
                            "<p style='font-family: Segoe UI; font-size: 14px; color: #00FF00;'>" +
                            "Password updated Successfully!</p>" +
                            "<p style='font-family: Segoe UI; font-size: 12px; color: #666666; margin-top: 10px;'>" +
                            "Your password is updated in Database</p>" +
                            "</div></html>";
                    currentUser.updateUser(newPassword);
                } else {
                    message = "<html><div style='width: 250px'>" +
                            "<p style='font-family: Segoe UI; font-size: 14px; color: #FF0000;'>" +
                            "Passwords do not match!</p>" +
                            "<p style='font-family: Segoe UI; font-size: 12px; color: #666666; margin-top: 10px;'>" +
                            "Please make sure both passwords are identical</p>" +
                            "</div></html>";
                }

                // Add a styled message
                JLabel messageLabel = new JLabel(message);
                messagePanel.add(messageLabel, BorderLayout.CENTER);

                // Show the custom dialog
                JOptionPane optionPane = new JOptionPane(
                        messagePanel,
                        JOptionPane.PLAIN_MESSAGE,
                        JOptionPane.DEFAULT_OPTION,
                        null,
                        new Object[]{"OK"},
                        "OK");

                JDialog dialog = optionPane.createDialog(this, "Update Password");
                dialog.setBackground(Color.WHITE);
                dialog.setVisible(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to Update password " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

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
}
