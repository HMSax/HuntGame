    package Game;

    import GUI.GameView;
    import GUI.MainFrame;

    import java.awt.event.KeyAdapter;
    import java.awt.event.KeyEvent;
    import java.io.IOException;

    public class GameController {
        private final GameModel model;
        private final GameView view;
        private final GameMessage message;
        private MainFrame mainFrame;
        public GameController(GameModel model, GameView view) {
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
                            } else {
                                model.moveTarget();
                                view.updateView(model.getGameBoard());
                            } if (model.checkLose()) {
                                message.loser();
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