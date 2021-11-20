package LessonDesign;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    JPanel backgroundPanel;
    public void BackgroundPanel()
    {
        backgroundPanel = new JPanel(){
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon(TheGUI.class.getResource("江苏大学.JPG"));
                img.paintIcon(this, g, 0, 0);
            }
        };
        backgroundPanel.setOpaque(false);
        setSize(800, 600);
    }
}
