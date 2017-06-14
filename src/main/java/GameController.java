
import exceptions.OccupiedCellException;
import model.Game;
import model.GameState;
import model.Seed;
import ui.UserInterface;

import java.util.Arrays;
import java.util.InputMismatchException;

public class GameController {
    Game game;
    UserInterface ui;
    int currentMoveRow;
    int currentMoveCol;
    int gameSize;

    public void initGame() {
        gameSize = 4;
        game = new Game();
        game.initGame(gameSize);
        ui = new UserInterface();

    }

    public void chooseGameMode() {

    }

    public boolean getIsGameEnd() {
    return game.getCurrentState().equals(GameState.DRAW) || game.getCurrentState().equals(GameState.NOUGHT_WON)
            || game.getCurrentState().equals(GameState.CROSS_WON);
    }

    public void showCurrentPlayer() {
        System.out.println("Current player: " + game.getCurrentPlayer());
    }

    public void getInputFromPlayer() {
        boolean isCorrectInput = false;

        while (!isCorrectInput) {
            System.out.println("Input row:");
            int a = 0;
            while (a == 0) {
                try {
                    currentMoveRow = ui.getNumberFromPlayer(gameSize);
                    a = 1;
                } catch (InputMismatchException e) {
                    System.out.println("Input number");
                } catch (IllegalArgumentException e) {
                    System.out.println("Number must be less than " + gameSize);
                }
            }
            a = 0;
            System.out.println("Input col:");
            while (a == 0) {
                try {
                    currentMoveCol = ui.getNumberFromPlayer(gameSize);
                    a = 1;
                } catch (InputMismatchException e) {
                    System.out.println("Input number");
                } catch (IllegalArgumentException e) {
                    System.out.println("Number must be less than " + gameSize);
                }
            }
            try {
                game.updateGameState(game.getCurrentPlayer(), currentMoveRow, currentMoveCol);
                isCorrectInput = true;
            }
            catch (OccupiedCellException e) {
                System.out.println("Cell is not empty, try again!");
            }

        }
    }

    public void showGameStatus() {
        for (int i = 0; i < gameSize; i++)
        System.out.println(Arrays.asList(Arrays.stream(game.getBoard().getCells()[i]).map(x -> x.getContent().visualisation()).toArray()));


    }

    public void showGameResults() {
    }
}
