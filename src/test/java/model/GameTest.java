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
        Board boardMock = mock(Board.class);
        game.initGame();
        assertEquals(game.getBoard().equals(boardMock));
    }

    @Test
    void TestInitGameCurrentStateInitialization() {
        game.initGame();
        assertEquals(game.getCurrentState().equals(GameState.PLAYING));
    }

    @Test
    void updateGameState() {
        game.initGame();
        game.updateGameState(Seed.CROSS, 1, 1);
        game.updateGameState(Seed.CROSS, 2, 1);
        game.updateGameState(Seed.CROSS, 3, 1);

        assertEquals(GameState.CROSS_WON, game.getCurrentState());
    }
}