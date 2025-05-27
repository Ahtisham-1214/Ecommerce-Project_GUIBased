package View;

import javax.swing.*;
import java.awt.*;

public class UserPanel extends JPanel {

    public UserPanel(String title) {
        setLayout(new GridBagLayout());
        SwingUtilities.invokeLater(() -> {
            JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (parentFrame != null) {
                parentFrame.setTitle(title);
            }
        });
    }
}
