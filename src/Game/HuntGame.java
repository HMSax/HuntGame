package Game;

import Factory.GridComponent;
import Factory.GridComponentFactory;
import Factory.GridComponentTypes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class HuntGame extends JFrame {

    private final ImageIcon backgroundImage = new ImageIcon("src/IconImages/map2.jpg");
    private final JPanel gamePanel;
    private JPanel topPanel;
    private final JLabel backgroundLabel;
    private JLabel messageLabel;
    private JLabel winLabel;
    private JLabel lossLabel;
    private GameBoard gameBoard;
    private GameMessage message;
    private GridComponent hunter;
    private GridComponent target;
    private char lastPressedKey;
    private int wins = 0;
    private int losses = 0;


    public HuntGame() throws IOException, InterruptedException {
        setTitle("Game.HuntGame");
        setSize(615, 685);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());
        setResizable(false);

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

        message = new GameMessage();
        messageLabel = message.getCurrentMessage();
        topPanel.add(scorePanel, BorderLayout.NORTH);
        topPanel.add(messageLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyChar());
                System.out.println(e.getKeyChar() + " Pressed");
            }
        });
        initGame();
        setFocusable(true);  // Se till att fönstret har fokus så att KeyListener fungerar
    }
    private void handleKeyPress(char keyChar) {
        switch (Character.toLowerCase(keyChar)) {
            case 'w':
            case 'a':
            case 's':
            case 'd':
                try {
                    gameBoard.moveMarker(Character.toString(keyChar));
                    paintGrid();
                    if (checkIfWin()){
                        int option = JOptionPane.showConfirmDialog(null, "Congratulations, you won!\nDo you want to play again?",
                                "GAME OVER",
                                JOptionPane.YES_NO_OPTION);
                        if (option == JOptionPane.YES_OPTION) {
                            playAgain();
                        }else {
                            System.exit(0);
                        }

                    } else {
                        gameBoard.moveTarget();
                        paintGrid();
                        if (checkIfLose()){
                            int option = JOptionPane.showConfirmDialog(null, "Sorry, you lost!\nDo you want to play again?",
                                    "GAME OVER",
                                    JOptionPane.YES_NO_OPTION);
                            if (option == JOptionPane.YES_OPTION){
                                playAgain();
                            }else
                                System.exit(0);
                        }
                    }
                    paintGrid();
                    lastPressedKey = keyChar;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
            default: message.tryAgain();
        }

    }
    private void playAgain() throws IOException, InterruptedException {
        GridComponentFactory gridComponentFactory = new GridComponentFactory();
        hunter = gridComponentFactory.createGridComponent(GridComponentTypes.HUNTER);
        target = gridComponentFactory.createGridComponent(GridComponentTypes.TARGET);
        gameBoard = new GameBoard(hunter.getCharMark(), target.getCharMark(), message);
        gameBoard.setMarkerX(4, 0, hunter.getCharMark());
        gameBoard.setTargetIT(4, 9, target.getCharMark());

        paintGrid();
        message.welcome();
        TimeUnit.SECONDS.sleep(1);
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
                    if (getLastPressedKey() == 'a' || getLastPressedKey() == 's' ||
                            getLastPressedKey() == 'd' || getLastPressedKey() == 'w') {
                        handleKeyPress(getLastPressedKey());
                        correctController = false;
                    } else {
                        message.tryAgain();
                        aSDW = controller.readLine().toLowerCase().trim();
                    }
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

    public boolean checkIfWin(){
        if (gameBoard.locationOfMarkerX().equals(gameBoard.getTargetLocation())) {
            message.winner();
            wins++;
            updateWinLossLabels();
            return true;
        }
        return false;
    }

    public boolean checkIfLose(){
        if (gameBoard.locationOfTarget().equals(gameBoard.getMarkerLocation())){
            message.loser();
            losses++;
            updateWinLossLabels();
            return true;
        }
        return false;
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
        backgroundLabel.revalidate();
        backgroundLabel.repaint();
    }

    public char getLastPressedKey() {
        return lastPressedKey;
    }
    private void updateWinLossLabels() {
        winLabel.setText("Wins: " + wins);
        lossLabel.setText("Losses: " + losses);
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        HuntGame game = new HuntGame();
    }
}