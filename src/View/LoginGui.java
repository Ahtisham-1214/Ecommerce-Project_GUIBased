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
        // Set a modern background color (light gray)
        getContentPane().setBackground(new Color(245, 245, 250));

        // Load the icon image
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/login.png")));
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    protected void addGuiComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;

        // Card panel for login form
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
                BorderFactory.createLineBorder(new Color(200, 200, 200), 2, true),
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
        titleLabel.setFont(new Font("Ravie", Font.BOLD, 32));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardPanel.add(titleLabel, c);

        c.gridwidth = 1;

        // Username
        c.gridy++;
        c.gridx = 0;
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        cardPanel.add(usernameLabel, c);

        c.gridx = 1;
        JTextField usernameField = new JTextField(20);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 20));
        cardPanel.add(usernameField, c);

        // Password
        c.gridy++;
        c.gridx = 0;
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        cardPanel.add(passwordLabel, c);

        c.gridx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 20));
        cardPanel.add(passwordField, c);

        // Login Button
        c.gridy++;
        c.gridx = 0;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.NONE;
        JButton loginButton = new JButton("Login") {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isRollover()) {
                    setBackground(new Color(200, 0, 40));
                } else {
                    setBackground(new Color(220, 20, 60));
                }
                super.paintComponent(g);
            }
        };
        loginButton.setFont(new Font("Dialog", Font.BOLD, 20));
        loginButton.setPreferredSize(new Dimension(120, 40));
        loginButton.setBackground(new Color(220, 20, 60));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 20, 60), 2, true),
                BorderFactory.createEmptyBorder(5, 20, 5, 20)));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cardPanel.add(loginButton, c);

        // Register Label
        c.gridy++;
        JLabel registerLabel = new JLabel("<html><center>Don't have an account? <a href='' style='color:#0066CC;text-decoration:none'>Register here</a></center></html>");
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        registerLabel.setForeground(Color.BLACK);
        cardPanel.add(registerLabel, c);

        // Add cardPanel to main frame
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(cardPanel, gbc);

        // ActionListener
        getRootPane().setDefaultButton(loginButton);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = String.valueOf(passwordField.getPassword()).trim();

            if (username.isBlank() && password.isBlank()) {
                JOptionPane.showMessageDialog(null, "Please Enter Username and Password", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (username.isBlank()) {
                JOptionPane.showMessageDialog(null, "Please Enter Username", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (password.isBlank()) {
                JOptionPane.showMessageDialog(null, "Please Enter Password", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!username.matches("^[a-zA-Z0-9]+$")) {
                JOptionPane.showMessageDialog(null, "Username can only contain letters and numbers", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!password.matches("^[a-zA-Z0-9]+$")) {
                JOptionPane.showMessageDialog(null, "Password can only contain letters and numbers", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                User user = UserDatabase.validateLogin(username, password);
                if (user != null) {
                    LoginGui.this.dispose();
                    dispose();
                    SwingUtilities.invokeLater(() -> {
                        JFrame frame = new JFrame("MobiLink Mobile Store");
                        ImageIcon frameIcon = new ImageIcon("/resources/login.png");
                        frame.setIconImage(frameIcon.getImage());
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.getContentPane().add(new DashboardPanel("Dashboard", user));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    });
                } else {
                    JOptionPane.showMessageDialog(LoginGui.this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
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