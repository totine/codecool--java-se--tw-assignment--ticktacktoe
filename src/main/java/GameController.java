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
        gameSize = 3;
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
    }

    public void getInputFromPlayer() {
        System.out.println("Input row:");
        int a = 0;
        while (a == 0) {
            try {
                currentMoveRow = ui.getNumberFromPlayer(gameSize);
                a = 1;
            } catch (InputMismatchException e) {
                System.out.println("Input number");
            }
            catch (IllegalArgumentException e) {
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
            }
            catch (IllegalArgumentException e) {
                System.out.println("Number must be less than " + gameSize);
            }
        }

        game.updateGameState(game.getCurrentPlayer(), currentMoveRow, currentMoveCol);
    }

    public void showGameStatus() {
        System.out.println(Arrays.asList(Arrays.stream(game.getBoard().getCells()[0]).map(x -> x.getContent() == Seed.CROSS ? "X" : x.getContent() == Seed.NOUGHT ? "O" : "").toArray()));
        System.out.println(Arrays.asList(Arrays.stream(game.getBoard().getCells()[1]).map(x -> x.getContent() == Seed.CROSS ? "X" : x.getContent() == Seed.NOUGHT ? "O" : "").toArray()));
        System.out.println(Arrays.asList(Arrays.stream(game.getBoard().getCells()[2]).map(x -> x.getContent() == Seed.CROSS ? "X" : x.getContent() == Seed.NOUGHT ? "O" : "").toArray()));


    }

    public void showGameResults() {
    }
}
