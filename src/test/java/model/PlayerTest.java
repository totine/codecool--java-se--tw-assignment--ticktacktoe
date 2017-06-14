package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class PlayerTest {
    private String name;
    private Seed seed;

    @BeforeEach
    void setUp(){
        name = "Player1";
        seed = mock(Seed.class);

        Player player1 = new Player(name);
    }

    @Test
    void getPlayerName() {
        assertEquals(name, player1.getName());
    }

    @Test
    void getPlayerSeed(){
        assertEquals(player1.getSeed().equals(seed));
    }
}