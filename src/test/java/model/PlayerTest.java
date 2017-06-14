package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class PlayerTest {
    private String name;
    private Seed seed;
    private Player player1;

    @BeforeEach
    void setUp(){
        name = "Player1";
        seed = Seed.CROSS;
        player1 = new Player(name);
    }

    @Test
    void getPlayerName() {
        assertEquals(name, player1.getName());
    }

    @Test
    void getPlayerSeed(){
        player1.setSeed(seed);
        assertEquals(seed, player1.getSeed());
    }

    @Test
    void testSetSeed_IfSeedIsEMPTY_throwsException(){
        seed = Seed.EMPTY;
        assertThrows(IllegalArgumentException.class, () -> player1.setSeed(seed));
    }
}