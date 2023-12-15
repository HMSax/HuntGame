package Game;

import Factory.GridComponent;
import Factory.GridComponentFactory;
import Factory.GridComponentTypes;
import GUI.GameView;

import javax.swing.*;
import java.io.IOException;

public class GameModel {
    private GameBoard gameBoard;
    private GridComponent hunter;
    private GridComponent target;
    private GameMessage message;
    private GameView gameView;

    public GameModel(){
        initializeGame();
    }
    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }
    public void initializeGame() {
        GridComponentFactory gridComponentFactory = new GridComponentFactory();
        hunter = gridComponentFactory.createGridComponent(GridComponentTypes.HUNTER);
        target = gridComponentFactory.createGridComponent(GridComponentTypes.TARGET);
        message = new GameMessage(new JLabel());
        message.welcome();
        gameBoard = new GameBoard(hunter.getCharMark(), target.getCharMark(), message, this.gameView);
        gameBoard.setMarkerX(4, 0, hunter.getCharMark());
        gameBoard.setTargetIT(4, 9, target.getCharMark());
    }

    public GameMessage getMessage() {
        return gameBoard.getMessage();
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
}