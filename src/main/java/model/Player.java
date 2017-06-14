package model;

public class Player {
    String name;
    Seed seed;

    public Player(String name) {
        this.name = name;
    }

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
}
