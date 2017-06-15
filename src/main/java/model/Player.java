package model;

public abstract class Player {
    String name;
    Seed seed;
    PlayerInput playerInput;

    public String getName() {
        return name;
    }

    public Seed getSeed() {
        return seed;
    }

    public void setSeed(Seed seed) {
        if (seed == Seed.EMPTY)
            throw new IllegalArgumentException();
        this.seed = seed;
    }

    public PlayerInput getInputFromPlayer(Board board) {
        return null;
    }
}
