package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuView extends JPanel {

    // CardLayout
    private JPanel cardPanel = new JPanel(new CardLayout());

    // Main menu panel
    private JPanel menuPanel = new JPanel();
    private JButton newGameButton = new JButton("New Game");
    private JButton controlsButton = new JButton("Controls");
    private JButton creatorsButton = new JButton("Creators");
    private JButton instructionButton = new JButton("Game Instructions");
    private JButton quitButton = new JButton("QUIT");

    // Creator panel.
    private JPanel creatorPanel = new JPanel();
    private JTextArea creatorText = new JTextArea("TExt:\n Text: \n Text: \n Text:");
    private JButton creatorBackButton = new JButton("Go Back");

    // Instruction panel.
    private JPanel instructionsPanel = new JPanel();
    private JTextArea instructionsText = new JTextArea("TExt:\n Text: \n Text: \n Text:");
    private JButton instructionsBackButton = new JButton("Go Back");

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
        ActionListener instructionsListener = ae -> showInstructionsPanel();
        ActionListener creatorsListener = ae -> showCreatorPanel();
        ActionListener goBackListener = ae -> showMenuPanel();
        ActionListener conrolListener = ae -> showControlPanel();
        ActionListener newGameListener = ae -> startNewGame();

        // Menu panel
        menuPanel.setLayout(new GridLayout(0,1));
        menuPanel.add(newGameButton);
        menuPanel.add(controlsButton);
        menuPanel.add(creatorsButton);
        menuPanel.add(instructionButton);
        menuPanel.add(quitButton);

        // button size
        newGameButton.setPreferredSize(new Dimension(200, 50));
        controlsButton.setPreferredSize(new Dimension(200, 50));
        creatorsButton.setPreferredSize(new Dimension(200, 50));
        instructionButton.setPreferredSize(new Dimension(200, 50));
        quitButton.setPreferredSize(new Dimension(200, 50));

        // run newGame

        // Instructions panel
        instructionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        instructionsPanel.add(instructionsText);
        instructionsPanel.add(instructionsBackButton);

        // Creator ponel
        creatorPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        creatorPanel.add(creatorText);
        creatorPanel.add(creatorBackButton);

        // controls panel
        controlsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        controlsPanel.add(controlsText);
        controlsPanel.add(controlsBackButton);


        // Add panels to cardPanel
        cardPanel.add(menuPanel, "menu");
        cardPanel.add(instructionsPanel, "instructions");
        cardPanel.add(creatorPanel, "creators");
        cardPanel.add(controlsPanel, "controls");

        // add ActionListeners to buttons
        quitButton.addActionListener(exitListener);
        instructionButton.addActionListener(instructionsListener);
        creatorsButton.addActionListener(creatorsListener);
        controlsButton.addActionListener(conrolListener);
        newGameButton.addActionListener(newGameListener);

        // Go back buttons
        instructionsBackButton.addActionListener(goBackListener);
        creatorBackButton.addActionListener(goBackListener);
        controlsBackButton.addActionListener(goBackListener);

        showMenuPanel();
        add(cardPanel);

        setSize(300, 320);
        setVisible(true);


    }
    public void setNewGameButtonAction(ActionListener actionListener) {
        newGameButton.addActionListener(actionListener);
    }
    // CardLayout methods

    public void startNewGame(){
        mainFrame.showGameView();
    }
    private void showInstructionsPanel() {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "instructions");
    }

    private void showMenuPanel() {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "menu");
    }
    private void showCreatorPanel() {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "creators");
    }
    private void showControlPanel() {
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
        cardLayout.show(cardPanel, "controls");
    }

}
