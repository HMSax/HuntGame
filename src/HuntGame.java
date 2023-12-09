import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class HuntGame extends JFrame {
    private final JPanel gamePanel;
    private GameBoard gameBoard;
    //private JButton[][] buttonBoard;

    public HuntGame() throws IOException, InterruptedException {
        setTitle("HuntGame");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());
        setResizable(false);

        gamePanel = new JPanel(new GridLayout(10, 10));
        gameBoard = new GameBoard();
        add(gamePanel,BorderLayout.CENTER);
        initGame();
    }

    private void initGame() throws InterruptedException, IOException {

        String playAgain;
        do {                                                    //Sätter upp spelet
            gameBoard.setMarkerX(4, 0, Hunter.hunterMark);
            gameBoard.setTargetIT(4, 9, Target.targetMark);
            BufferedReader controller = new BufferedReader(new InputStreamReader(System.in));
            GameMessage.welcome();
            TimeUnit.SECONDS.sleep(3);
            boolean keepPlaying = true;
            while (keepPlaying) {
                paintGrid();
                GameMessage.howTo();
                System.out.println(gameBoard);
                String aSDW = controller.readLine().toLowerCase().trim();
                boolean correctController = true;
                while (correctController) {
                    if (aSDW.equals("a") || aSDW.equals("s") || aSDW.equals("d") || aSDW.equals("w")) {
                        gameBoard.moveMarker(aSDW);
                        correctController = false;
                    } else {
                        GameMessage.tryAgain();
                        aSDW = controller.readLine().toLowerCase().trim();
                    }
                }
                if (gameBoard.locationOfMarkerX().equals(gameBoard.getTargetLocation())) {
                    System.out.println("\n");
                    System.out.println(gameBoard);
                    GameMessage.winner();
                    keepPlaying = false;
                } else {
                    gameBoard.moveTarget();
                }
                if (gameBoard.locationOfTarget().equals(gameBoard.getMarkerLocation())) {
                    System.out.println("\n");
                    System.out.println(gameBoard);
                    GameMessage.loser();
                    keepPlaying = false;
                }
            }
            GameMessage.playAgain();
            playAgain = controller.readLine().toLowerCase().trim();
        } while (playAgain.equals("yes"));
        GameMessage.goodbye();

    }

    private void paintGrid() {
        gamePanel.removeAll();
        String[][] board = gameBoard.getGameBoard();
        String onIndex;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                onIndex = board[i][j];
                JButton button = new JButton(onIndex);
                button.setOpaque(true);
                button.setBorderPainted(true);
                button.setFocusPainted(false);
                button.setContentAreaFilled(true);
                button.setBackground(Color.WHITE);
                button.setForeground(Color.BLACK);
                button.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
                gamePanel.add(button);
            }
        }
        gamePanel.revalidate();
        gamePanel.repaint();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HuntGame game = new HuntGame();

    }
}