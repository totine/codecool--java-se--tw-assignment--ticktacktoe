package ui;

import model.Game;
import model.Seed;

import java.util.Arrays;

public class Printer {
    public void currentPlayerDislay(Game game){
        System.out.println("Current player: " + game.getCurrentPlayer());
    }

    public void askForRowInput(){
        System.out.println("Input row:");
    }

    public void askForColInput(){
        System.out.println("Input col:");
    }
    public void mismatchExceptionMessage() {
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

    public void winnerDisplay(){
        System.out.println("draw");
    }
}
