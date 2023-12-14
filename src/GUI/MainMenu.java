package GUI;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    JPanel borderPanel = new JPanel();
    JPanel menuPanel = new JPanel();
    JButton b = new JButton("New Game");
    JButton b2 = new JButton("B2");
    JButton b3 = new JButton("B3");
    JButton b4 = new JButton("B4");
    JButton b5 = new JButton("QUIT");

    public MainMenu() {
        // Panels
        this.add(borderPanel);
        borderPanel.setLayout(new BorderLayout());
        borderPanel.add(menuPanel, BorderLayout.CENTER);
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // buttons
        b.setPreferredSize(new Dimension(200, 50));
        b2.setPreferredSize(new Dimension(200, 50));
        b3.setPreferredSize(new Dimension(200, 50));
        b4.setPreferredSize(new Dimension(200, 50));
        b5.setPreferredSize(new Dimension(200, 50));

        menuPanel.add(b);
        menuPanel.add(b2);
        menuPanel.add(b3);
        menuPanel.add(b4);
        menuPanel.add(b5);

        setTitle("HuntGame");
        setSize(300, 320);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainMenu m = new MainMenu();
        });
    }
}
