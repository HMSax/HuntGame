package Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GameBoard {
    String hunterMark;
    String targetMark;

    private String[][] gameBoard = {                                                             //Spelplanen
            {"[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]"},
            {"[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]"},
            {"[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]"},
            {"[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]"},
            {"[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]"},
            {"[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]"},
            {"[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]"},
            {"[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]"},
            {"[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]"},
            {"[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]", "[  ]"},
    };
    private String targetLocation = "  0 9";
    private String markerLocation = "  9 0";

    //constructor för spelplanen
    public GameBoard(String hunterMark, String targetMark) {
        this.gameBoard = gameBoard;
        this.hunterMark = hunterMark;
        this.targetMark = targetMark;
    }

    public String[][] getGameBoard() {
        return gameBoard;
    }

    public void setMarkerX(int indexx, int indexy, String marker) {     //Placerar X i arrayen
        this.gameBoard[indexx][indexy] = marker;
    }

    public String getMarkerLocation() {                                  //hämtar positionen för X
        return markerLocation;
    }

    public String locationOfMarkerX() {                                  //Hittar hunter i arrayen
        StringBuilder result = new StringBuilder(" ");
        for (int i = 0; i < this.gameBoard.length; i++) {
            for (int j = 0; j < this.gameBoard[i].length; j++) {
                if (this.gameBoard[i][j].equals(hunterMark)) {
                    result.append(" ").append(i);
                    result.append(" ").append(j);
                }
            }
        }
        this.markerLocation = result.toString();
        return result.toString();
    }

    public void moveMarker(String aSDW) throws InputMismatchException, IOException {            //Flyttar X åt något håller beroende på vad användaren anger.
        String asdw = aSDW.toLowerCase();
        Scanner scan = new Scanner(this.locationOfMarkerX());
        boolean tryAgain = true;
        int locX = scan.nextInt();
        int locY = scan.nextInt();
        while (tryAgain) {
            try {
                switch (asdw) {
                    case "s" -> this.gameBoard[locX + 1][locY] = hunterMark;
                    case "d" -> this.gameBoard[locX][locY + 1] = hunterMark;
                    case "w" -> this.gameBoard[locX - 1][locY] = hunterMark;
                    default -> this.gameBoard[locX][locY - 1] = hunterMark;
                }
                tryAgain = false;
            } catch (ArrayIndexOutOfBoundsException e) {
                GameMessage.moveOutsideBoard();
                BufferedReader control = new BufferedReader(new InputStreamReader(System.in));
                asdw = control.readLine();
            }
        }
        this.markerLocation = this.locationOfMarkerX();
        this.gameBoard[locX][locY] = "[  ]";
    }


    public void setTargetIT(int indexx, int indexy, String target) {    //Placerar IT i arrayen
        this.gameBoard[indexx][indexy] = target;
    }

    public String locationOfTarget() {                                  //Hittar IT i arrayen
        StringBuilder result = new StringBuilder(" ");
        for (int i = 0; i < this.gameBoard.length; i++) {
            for (int j = 0; j < this.gameBoard[i].length; j++) {
                if (this.gameBoard[i][j].equals(targetMark)) {
                    result.append(" ").append(i);
                    result.append(" ").append(j);
                }
            }
        }
        this.targetLocation = result.toString();
        return result.toString();
    }

    public String getTargetLocation() {                                 //Hämtar position för IT
        return targetLocation;
    }

    public void moveTarget() throws InputMismatchException {            //Flyttar IT slumpmässigt
        boolean tryAgain = true;
        Scanner scan = new Scanner(this.locationOfTarget());
        int locX = scan.nextInt();
        int locY = scan.nextInt();
        while (tryAgain) {
            double directionCounter = Math.random();
            try {
                if (directionCounter < 0.20) {
                    this.gameBoard[locX + 1][locY] = targetMark;
                    this.gameBoard[locX][locY] = "[  ]";
                } else if (directionCounter < 0.40) {
                    this.gameBoard[locX][locY + 1] = targetMark;
                    this.gameBoard[locX][locY] = "[  ]";
                } else if (directionCounter < 0.60) {
                    this.gameBoard[locX - 1][locY] = targetMark;
                    this.gameBoard[locX][locY] = "[  ]";
                } else if (directionCounter < 0.80) {
                    this.gameBoard[locX][locY - 1] = targetMark;
                    this.gameBoard[locX][locY] = "[  ]";
                } else {
                    this.gameBoard[locX][locY] = targetMark;
                }
                tryAgain = false;
            } catch (ArrayIndexOutOfBoundsException e) {
                tryAgain = true;
            }
        }
        this.targetLocation = this.locationOfTarget();
    }

    @Override
    public String toString() {
        String outString = "";
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                outString += " " + gameBoard[i][j];
            }
            outString += "\n";
        }
        return outString;
    }


}
