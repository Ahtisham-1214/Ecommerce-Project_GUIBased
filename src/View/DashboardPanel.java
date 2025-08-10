package View;

import Backend.User;
import MobileManager.MobileManagementPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Objects;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DashboardPanel extends JPanel {
    private User currentUser;

    public DashboardPanel(String title) {
        this(title, null);
    }

    public DashboardPanel(String title, User user) {
        this.currentUser = user;
        setLayout(new GridBagLayout());
        setOpaque(false); // We'll paint a gradient background
        SwingUtilities.invokeLater(() -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (parentFrame != null) {
                parentFrame.setTitle(title);
            }
        });

        setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));

        // GridBagConstraints for layout management
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTH;

        // Welcome message
        String welcome = "Welcome" + (user != null && user.getFullName() != null ? ", " + user.getFullName() : "!");
        JLabel welcomeLabel = new JLabel(welcome);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        welcomeLabel.setForeground(new Color(81, 4, 4));
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        add(welcomeLabel, gbc);

        // Add card-like labels with images and hover effect
        JPanel userCardPanel = createHoverableCard(createUserCardPanel());
        JPanel productCardPanel = createHoverableCard(createProductCardPanel());
        JPanel cartCardPanel = createHoverableCard(createCartCardPanel());

        // Create a panel to hold all cards side by side
        JPanel cardsContainer = new JPanel(new GridLayout(1, 3, 20, 0));
        cardsContainer.setOpaque(false);
        cardsContainer.add(userCardPanel);
        cardsContainer.add(productCardPanel);
        cardsContainer.add(cartCardPanel);

        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(cardsContainer, gbc);

        // Set panel properties
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    // Paint a subtle vertical gradient background
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();
        Color color1 = new Color(255, 245, 245);
        Color color2 = new Color(245, 235, 235);
        GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

    // Wrap a card panel to add hover effect (scale and shadow)
    private JPanel createHoverableCard(JPanel card) {
        JPanel wrapper = new JPanel(new BorderLayout()) {
            float scale = 1.0f;
            boolean hovered = false;
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (hovered) {
                    g2.setColor(new Color(220, 220, 220, 80));
                    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
                }
                g2.dispose();
                super.paintComponent(g);
            }
        };
        wrapper.setOpaque(false);
        wrapper.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        wrapper.add(card, BorderLayout.CENTER);
        wrapper.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(156, 37, 37), 2),
                        BorderFactory.createEmptyBorder(13, 13, 13, 13)));
                wrapper.repaint();
            }
            @Override
            public void mouseExited(MouseEvent e) {
                card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                        BorderFactory.createEmptyBorder(15, 15, 15, 15)));
                wrapper.repaint();
            }
        });
        return wrapper;
    }

    private JPanel createUserCardPanel() {
        // Create a panel with border layout
        JPanel cardPanel = new JPanel(new BorderLayout(10, 10));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Add drop shadow effect
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(2, 2, 5, 5),
                        BorderFactory.createEmptyBorder(15, 15, 15, 15)
                )
        ));

        // Load and scale the image
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/user.png")));
        Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create text label
        JLabel textLabel = new JLabel("User");
        textLabel.setFont(new Font(textLabel.getFont().getName(), Font.BOLD, 16));
        textLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add components to the card panel
        cardPanel.add(imageLabel, BorderLayout.CENTER);
        cardPanel.add(textLabel, BorderLayout.SOUTH);

        // Set preferred size for the card
        cardPanel.setPreferredSize(new Dimension(150, 180));

        cardPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(DashboardPanel.this);
                currentFrame.getContentPane().removeAll();
                UserPanel userPanel = new UserPanel("User Profile");
                if (currentUser != null) {
                    userPanel.setUser(currentUser);
                }
                currentFrame.getContentPane().add(userPanel);
                currentFrame.getContentPane().revalidate();
                currentFrame.getContentPane().repaint();
            }
        });
        return cardPanel;
    }

    private JPanel createProductCardPanel() {
        // Create a panel with border layout
        JPanel cardPanel = new JPanel(new BorderLayout(10, 10));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Add drop shadow effect
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(2, 2, 5, 5),
                        BorderFactory.createEmptyBorder(15, 15, 15, 15)
                )
        ));

        // Load and scale the image
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/product.png")));
        Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create text label
        JLabel textLabel = new JLabel("Product");
        textLabel.setFont(new Font(textLabel.getFont().getName(), Font.BOLD, 16));
        textLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add components to the card panel
        cardPanel.add(imageLabel, BorderLayout.CENTER);
        cardPanel.add(textLabel, BorderLayout.SOUTH);

        // Set preferred size for the card
        cardPanel.setPreferredSize(new Dimension(150, 180));

        cardPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(DashboardPanel.this);
                currentFrame.getContentPane().removeAll();
                ProductPanel productPanel = new ProductPanel("Product Management");
                currentFrame.getContentPane().add(productPanel);
                currentFrame.getContentPane().revalidate();
                currentFrame.getContentPane().repaint();
            }
        });
        return cardPanel;
    }

    private JPanel createCartCardPanel() {
        // Create a panel with border layout
        JPanel cardPanel = new JPanel(new BorderLayout(10, 10));
        cardPanel.setBackground(Color.WHITE);
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        // Add drop shadow effect
        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createEmptyBorder(2, 2, 5, 5),
                        BorderFactory.createEmptyBorder(15, 15, 15, 15)
                )
        ));

        // Load and scale the image
        ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/resources/cart.png")));
        Image image = imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(image));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);

        // Create text label
        JLabel textLabel = new JLabel("Cart");
        textLabel.setFont(new Font(textLabel.getFont().getName(), Font.BOLD, 16));
        textLabel.setHorizontalAlignment(JLabel.CENTER);

        // Add components to the card panel
        cardPanel.add(imageLabel, BorderLayout.CENTER);
        cardPanel.add(textLabel, BorderLayout.SOUTH);

        // Set preferred size for the card
        cardPanel.setPreferredSize(new Dimension(150, 180));

        cardPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Launch the CartFrame using its main method
                SwingUtilities.invokeLater(() -> {
                    try {
                        // Get the CartFrame class using reflection
                        Class<?> cartFrameClass = Class.forName("CartFrame");
                        // Invoke its main method
                        cartFrameClass.getMethod("main", String[].class).invoke(null, (Object) new String[0]);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(DashboardPanel.this, 
                            "Error opening cart: " + ex.getMessage(), 
                            "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                });
            }
        });
        return cardPanel;
    }
}
