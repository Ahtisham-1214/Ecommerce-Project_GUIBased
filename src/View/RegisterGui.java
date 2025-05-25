package View;

import Model.UserDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class RegisterGui extends BaseFrame {

    public RegisterGui() {
        super("Register");
        //set background color
//        getContentPane().setBackground(new Color(255, 204, 204));

        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/login.png")));
        setIconImage(icon.getImage());
        setSize(700, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    protected void addGuiComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20); // more padding

        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int y = 0;

        // Title
        JLabel titleLabel = new JLabel("Mobilink");
        titleLabel.setFont(new Font("Ravie", Font.BOLD, 30));
        gbc.gridx = 1;
        gbc.gridy = y++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;

        // Fields
        JTextField nameField = new JTextField(25);
        JTextField phoneField = new JTextField(25);
        JTextField addressField = new JTextField(25);
        JTextField emailField = new JTextField(25);
        JTextField usernameField = new JTextField(25);
        JPasswordField passwordField = new JPasswordField(25);
        JPasswordField rePasswordField = new JPasswordField(25);

        addLabelAndField("Full Name:", nameField, gbc, y++);
        addLabelAndField("Phone Number:", phoneField, gbc, y++);
        addLabelAndField("Address:", addressField, gbc, y++);
        addLabelAndField("Email:", emailField, gbc, y++);
        addLabelAndField("Username:", usernameField, gbc, y++);

        // Gender
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel("Gender:"), gbc);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JRadioButton maleButton = new JRadioButton("Male");
        JRadioButton femaleButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);

        gbc.gridx = 1;
        add(genderPanel, gbc);
        y++;

        addLabelAndField("Password:", passwordField, gbc, y++);
        addLabelAndField("Re-type Password:", rePasswordField, gbc, y++);

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Dialog", Font.BOLD, 18));
        gbc.gridx = 1;
        gbc.gridy = y++;
        gbc.gridwidth = 150;
        // Prevent the layout from stretching the button
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridwidth = 1;
        registerButton.setBackground(new Color(220, 20, 60)); // Crimson red
        registerButton.setPreferredSize(new Dimension(120, 40)); // Set width and height
        registerButton.setForeground(Color.WHITE);// White text
        registerButton.setFocusPainted(false); // Optional: no focus border
        registerButton.setBorder(BorderFactory.createEtchedBorder());
        add(registerButton, gbc);
        // Register Button
//        y += ySpacing;
//        JButton registerButton = new JButton("Register");
//        registerButton.setBounds(150, y, 150, 35);  // Slightly smaller button height
//        registerButton.setFont(new Font("Dialog", Font.BOLD, 18));  // Slightly smaller font
//
//

        // Login link
        gbc.gridy++;
        JLabel loginLabel = new JLabel("<html><center>Already have an account? <a href='' style='color:#0066CC;text-decoration:none'>Sign in here</a></center></html>");
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        loginLabel.setForeground(Color.BLACK);  // Set font color here
        add(loginLabel, gbc);

        getRootPane().setDefaultButton(registerButton); // Set default button to register button
        // Register Action
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());
            String rePassword = String.valueOf(rePasswordField.getPassword());
            String fullName = nameField.getText();
            String phone = phoneField.getText();
            String address = addressField.getText();
            String email = emailField.getText();
            String gender = maleButton.isSelected() ? "Male" :
                    femaleButton.isSelected() ? "Female" : "";

            if (fullName.isEmpty() || phone.isEmpty() || address.isEmpty() ||
                    email.isEmpty() || username.isEmpty() || password.isEmpty() ||
                    rePassword.isEmpty() || gender.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!fullName.matches("^[a-zA-Z]+$")) { // Regex for only Alphabetic name
                JOptionPane.showMessageDialog(this, "Invalid full name", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (!phone.matches("^[0-9]{11}$")) { // Regex for only numbers and 11 digits
                JOptionPane.showMessageDialog(this, "Invalid phone number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else if (!username.matches("^[a-zA-Z0-9]+$")) { // Regex for only Alphanumeric
                JOptionPane.showMessageDialog(null, "Username can only contain letters and numbers", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!password.matches("^[a-zA-Z0-9]+$")) {
                JOptionPane.showMessageDialog(null, "Password can only contain letters and numbers", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) { // Regex for only Email address
                JOptionPane.showMessageDialog(this, "Invalid email address", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!password.equals(rePassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (username.length() < 6) {
                JOptionPane.showMessageDialog(this, "Username must be at least 6 characters.");
                return;
            } else if (!username.matches("^[a-zA-Z0-9]+$")) { // Regex for only Alphanumeric
                JOptionPane.showMessageDialog(null, "Username can only contain letters and numbers", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!password.matches("^[a-zA-Z0-9]+$")) {
                JOptionPane.showMessageDialog(null, "Password can only contain letters and numbers", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (validateUserInput(username, password, rePassword)) {
                if (UserDatabase.register(username, email, password, fullName, phone, address)) {
                    this.dispose();
                    LoginGui loginGui = new LoginGui();
                    loginGui.setVisible(true);
                    JOptionPane.showMessageDialog(loginGui, "Registered Account Successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Username or Email already exists.");
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Error: Username must be at least 6 characters\nand/or Password must match");
            }
        });

        // Login link click
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterGui.this.dispose();
                new LoginGui().setVisible(true);
            }
        });

    }

    private void addLabelAndField(String labelText, JComponent field, GridBagConstraints gbc, int y) {
        gbc.gridx = 0;
        gbc.gridy = y;
        add(new JLabel(labelText), gbc);
        gbc.gridx = 1;
        add(field, gbc);
    }

    private boolean validateUserInput(String username, String password, String rePassword) {
        return username.length() >= 6 && password.equals(rePassword);
    }
}
