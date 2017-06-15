
import exceptions.OccupiedCellException;
import model.Game;
import model.GameState;
import model.Seed;
import ui.Printer;
import ui.UserInterface;

import java.util.Arrays;
import java.util.InputMismatchException;

public class GameController {
    Game game;
    UserInterface ui;
    int currentMoveRow;
    int currentMoveCol;
    int gameSize;
    Printer printer;

    public void initGame() {
        gameSize = 4;
        game = new Game();
        game.initGame(gameSize);
        ui = new UserInterface();
        printer = new Printer();

    }

    public void chooseGameMode() {

    }

    public boolean getIsGameEnd() {
    return game.getCurrentState().equals(GameState.DRAW) || game.getCurrentState().equals(GameState.NOUGHT_WON)
            || game.getCurrentState().equals(GameState.CROSS_WON);
    }

    public void showCurrentPlayer() {
        printer.currentPlayerDislay(game);
    }

    public void getInputFromPlayer() {
        boolean isCorrectInput = false;

        while (!isCorrectInput) {
            boolean dimensionInputSwitch = true;
            int playerInputSwitch = 0;

            while (playerInputSwitch != 2) {
                try {
                   if(dimensionInputSwitch) {
                       printer.askForRowInput();
                       currentMoveRow = ui.getNumberFromPlayer(gameSize);
                       dimensionInputSwitch = false;
                   } else {
                       printer.askForColInput();
                       currentMoveCol = ui.getNumberFromPlayer(gameSize);
                   }
                    playerInputSwitch++;
                } catch (InputMismatchException e) {
                    printer.mismatchExceptionMessage();
                } catch (IllegalArgumentException e) {
                    printer.illegalArgumentExceptionMessage(gameSize);
                }
            }
            try {
                game.updateGameState(game.getCurrentPlayer(), currentMoveRow, currentMoveCol);
                isCorrectInput = true;
            }
            catch (OccupiedCellException e) {
                printer.occupiedCellExceptionMessage();
            }

        }
    }

    public void showGameStatus() {
        printer.gameBoardDisplay(game, gameSize);
    }

    public void showGameResults() {
    }
}
