/**
 * Created by joanna on 14.06.17.
 */
public class App {
    GameController gameController;
    boolean isAppActive;

    private App() {
        gameController = new GameController();
        isAppActive = true;
    }

    public static void main(String[] args) {
        App app = new App();
        app.gameInit();

    }

    private void askToContinue() {

        isAppActive = false;
    }

    private void gameInit() {
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
