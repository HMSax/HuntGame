package GUI;

import Game.GameController;
import Game.GameModel;

import javax.swing.*;
import java.awt.*;

public class MainFrame {

    private final JFrame frame;

    public MainFrame(){
        frame = new JFrame("HuntGame");
        frame.setSize(600, 615);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        //frame.setResizable(false);
        frame.setFocusable(true);
        showMainMenu();
    }

    private void clear() {
        frame.getContentPane().removeAll(); // Remove all components
        frame.repaint();  // Repaint the frame
        frame.revalidate(); // Revalidate the frame layout
    }

    public void update() {
        frame.repaint();
        frame.revalidate();
    }

    public void showMainMenu(){
        SwingUtilities.invokeLater(() -> {
            clear();

            MainMenuView mainMenuView = new MainMenuView(this);
            frame.setSize(300,320);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(mainMenuView);
            frame.revalidate();
            frame.repaint();
        });
    }

    public void showGameView(){
        SwingUtilities.invokeLater(() -> {
            clear();
            GameModel model = new GameModel();
            GameView gameView = new GameView(this, model);
            GameController controller = new GameController(model, gameView);
            frame.setSize(615, 660);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(gameView);
            frame.revalidate();
            frame.repaint();
            gameView.requestFocus();
        });
    }
}
