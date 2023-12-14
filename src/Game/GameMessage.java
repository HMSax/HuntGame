package Game;

import javax.swing.*;

public class GameMessage {
    JLabel currentMessage = new JLabel("");

    public GameMessage(){

    }

    public void welcome() {
        System.out.println("Welcome to Catch IT! You are Factory.Hunter X.");
    }

    public void howTo() {
        System.out.println("Write A,S,D or W (and press enter) to move the HX left(A),right(D),up(W) or down(S)\n " +
                            "and try to catch IT. WARNING: Don't let it catch you! You move before IT moves.");
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
