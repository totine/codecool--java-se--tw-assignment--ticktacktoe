
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
        printer = new Printer();
        ui = new UserInterface();
        this.gameSize = chooseGameBoardSize();
        game = new Game();
        game.initGame(gameSize);
    }

    public void chooseGameMode() {
        System.out.println("Choose game mode:");
        System.out.println("1. Player vs. Player");
        System.out.println("2. Player vs. Computer - easy");
        System.out.println("3. Player vs. Computer - hard");
        int minInput = 1;
        int maxInput = 2;
        int choosenGameMode = ui.getInputFromTo(minInput, maxInput);
        switch (choosenGameMode) {
            case 1:
                gameMode = GameMode.PLAYER_VS_PLAYER;
                firstPlayer = new PlayerReal(game.getCurrentPlayer());
                secondPlayer = new PlayerReal(game.getOppositePlayer());
                currentPlayer = firstPlayer;
                break;
            case 2:
                gameMode = GameMode.PLAYER_VS_AI_EASY;
                firstPlayer = new PlayerReal(game.getCurrentPlayer());
                secondPlayer = new PlayerAIEasy(game.getOppositePlayer());
                currentPlayer = firstPlayer;
                break;
            case 3:
                gameMode = GameMode.PLAYER_VS_AI_HARD;
                firstPlayer = new PlayerReal(game.getCurrentPlayer());
                secondPlayer = new PlayerAIHard(game.getOppositePlayer());
                currentPlayer = firstPlayer;
                break;
        }

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
                System.out.println("Current cell is not empty, try again");
                ;
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

    private int chooseGameBoardSize() {
        int minInput = 3;
        int maxInput = 10;
        printer.askForGameBoardSize();
        return ui.getInputFromTo(minInput,maxInput);
    }

    public boolean playAnotherGame() {
        System.out.println("Want to play another game? Pick 1 = YES, 2 = NO");
        int minInput = 1;
        int maxInput = 2;
        int playAgainSwich = ui.getInputFromTo(minInput, maxInput);
        return playAgainSwich == 1;
    }
}
