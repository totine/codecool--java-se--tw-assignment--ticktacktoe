
import exceptions.OccupiedCellException;
import model.Game;
import model.GameState;
import model.Seed;
import ui.Printer;
import model.*;
import ui.UserInterface;

public class GameController {
    private Game game;
    private UserInterface ui;
    private int gameSize;
    private GameMode gameMode;
    private Player firstPlayer;
    private Player secondPlayer;
    private Player currentPlayer;

    private Printer printer;

    void initGame() {
        printer = new Printer();
        ui = new UserInterface();
        this.gameSize = chooseGameBoardSize();
        game = new Game();
        game.initGame(gameSize);
    }

    void chooseGameMode() {
        printer.askForGameMode();
        int minInput = 1;
        int maxInput = gameSize > 3 ? 2 : 3;
        int choosenGameMode = ui.getInputFromTo(minInput, maxInput);
        gameMode = GameMode.getByOptionNumber(choosenGameMode);
    }

    void setPlayers() {
        firstPlayer = gameMode.getPlayer();
        secondPlayer = new PlayerReal(Seed.NOUGHT);
        currentPlayer = firstPlayer.getSeed().equals(game.getCurrentPlayer()) ? firstPlayer : secondPlayer;
    }

    boolean getIsGameEnd() {
        return game.getCurrentState().equals(GameState.DRAW) || game.getCurrentState().equals(GameState.NOUGHT_WON)
            || game.getCurrentState().equals(GameState.CROSS_WON);
    }

    void showCurrentPlayer() {
        printer.currentPlayerDislay(game);
    }

    void getInputFromPlayer() {
        PlayerInput playerInput = currentPlayer.getInputFromPlayer(game.getBoard());
        boolean isCorrectInput = false;
        while (!isCorrectInput) {
            try {
                game.updateGameState(playerInput.getSeed(), playerInput.getRow(), playerInput.getCol());
                isCorrectInput = true;
            } catch (OccupiedCellException e) {
                printer.occupiedCellExceptionMessage();
            }
    }
    }

    void showGameStatus() {
        printer.gameBoardDisplay(game, gameSize);
    }

    void showGameResults() {
        if (game.getCurrentState().equals(GameState.DRAW))
            printer.drawDisplay();
        else
            printer.winnerDisplay(game.getOppositePlayer());
    }


    void switchPlayers() {
        currentPlayer = firstPlayer.getSeed().equals(game.getCurrentPlayer()) ? firstPlayer : secondPlayer;
    }

    private int chooseGameBoardSize() {
        int minInput = 3;
        int maxInput = 15;
        printer.askForGameBoardSize();
        return ui.getInputFromTo(minInput,maxInput);
    }

    boolean playAnotherGame() {
        printer.askToPlayAgain();
        int minInput = 1;
        int maxInput = 2;
        int playAgainSwich = ui.getInputFromTo(minInput, maxInput);
        return playAgainSwich == 1;
    }
}
