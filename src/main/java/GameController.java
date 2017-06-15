
import exceptions.OccupiedCellException;
import model.Game;
import model.GameState;
import model.Seed;
import ui.Printer;
import model.*;
import ui.UserInterface;

import java.util.Arrays;
import java.util.InputMismatchException;

public class GameController {
    Game game;
    UserInterface ui;
    int currentMoveRow;
    int currentMoveCol;
    int gameSize;
    GameMode gameMode;
    Player firstPlayer;
    Player secondPlayer;
    Player currentPlayer;

    Printer printer;

    public void initGame() {
        gameSize = 3;
        game = new Game();
        game.initGame(gameSize);
        ui = new UserInterface();
        printer = new Printer();

    }

    public void chooseGameMode() {
        System.out.println("Choose game mode:");
        System.out.println("1. Player vs. Player");
        System.out.println("2. Player vs. Computer - easy");
        System.out.println("3. Player vs. Computer - hard");
        int optionCount = 3;
        int choosenGameMode = ui.getNumberFromPlayer(optionCount);
        gameMode = GameMode.getByOptionNumber(choosenGameMode);
    }

    public void setPlayers() {

           firstPlayer = gameMode.getPlayer();
           secondPlayer = new PlayerReal(Seed.NOUGHT);
           currentPlayer = firstPlayer.getSeed().equals(game.getCurrentPlayer()) ? firstPlayer : secondPlayer;



    }

    public boolean getIsGameEnd() {
    return game.getCurrentState().equals(GameState.DRAW) || game.getCurrentState().equals(GameState.NOUGHT_WON)
            || game.getCurrentState().equals(GameState.CROSS_WON);
    }

    public void showCurrentPlayer() {
        printer.currentPlayerDislay(game);
    }

    public void getInputFromPlayer() {
            PlayerInput playerInput = currentPlayer.getInputFromPlayer(game.getBoard());
            boolean isCorrectInput = false;
            while (!isCorrectInput) {
                try {
                    game.updateGameState(playerInput.getSeed(), playerInput.getRow(), playerInput.getCol());
                    isCorrectInput = true;
                } catch (OccupiedCellException e) {
                    System.out.println("Current cell is not empty, try again");;
                }
    }
    }

    public void showGameStatus() {
        printer.gameBoardDisplay(game, gameSize);
    }

    public void showGameResults() {
    }


    public void switchPlayers() {
        currentPlayer = firstPlayer.getSeed().equals(game.getCurrentPlayer()) ? firstPlayer : secondPlayer;
    }
}

