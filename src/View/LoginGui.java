package View;

import Model.UserDatabase;
import Backend.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

/*
    This gui will allow user to login or launch the register gui
    This extends from the BaseFrame which emans we will need to define our own addGuiComponent()
 */
public class LoginGui extends View.BaseFrame {
    public LoginGui() {
        super("Login");
        //set background color
//        getContentPane().setBackground(new Color(255, 204, 204));

        // Load the icon image
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/login.png")));
        // Replace with the actual path to your image file

        // Set the icon for the JFrame
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    protected void addGuiComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel titleLabel = new JLabel("Mobilink");
        titleLabel.setFont(new Font("Ravie", Font.BOLD, 32));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(titleLabel, gbc);


        gbc.gridwidth = 1;

        // Username
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(usernameLabel, gbc);

        gbc.gridx = 1;
        JTextField usernameField = new JTextField(20);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(usernameField, gbc);

        // Password field section
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 20));
        add(passwordField, gbc);

        // Login Button
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 2;
        // Prevent the layout from stretching the button
        gbc.fill = GridBagConstraints.NONE;
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Dialog", Font.BOLD, 20));
        loginButton.setPreferredSize(new Dimension(120, 40)); // Set width and height

        loginButton.setBackground(new Color(220, 20, 60)); // Crimson red
        loginButton.setForeground(Color.WHITE); // White text
        loginButton.setFocusPainted(false); // Optional: no focus border
        loginButton.setBorder(BorderFactory.createEtchedBorder());// width=100, height=40
        add(loginButton, gbc);


        // Register Label
        gbc.gridy++;
        JLabel registerLabel = new JLabel("<html><center>Don't have an account? <a href='' style='color:#0066CC;text-decoration:none'>Register here</a></center></html>");
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Hand cursor on hover
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        registerLabel.setForeground(Color.BLACK);  // Set font color here
        add(registerLabel, gbc);

        // ActionListener
        getRootPane().setDefaultButton(loginButton); // Set default button to login button
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = String.valueOf(passwordField.getPassword()).trim();

            if (username.isBlank() && password.isBlank()) {
                JOptionPane.showMessageDialog(null, "Please Enter Username and Password", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (username.isBlank()) {
                JOptionPane.showMessageDialog(null, "Please Enter Username", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (password.isBlank()) {
                JOptionPane.showMessageDialog(null, "Please Enter Password", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!username.matches("^[a-zA-Z0-9]+$")) { // Regex for only Alphanumeric
                JOptionPane.showMessageDialog(null, "Username can only contain letters and numbers", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!password.matches("^[a-zA-Z0-9]+$")) {
                JOptionPane.showMessageDialog(null, "Password can only contain letters and numbers", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                User user = UserDatabase.validateLogin(username, password);
                if (user != null) {
                    LoginGui.this.dispose();
//                    JOptionPane.showMessageDialog(null, "Login Successfully!");
//                     Close, login window
                    dispose();

                    // Open Dashboard Panel
                    SwingUtilities.invokeLater(() -> {
                        JFrame frame = new JFrame("MobiLink Mobile Store");
                        ImageIcon frameIcon = new ImageIcon("/resources/login.png");
                        frame.setIconImage(frameIcon.getImage());
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.getContentPane().add(new DashboardPanel("Dashboard"));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    });
                } else {
                    JOptionPane.showMessageDialog(LoginGui.this, "Login failed...", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginGui.this.dispose();
                new RegisterGui().setVisible(true);
            }
        });
    }
}