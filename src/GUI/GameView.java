package GUI;

import Factory.GridComponent;
import Game.GameMessage;
import Game.GameModel;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {
    private final ImageIcon backgroundImage = new ImageIcon("src/IconImages/map2.jpg");
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel cardPanel = new JPanel(cardLayout);
    private JPanel gamePanel;
    private JLabel backgroundLabel;
    private JLabel messageLabel;
    private GameMessage message = new GameMessage();
    private GameModel model;
    private GridComponent hunter;
    private GridComponent target;



    public GameView (GameModel model){
        this.model = model;
        this.hunter = model.getHunter();
        this.target = model.getTarget();
        setTitle("HuntGame");
        setSize(615, 660);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());
        setResizable(false);
        this.setFocusable(true);

        gamePanel = new JPanel();
        backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setLayout(new GridLayout(10, 10));
        gamePanel.add(backgroundLabel);
        messageLabel = message.getCurrentMessage();
        add(messageLabel,BorderLayout.NORTH);
        message.welcome();
        add(gamePanel, BorderLayout.CENTER);
    }
    public void updateView(String[][] gameBoard) {
        backgroundLabel.removeAll();
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
        messageLabel.setText(message.getCurrentMessage().getText());
        backgroundLabel.revalidate();
        backgroundLabel.repaint();
    }

    /*private void paintGrid() {
        backgroundLabel.removeAll();
        String[][] board = gameBoard.getGameBoard();
        String onIndex;

        backgroundLabel.revalidate();
        backgroundLabel.repaint();
    }

     */

}
