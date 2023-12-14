package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    // CardLayout
    private JPanel cardPanel = new JPanel(new CardLayout());

    // Main menu panel
    private JPanel menuPanel = new JPanel();
    private JButton newGameButton = new JButton("New Game");
    private JButton b2 = new JButton("Button 2");
    private JButton b3 = new JButton("Button 3");
    private JButton b4 = new JButton("Game Instructions");



    // Instruction panel.
    private JPanel instructionsPanel = new JPanel();
    private JTextArea instructionsText = new JTextArea("TExt:\n Text: \n Text: \n Text:");

    // Go back button and quit button (can resue)
    private JButton goBackButton = new JButton("Go Back");
    private JButton quitButton = new JButton("QUIT");

    public MainMenu() {
        // ActionListeners
        ActionListener exitListener = ae -> System.exit(0);
        ActionListener instructionsListener = ae -> showInstructionsPanel();
        ActionListener goBackListener = ae -> showMenuPanel();

        // Menu panel
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        menuPanel.add(newGameButton);
        menuPanel.add(b2);
        menuPanel.add(b3);
        menuPanel.add(b4);
        menuPanel.add(quitButton);
        newGameButton.setPreferredSize(new Dimension(200, 50));
        b2.setPreferredSize(new Dimension(200, 50));
        b3.setPreferredSize(new Dimension(200, 50));
        b4.setPreferredSize(new Dimension(200, 50));
        quitButton.setPreferredSize(new Dimension(200, 50));

        // Instructions panel
        instructionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        instructionsPanel.add(instructionsText);
        instructionsPanel.add(goBackButton);

        // Add panels to cardPanel
        cardPanel.add(menuPanel, "menu");
        cardPanel.add(instructionsPanel, "instructions");

        // add ActionListeners to buttons
        quitButton.addActionListener(exitListener);
        b4.addActionListener(instructionsListener);
        goBackButton.addActionListener(goBackListener);

        //
        showMenuPanel();
        add(cardPanel);

        //
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

    private void showInstructionsPanel() {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "instructions");
    }

    private void showMenuPanel() {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "menu");
    }
}
