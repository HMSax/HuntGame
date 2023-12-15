    package Game;

    import GUI.GameView;
    import GUI.MainFrame;

    import javax.swing.*;
    import java.awt.event.KeyAdapter;
    import java.awt.event.KeyEvent;
    import java.io.IOException;

    public class GameController {
        private final GameModel model;
        private final GameView view;
        private final GameMessage message;
        private MainFrame mainFrame;
        public GameController(GameModel model, GameView view, MainFrame mainFrame) {
            this.mainFrame = mainFrame;
            this.model = model;
            this.view = view;
            this.message = model.getMessage();

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
                            if (model.checkWin()){
                                message.winner();
                                int option = JOptionPane.showConfirmDialog(null,
                                        "Congratulations, you won!\nDo you want to play again?",
                                        "GAME OVER",
                                        JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.YES_OPTION) {
                                    model.initializeGame();
                                }else
                                    mainFrame.showMainMenu();
                            } else {
                                model.moveTarget();
                                view.updateView(model.getGameBoard());
                            } if (model.checkLose()) {
                                message.loser();
                                int option = JOptionPane.showConfirmDialog(null, "Sorry, you lost!\nDo you want to play again?",
                                        "GAME OVER",
                                        JOptionPane.YES_NO_OPTION);
                                if (option == JOptionPane.YES_OPTION){
                                    model.initializeGame();
                                }else
                                    mainFrame.showMainMenu();
                            }
                            view.updateView(model.getGameBoard());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            message.moveOutsideBoard();
                            throw new RuntimeException(ex);
                        }
                        return;
                    default: message.tryAgain();

                }
            }
        }
    }