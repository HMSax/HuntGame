package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuView extends JPanel {

    // CardLayout
    private JPanel cardPanel = new JPanel(new CardLayout());

    // Main menu panel
    private ImageIcon HGbanner = new ImageIcon("src/IconImages/HGBanner.png");
    private JLabel bannerLabel = new JLabel();
    private JPanel menuPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JButton newGameButton = new JButton("New Game");
    private JButton controlsButton = new JButton("Controls");
    private JButton aboutButton = new JButton("About");
    //private JButton instructionButton = new JButton("Game Instructions");
    private JButton quitButton = new JButton("QUIT");

    // Creator panel.
    private JPanel aboutPanel = new JPanel();
    private JTextArea aboutText = new JTextArea("TExt:\n Text: \n Text: \n Text:");
    private JButton aboutBackButton = new JButton("Go Back");

    // Instruction panel.
    //private JPanel instructionsPanel = new JPanel();
    //private JTextArea instructionsText = new JTextArea("TExt:\n Text: \n Text: \n Text:");
    //private JButton instructionsBackButton = new JButton("Go Back");

    // Controls panel
    private JPanel controlsPanel = new JPanel();
    private JTextArea controlsText = new JTextArea("TExt:\n Text: \n Text: \n Text:");
    private JButton controlsBackButton = new JButton("Go Back");
    private final MainFrame mainFrame;
    private GameView gameView;


    public MainMenuView(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        // ActionListeners
        ActionListener exitListener = ae -> System.exit(0);
        // ActionListener instructionsListener = ae -> showInstructionsPanel();
        ActionListener aboutListener = ae -> showAboutPanel();
        ActionListener goBackListener = ae -> showMenuPanel();
        ActionListener controlListener = ae -> showControlPanel();
        ActionListener newGameListener = ae -> startNewGame();


        // Menu panel
        menuPanel.setLayout(new GridLayout(0, 1));
        buttonPanel.setLayout(new GridLayout(2, 2));

        bannerLabel.setIcon(HGbanner);

        // button design
        newGameButton = mainFrame.buttonDesigner(newGameButton);
        controlsButton = mainFrame.buttonDesigner(controlsButton);
        aboutButton = mainFrame.buttonDesigner(aboutButton);
        //instructionButton.setPreferredSize(new Dimension(200, 50));
        quitButton = mainFrame.buttonDesigner(quitButton);

        menuPanel.add(bannerLabel);
        buttonPanel.add(newGameButton);
        buttonPanel.add(controlsButton);
        buttonPanel.add(aboutButton);
        //menuPanel.add(instructionButton);
        buttonPanel.add(quitButton);
        menuPanel.add(buttonPanel);

        // run newGame

        // Instructions panel
        //instructionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //instructionsPanel.add(instructionsText);
        //instructionsPanel.add(instructionsBackButton);

        // Aboutpanel
        aboutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        aboutPanel.setBackground(Color.BLACK);
        aboutBackButton = mainFrame.buttonDesigner(aboutBackButton);
        aboutPanel.add(aboutText);
        aboutPanel.add(aboutBackButton);

        // controls panel
        controlsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        controlsPanel.setBackground(Color.BLACK);
        controlsBackButton = mainFrame.buttonDesigner(controlsBackButton);
        controlsPanel.add(controlsText);
        controlsPanel.add(controlsBackButton);


        // Add panels to cardPanel
        cardPanel.add(menuPanel, "menu");
        //cardPanel.add(instructionsPanel, "instructions");
        cardPanel.add(aboutPanel, "About");
        cardPanel.add(controlsPanel, "controls");

        // add ActionListeners to buttons
        quitButton.addActionListener(exitListener);
        //instructionButton.addActionListener(instructionsListener);
        aboutButton.addActionListener(aboutListener);
        controlsButton.addActionListener(controlListener);
        newGameButton.addActionListener(newGameListener);

        // Go back buttons
        //instructionsBackButton.addActionListener(goBackListener);
        aboutBackButton.addActionListener(goBackListener);
        controlsBackButton.addActionListener(goBackListener);

        showMenuPanel();
        add(cardPanel);

        setSize(615, 400);
        setVisible(true);

    }

    public void setNewGameButtonAction(ActionListener actionListener) {
        newGameButton.addActionListener(actionListener);
    }
    // CardLayout methods

    public void startNewGame() {
        mainFrame.showGameView();
    }
    // private void showInstructionsPanel() {
    //    CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
    //   cardLayout.show(cardPanel, "instructions");
    //}

    private void showMenuPanel() {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "menu");
    }

    private void showAboutPanel() {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "About");
    }

    private void showControlPanel() {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "controls");
    }

}
