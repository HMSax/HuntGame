package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    JPanel borderPanel = new JPanel();
    JPanel menuPanel = new JPanel();
    JButton newGameButton = new JButton("New Game");
    JButton b2 = new JButton("Button 2");
    JButton b3 = new JButton("Button 3");
    JButton b4 = new JButton("Button 4");
    JButton quitButton = new JButton("QUIT");

    public MainMenu() {
        // ActionListeners
        ActionListener exitListener = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        };

        // Panels
        this.add(borderPanel);
        borderPanel.setLayout(new BorderLayout());
        borderPanel.add(menuPanel, BorderLayout.CENTER);
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // button size
        newGameButton.setPreferredSize(new Dimension(200, 50));
        b2.setPreferredSize(new Dimension(200, 50));
        b3.setPreferredSize(new Dimension(200, 50));
        b4.setPreferredSize(new Dimension(200, 50));
        quitButton.setPreferredSize(new Dimension(200, 50));

        // Add buttons to panel
        menuPanel.add(newGameButton);
        menuPanel.add(b2);
        menuPanel.add(b3);
        menuPanel.add(b4);
        menuPanel.add(quitButton);

        // add ActionListeners to buttons
        quitButton.addActionListener(exitListener);

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
