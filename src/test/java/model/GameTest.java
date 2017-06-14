package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GameTest {
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @Test
    void TestInitGameBoardInitialization() {
        game.initGame(3);
        assertEquals(Board.class, game.getBoard().getClass());
    }

    @Test
    void TestInitGameCurrentStateInitialization() {
        game.initGame(3);
        assertEquals(GameState.PLAYING, game.getCurrentState());
    }

    @Test
    void updateGameState() {
        game.initGame(3);
        game.updateGameState(Seed.CROSS, 1, 1);
        game.updateGameState(Seed.CROSS, 2, 1);
        game.updateGameState(Seed.CROSS, 3, 1);

        assertEquals(GameState.CROSS_WON, game.getCurrentState());
    }
}