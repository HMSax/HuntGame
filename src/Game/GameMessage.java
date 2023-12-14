package Game;

import javax.swing.*;
import java.awt.desktop.ScreenSleepEvent;
import java.util.concurrent.TimeUnit;

public class GameMessage {
    JLabel currentMessage = new JLabel("");

    public GameMessage(){

    }
    public JLabel getCurrentMessage() {
        return currentMessage;
    }

    public void welcome() {
        System.out.println("Welcome to Catch IT! You are the hunter. Catch the target!");
        currentMessage.setText("Welcome to Catch IT! You are the hunter. Catch the target!");
        currentMessage.revalidate();
        currentMessage.repaint();

    }

    public void howTo() {
        System.out.println("Write A,S,D or W (and press enter) to move left(A),right(D),up(W) or down(S)\n " +
                            "WARNING: Don't let the target catch you! You move before the target moves.");
    }

    public void tryAgain() {
        System.out.println("Try again! Use A,S,D or W.");
    }

    public void moveOutsideBoard() {
        System.out.println("You are trying to move outside the board or didn't write A,S,D or W. Try again!");
    }

    public void winner() {
        System.out.println("You caught IT! Yay!");
    }

    public void loser() {
        System.out.println("It caught YOU! Oh no!");
    }

    public void playAgain() {
        System.out.println("Play again? (Y/N)");
    }
    public void goodbye() {
        System.out.println("Goodbye!");
    }
}
