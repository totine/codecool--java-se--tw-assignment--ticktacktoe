package model;


import exceptions.OccupiedCellException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
        game.initGame(3);
    }

    @Test
    void testInitGameBoardInitialization() {
        assertEquals(Board.class, game.getBoard().getClass());
    }

    @Test
    void testInitGameCurrentStateInitialization() {
        assertEquals(GameState.PLAYING, game.getCurrentState());
    }

    @Test
    void testUpdateGameState() throws OccupiedCellException {
        game.updateGameState(Seed.CROSS, 1, 1);
        game.updateGameState(Seed.CROSS, 2, 1);
        game.updateGameState(Seed.CROSS, 3, 1);
        assertEquals(GameState.CROSS_WON, game.getCurrentState());
    }

    @Test
    void testGetCurrentState(){
        assertEquals(GameState.PLAYING, game.getCurrentState());
    }

    @Test
    void testGetCurrentPlayer(){
        assertEquals(true,
                        game.getCurrentPlayer() == Seed.CROSS || game.getCurrentPlayer() == Seed.NOUGHT);
    }

    @Test
    void testGetOppositePlayer(){
        assertEquals(true, game.getCurrentPlayer() != game.getOppositePlayer());
    }
}