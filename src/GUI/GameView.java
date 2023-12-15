package GUI;

import Factory.GridComponent;
import Game.GameMessage;
import Game.GameModel;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel {
    private final ImageIcon backgroundImage = new ImageIcon("src/IconImages/map2.jpg");
    private JPanel gamePanel;

    private JPanel topPanel;
    private JLabel backgroundLabel;
    private JLabel messageLabel;
    private JLabel winLabel;
    private JLabel lossLabel;

    private final GameModel model;
    private final GameMessage message = new GameMessage(new JLabel());
    private GridComponent hunter;
    private GridComponent target;
    private MainFrame mainFrame;
    private int wins = 0;
    private int losses = 0;


    public GameView (MainFrame mainFrame, GameModel model){
        this.mainFrame = mainFrame;
        this.model = model;
        this.message.welcome();
        this.hunter = model.getHunter();
        this.target = model.getTarget();
        model.setGameView(this);
        setSize(615, 685);
        setVisible(true);
        setLayout(new BorderLayout());
        this.setFocusable(true);


        gamePanel = new JPanel();
        backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setLayout(new GridLayout(10, 10));
        gamePanel.add(backgroundLabel);

        topPanel = new JPanel(new BorderLayout());

        JPanel scorePanel = new JPanel(new FlowLayout());
        winLabel = new JLabel("Wins: 0");
        lossLabel = new JLabel("Losses: 0");
        scorePanel.add(winLabel);
        scorePanel.add(lossLabel);

        messageLabel = message.getCurrentMessage();

        topPanel.add(scorePanel, BorderLayout.NORTH);
        topPanel.add(messageLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);

    }
    public void updateView(String[][] gameBoard) {
        backgroundLabel.removeAll();
        backgroundLabel.setLayout(new GridLayout(10, 10));
        String onIndex;

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard.length; j++) {
                onIndex = gameBoard[i][j];
                JButton button = new JButton();
                if (onIndex.equals(hunter.getCharMark())){
                    button.setIcon(hunter.getIcon());
                } else if (onIndex.equals(target.getCharMark())) {
                    button.setIcon(target.getIcon());
                } else {
                    button.setVisible(false);
                }
                button.setOpaque(true);
                button.setBorderPainted(true);
                button.setFocusPainted(false);
                button.setContentAreaFilled(true);
                button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                backgroundLabel.add(button);
            }
        }
        winLabel.setText("Wins: " + model.getWins());
        lossLabel.setText("Losses: " + model.getLosses());
        messageLabel.setText(model.getMessage().getCurrentMessage().getText());
        mainFrame.update();
        topPanel.revalidate();
        this.revalidate();
        this.repaint();
    }

    public void setWinLabel(JLabel winLabel) {
        //winLabel.setText();
    }

    public void setLossLabel(JLabel lossLabel) {
        this.lossLabel = lossLabel;
    }
}
