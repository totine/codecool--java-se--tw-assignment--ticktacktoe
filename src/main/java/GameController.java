
import exceptions.OccupiedCellException;
import model.*;
import ui.Printer;
import ui.UserInterface;

public class GameController {
    private Game game;
    private UserInterface ui;
    private int gameSize;
    private GameMode gameMode;
    private Player firstPlayer;
    private Player secondPlayer;
    private Player currentPlayer;

    Printer printer;

    public void initGame() {
        gameSize = 3;
        game = new Game();
        game.initGame(gameSize);
        ui = new UserInterface();
        printer = new Printer();

    }

    public void chooseGameMode() {
        printer.askForGameMode();
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
        if (game.getCurrentState().equals(GameState.DRAW))
            printer.drawDisplay();
        else
            printer.winnerDisplay(game.getOppositePlayer());
    }


    public void switchPlayers() {
        currentPlayer = firstPlayer.getSeed().equals(game.getCurrentPlayer()) ? firstPlayer : secondPlayer;
    }
}

