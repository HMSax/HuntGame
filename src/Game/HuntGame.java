package Game;

import Factory.GridComponent;
import Factory.GridComponentFactory;
import Factory.GridComponentTypes;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class HuntGame extends JFrame {

//    private final ImageIcon targetIcon = new ImageIcon("src/IconImages/TargetIconImage.png");
//    private final ImageIcon hunterIcon = new ImageIcon("src/IconImages/HunterIconImage.png");
    private final ImageIcon backgroundImage = new ImageIcon("src/IconImages/map2.jpg");
    private final JPanel gamePanel;
    private final JLabel backgroundLabel;
    private JLabel messageLabel;
    private GameBoard gameBoard;
    private GameMessage message;
    private GridComponent hunter;
    private GridComponent target;



    //private JButton[][] buttonBoard;

    public HuntGame() throws IOException, InterruptedException {
        setTitle("Game.HuntGame");
        setSize(615, 660);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());
        setResizable(false);

        gamePanel = new JPanel();
        backgroundLabel = new JLabel();
        backgroundLabel.setIcon(backgroundImage);
        backgroundLabel.setLayout(new GridLayout(10,10));
        gamePanel.add(backgroundLabel);

        message = new GameMessage();
        messageLabel = message.getCurrentMessage();

        add(messageLabel,BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        initGame();
    }

    private void initGame() throws InterruptedException, IOException {

        String playAgain;
        do {                                                    //Sätter upp spelet
            GridComponentFactory gridComponentFactory = new GridComponentFactory();
            hunter = gridComponentFactory.createGridComponent(GridComponentTypes.HUNTER);
            target = gridComponentFactory.createGridComponent(GridComponentTypes.TARGET);
            gameBoard = new GameBoard(hunter.getCharMark(), target.getCharMark(), message);
            gameBoard.setMarkerX(4, 0, hunter.getCharMark());
            gameBoard.setTargetIT(4, 9, target.getCharMark());

            BufferedReader controller = new BufferedReader(new InputStreamReader(System.in));
            message.welcome();
            TimeUnit.SECONDS.sleep(2);
            boolean keepPlaying = true;
            while (keepPlaying) {
                paintGrid();
                message.howTo();
                System.out.println(gameBoard);
                String aSDW = controller.readLine().toLowerCase().trim();
                boolean correctController = true;
                while (correctController) {
                    if (aSDW.equals("a") || aSDW.equals("s") || aSDW.equals("d") || aSDW.equals("w")) {
                        gameBoard.moveMarker(aSDW);
                        correctController = false;
                    } else {
                        message.tryAgain();
                        aSDW = controller.readLine().toLowerCase().trim();
                    }
                }
                if (gameBoard.locationOfMarkerX().equals(gameBoard.getTargetLocation())) {
                    System.out.println("\n");
                    System.out.println(gameBoard);
                    message.winner();
                    keepPlaying = false;
                } else {
                    gameBoard.moveTarget();
                }
                if (gameBoard.locationOfTarget().equals(gameBoard.getMarkerLocation())) {
                    System.out.println("\n");
                    System.out.println(gameBoard);
                    message.loser();
                    keepPlaying = false;
                }
            }
            paintGrid();
            TimeUnit.SECONDS.sleep(2);
            message.playAgain();
            playAgain = controller.readLine().toLowerCase().trim();
        } while (playAgain.equals("y"));
        message.goodbye();
    }

    private void paintGrid() {
        backgroundLabel.removeAll();
        String[][] board = gameBoard.getGameBoard();
        String onIndex;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                onIndex = board[i][j];
                JButton button = new JButton();
                if (onIndex.equals(hunter.getCharMark())) {
                    button.setIcon(hunter.getIcon());
                } else if (onIndex.equals(target.getCharMark())) {
                    button.setIcon(target.getIcon());
                }
                else { button.setVisible(false);}
                button.setOpaque(true);
                button.setBorderPainted(true);
                button.setFocusPainted(false);
                button.setContentAreaFilled(true);
                //button.setBackground(Color.WHITE);
                //button.setForeground(Color.BLACK);
                button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                backgroundLabel.add(button);
            }
        }
        backgroundLabel.revalidate();
        backgroundLabel.repaint();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HuntGame game = new HuntGame();

    }
}