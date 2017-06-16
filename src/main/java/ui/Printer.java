package ui;

import model.Game;
import model.Seed;

import java.text.MessageFormat;
import java.util.Arrays;

public class Printer {

    public void currentPlayerDislay(Game game){
        System.out.println("Current player: " + game.getCurrentPlayer());
    }

    void mismatchExceptionMessage() {
        System.out.println("Input number");
    }

    public void illegalArgumentExceptionMessage(int gameSize){
        System.out.println("Please provide number from 1 to " + gameSize);
    }
    public void occupiedCellExceptionMessage(){
        System.out.println("Cell is not empty, try again!");
    }

    public void gameBoardDisplay(Game game, int gameSize){
        for (int i = 0; i < gameSize; i++) {
            System.out.println(Arrays.asList(Arrays.stream(game
                                                            .getBoard()
                                                            .getCells()[i]).map(
                                                                    x -> x.getContent()
                                                                            .visualisation())
                                                                            .toArray()));
        }
    }

    public void winnerDisplay(Seed seed){
        System.out.println(seed + " won");
    }

    public void askForGameMode() {
        System.out.println("Choose game mode:");
        System.out.println("1. Player vs. Player");
        System.out.println("2. Player vs. Computer - easy");
        System.out.println("3. Player vs. Computer - hard");
    }

    public void drawDisplay() {
        System.out.println("It's draw!");
    }

    public void askForGameBoardSize(){
        System.out.println("Insert game board size you want to play on, from 3 to 10:");
        System.out.println("Note: hard mode is only for size 3 board");
    }

    public void askForNumberBetween(int minNumber, int maxNumber){
        System.out.println(MessageFormat.format("Enter number (between {0} and {1})", minNumber, maxNumber));
    }

    public void askToPlayAgain() {
        System.out.println("Want to play another game? Pick 1 = YES, 2 = NO");
    }

    void notEmptyCellMessage(int row, int col) {
        System.out.println("Cell " + row + " " + col + " is not empty!");
        System.out.println("Try again");
    }

    public void rowColRequestDisplay(String rowOrCol, int boardSize) {
        System.out.println(MessageFormat.format("Enter {0} number (between 1 and {1})", rowOrCol, boardSize));
    }

    public void artificialInteligenceMoveDisplay(int row, int col) {
        System.out.println(row);
        System.out.println(col);
    }

    public void getEmptyCellDisplay(int i) {
        System.out.println(i);
    }
}