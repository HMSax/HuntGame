package Game;

import GUI.MainMenuView;

import javax.swing.*;

public class HuntGameMain {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            /*GameModel model = new GameModel();
            GameView view = new GameView(model);
            GameController controller = new GameController(model, view);

             */
            MainMenuView main = new MainMenuView();

        });
    }
}
