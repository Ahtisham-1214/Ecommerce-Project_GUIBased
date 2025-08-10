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
        setBackground(new Color(245, 245, 250));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;

        // Card panel for registration form
        JPanel cardPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.WHITE);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        cardPanel.setOpaque(false);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(30, 30, 30, 30)));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;

        // Logo
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon logoIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/login.png")));
        Image logoImg = logoIcon.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        logoLabel.setIcon(new ImageIcon(logoImg));
        cardPanel.add(logoLabel, c);

        // Title
        c.gridy++;
        JLabel titleLabel = new JLabel("Mobilink");
        titleLabel.setFont(new Font("Ravie", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardPanel.add(titleLabel, c);

        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;

        // Fields
        JTextField nameField = new JTextField(25);
        JTextField phoneField = new JTextField(25);
        JTextField addressField = new JTextField(25);
        JTextField emailField = new JTextField(25);
        JTextField usernameField = new JTextField(25);
        JPasswordField passwordField = new JPasswordField(25);
        JPasswordField rePasswordField = new JPasswordField(25);

        // Tooltips
        emailField.setToolTipText("Enter a valid email address");
        passwordField.setToolTipText("Password must be alphanumeric");
        rePasswordField.setToolTipText("Re-type your password");

        int y = 2;
        addLabelAndFieldToPanel("Full Name:", nameField, cardPanel, c, y++);
        addLabelAndFieldToPanel("Phone Number:", phoneField, cardPanel, c, y++);
        addLabelAndFieldToPanel("Address:", addressField, cardPanel, c, y++);
        addLabelAndFieldToPanel("Email:", emailField, cardPanel, c, y++);
        addLabelAndFieldToPanel("Username:", usernameField, cardPanel, c, y++);

        // Gender
        c.gridx = 0;
        c.gridy = y;
        cardPanel.add(new JLabel("Gender:"), c);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        JRadioButton maleButton = new JRadioButton("Male");
        JRadioButton femaleButton = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);

        c.gridx = 1;
        cardPanel.add(genderPanel, c);
        y++;

        addLabelAndFieldToPanel("Password:", passwordField, cardPanel, c, y++);
        addLabelAndFieldToPanel("Re-type Password:", rePasswordField, cardPanel, c, y++);

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Dialog", Font.BOLD, 18));
        c.gridx = 1;
        c.gridy = y++;
        c.gridwidth = 150;
        c.fill = GridBagConstraints.NONE;
        c.anchor = GridBagConstraints.CENTER;
        registerButton.setBackground(new Color(220, 20, 60));
        registerButton.setPreferredSize(new Dimension(120, 40));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorder(BorderFactory.createEtchedBorder());
        cardPanel.add(registerButton, c);
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor = GridBagConstraints.WEST;

        // Login link
        c.gridy++;
        JLabel loginLabel = new JLabel("<html><center>Already have an account? <a href='' style='color:#0066CC;text-decoration:none'>Sign in here</a></center></html>");
        loginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        loginLabel.setForeground(Color.BLACK);
        cardPanel.add(loginLabel, c);

        // Add cardPanel to main frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(cardPanel, gbc);

        getRootPane().setDefaultButton(registerButton);

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
            } else if (!fullName.matches("^[a-zA-Z]+( [a-zA-Z]+)*$")) {
                JOptionPane.showMessageDialog(this, "Invalid full name", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }else if (!phone.matches("^[0-9]{11}$")) {
                JOptionPane.showMessageDialog(this, "Invalid phone number", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else if (!username.matches("^[a-zA-Z0-9]+$")) {
                JOptionPane.showMessageDialog(null, "Username can only contain letters and numbers", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!password.matches("^[a-zA-Z0-9]+$")) {
                JOptionPane.showMessageDialog(null, "Password can only contain letters and numbers", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")) {
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
            } else if (!username.matches("^[a-zA-Z0-9]+$")) {
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

    // Helper to add label and field to card panel
    private void addLabelAndFieldToPanel(String labelText, JComponent field, JPanel panel, GridBagConstraints c, int y) {
        c.gridx = 0;
        c.gridy = y;
        panel.add(new JLabel(labelText), c);
        c.gridx = 1;
        panel.add(field, c);
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
