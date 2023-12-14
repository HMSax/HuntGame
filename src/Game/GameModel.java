package Game;

import Factory.GridComponent;
import Factory.GridComponentFactory;
import Factory.GridComponentTypes;

import java.io.IOException;

public class GameModel {
    private GameBoard gameBoard;
    private GridComponent hunter;
    private GridComponent target;
    private GameMessage message;



    public GameModel(){
        initializeGame();
    }
    private void initializeGame() {
        GridComponentFactory gridComponentFactory = new GridComponentFactory();
        hunter = gridComponentFactory.createGridComponent(GridComponentTypes.HUNTER);
        target = gridComponentFactory.createGridComponent(GridComponentTypes.TARGET);
        gameBoard = new GameBoard(hunter.getCharMark(), target.getCharMark(), message);
        gameBoard.setMarkerX(4, 0, hunter.getCharMark());
        gameBoard.setTargetIT(4, 9, target.getCharMark());
    }
    public void moveHunter(String direction) throws IOException {
        gameBoard.moveMarker(direction);
    }

    public void moveTarget(){
        gameBoard.moveTarget();
    }

    public boolean checkWin(){
        return gameBoard.locationOfMarkerX().equals(gameBoard.getTargetLocation());
    }
    public boolean checkLose(){
        return gameBoard.locationOfTarget().equals(gameBoard.getMarkerLocation());
    }

    public String[][] getGameBoard(){
        return gameBoard.getGameBoard();
    }

    public GridComponent getHunter() {
        return hunter;
    }

    public GridComponent getTarget() {
        return target;
    }
    /*
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
                        JOptionPane.showMessageDialog(null, "You win!");
                    } else {
                        gameBoard.moveTarget();
                        paintGrid();
                        if (checkIfLose()){
                            JOptionPane.showMessageDialog(null, "You lose!");
                        }
                    }
                    paintGrid();
                    lastPressedKey = keyChar;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default: message.tryAgain();
        }
    }

     */
}