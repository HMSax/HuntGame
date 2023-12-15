package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuView extends JPanel {

    // CardLayout
    private JPanel cardPanel = new JPanel(new CardLayout());

    // Main menu panel
    private ImageIcon HGbanner = new ImageIcon("src/IconImages/HGBanner.png");
    private JPanel menuPanel = new JPanel();
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
        ActionListener instructionsListener = ae -> showInstructionsPanel();
        ActionListener creatorsListener = ae -> showCreatorPanel();
        ActionListener goBackListener = ae -> showMenuPanel();
        ActionListener controlListener = ae -> showControlPanel();
        ActionListener newGameListener = ae -> startNewGame();

        // Menu panel
        menuPanel.setLayout(new GridLayout(0,1));
        menuPanel.add(newGameButton);
        menuPanel.add(controlsButton);
        menuPanel.add(aboutButton);
        //menuPanel.add(instructionButton);
        menuPanel.add(quitButton);

        // button size
        newGameButton.setPreferredSize(new Dimension(200, 50));
        controlsButton.setPreferredSize(new Dimension(200, 50));
        aboutButton.setPreferredSize(new Dimension(200, 50));
        //instructionButton.setPreferredSize(new Dimension(200, 50));
        quitButton.setPreferredSize(new Dimension(200, 50));

        // run newGame

        // Instructions panel
        //instructionsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //instructionsPanel.add(instructionsText);
        //instructionsPanel.add(instructionsBackButton);

        // Creator panel
        aboutPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        aboutPanel.add(aboutText);
        aboutPanel.add(aboutBackButton);

        // controls panel
        controlsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
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
        aboutButton.addActionListener(creatorsListener);
        controlsButton.addActionListener(controlListener);
        newGameButton.addActionListener(newGameListener);

        // Go back buttons
        //instructionsBackButton.addActionListener(goBackListener);
        aboutBackButton.addActionListener(goBackListener);
        controlsBackButton.addActionListener(goBackListener);

        showMenuPanel();
        add(cardPanel);

        setSize(615, 685);
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
