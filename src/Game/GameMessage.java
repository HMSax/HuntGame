package Game;

import javax.swing.*;

public class GameMessage {
    private JLabel currentMessage = new JLabel("");

    public GameMessage(){}
    public JLabel getCurrentMessage() {
        return currentMessage;
    }

    private void printIt(String message) {
        System.out.println(message);
        currentMessage.setText(message);
        currentMessage.revalidate();
        currentMessage.repaint();
    }
    public void welcome() {
        String welcome = "Welcome to HuntGame! You are the hunter. Catch the target! Don't let the target catch you!";
        printIt(welcome);

    }


    public void howTo() {
        String howTo = "Write A,S,D or W (and press enter) to move left(A),right(D),up(W) or down(S).";
        printIt(howTo);
    }

    public void tryAgain() {
        String tryAgain = "Try again! Use A,S,D or W.";
        printIt(tryAgain);
    }

    public void moveOutsideBoard() {
        String moveOutside = "You are trying to move outside the board or didn't write A,S,D or W. Try again!";
        printIt(moveOutside);
    }

    public void winner() {
        String winner = "You caught the target! Yay!";
        printIt(winner);
    }

    public void loser() {
        String loser = "The target caught YOU! Oh no!";
        printIt(loser);
    }

    public void playAgain() {
        String playAgain = "Play again? (Y/N) +enter";
        printIt(playAgain);
    }
    public void goodbye() {
        String goodbye = "Goodbye!";
        printIt(goodbye);
    }
}
