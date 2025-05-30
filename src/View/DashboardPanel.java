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
        SwingUtilities.invokeLater(() -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (parentFrame != null) {
                parentFrame.setTitle(title);
            }
        });

        setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
        setBackground(new Color(81, 4, 4));

        // GridBagConstraints for layout management
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.NORTH;


        // Add card-like labels with images
        JPanel userCardPanel = createUserCardPanel();
        JPanel productCardPanel = createProductCardPanel();
        JPanel cartCardPanel = createCartCardPanel();

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
