public class App {
    private GameController gameController;
    private boolean isAppActive;

    App() {
        gameController = new GameController();
        isAppActive = true;
    }

    private void askToContinue() {
        isAppActive = gameController.playAnotherGame();
    }

    void gameInit() {
        while (isAppActive) {
            gameController.initGame();
            gameController.chooseGameMode();
            while (!gameController.getIsGameEnd()) {
                gameController.showCurrentPlayer();
                gameController.getInputFromPlayer();
                gameController.switchPlayers();
                gameController.showGameStatus();
            }
            gameController.showGameResults();
            askToContinue();
        }
    }
}