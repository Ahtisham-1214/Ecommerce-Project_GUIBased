package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class DashboardPanel extends JPanel {
    public DashboardPanel(String title) {
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


        // Add a card-like label with image
        JPanel cardPanel = createCardPanel();
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(cardPanel, gbc);

        // Set panel properties
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private JPanel createCardPanel() {
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
                currentFrame.getContentPane().add(new UserPanel("User Profile"));
                currentFrame.getContentPane().revalidate();
                currentFrame.getContentPane().repaint();
            }
        });
        return cardPanel;
    }
}
