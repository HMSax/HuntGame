package Game;

import GUI.GameView;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class GameController {
    private final GameModel model;
    private final GameView view;
    private GameMessage message = new GameMessage();

    public GameController(GameModel model, GameView view) {
        this.model = model;
        this.view = view;

        addActionListeners();
        view.updateView(this.model.getGameBoard());
    }

    private void addActionListeners() {
        view.addKeyListener(new KeyController());
    }

    private class KeyController extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            char keyChar = e.getKeyChar();

            switch (Character.toLowerCase(keyChar)) {
                case 'w':
                case 'a':
                case 's':
                case 'd':
                    try {
                        model.moveHunter(Character.toString(keyChar));
                        message.howTo();
                        view.updateView(model.getGameBoard());
                        if (model.checkWin()) {
                            JOptionPane.showMessageDialog(null, "You win!");
                        } else{
                            model.moveTarget();
                            view.updateView(model.getGameBoard());
                            if (model.checkLose()) {
                                JOptionPane.showMessageDialog(null, "You lose!");
                            }
                        }
                        view.updateView(model.getGameBoard());

                    } catch (IOException ex) {
                        ex.printStackTrace();
                        throw new RuntimeException(ex);
                    }
                    break;
                default: message.tryAgain();

            }
        }
    }
}