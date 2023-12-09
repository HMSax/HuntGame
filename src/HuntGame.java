import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class HuntGame extends JFrame {
    private JPanel gamePanel;
    private JButton[][] buttonBoard;

    public HuntGame() throws IOException, InterruptedException {

        initUI();

    }

    private void initUI() throws IOException, InterruptedException {
        setTitle("HuntGame");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());
        setResizable(false);

        gamePanel = new JPanel(new GridLayout(10, 10));
        GameBoard gameBoard = new GameBoard();
        buttonBoard = new JButton[10][10];
        createGrid(gameBoard.getGameBoard());
        add(gamePanel,BorderLayout.CENTER);

        String playAgain;
        do {                                                    //Sätter upp spelet
            gameBoard.setMarkerX(4, 0, Hunter.hunterMark);
            gameBoard.setTargetIT(4, 9, Target.targetMark);
            BufferedReader controller = new BufferedReader(new InputStreamReader(System.in));
            GameMessage.welcome();
            TimeUnit.SECONDS.sleep(3);
            boolean keepPlaying = true;
            while (keepPlaying) {
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

    private void createGrid(String[][] gameBoard) {
        gamePanel.removeAll();

        for(int i = 0; i < gameBoard.length; i++) {
            for(int j = 0; j < gameBoard[i].length; j++) {
                buttonBoard[i][j] = new JButton(gameBoard[i][j]);
                buttonBoard[i][j].setOpaque(true);
                buttonBoard[i][j].setBorderPainted(true);
                buttonBoard[i][j].setFocusPainted(false);
                buttonBoard[i][j].setContentAreaFilled(true);
                buttonBoard[i][j].setBackground(Color.WHITE);
                this.add(buttonBoard[i][j]);
            }
        }
        this.revalidate();
        this.repaint();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame game;
            try {
                game = new HuntGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

    }
}