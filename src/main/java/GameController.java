
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

    Printer printer;

    public void initGame() {
        printer = new Printer();
        ui = new UserInterface();
        this.gameSize = chooseGameBoardSize();
        game = new Game();
        game.initGame(gameSize);
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
