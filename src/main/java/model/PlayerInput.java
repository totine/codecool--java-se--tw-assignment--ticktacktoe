package model;

/**
 * Created by joanna on 15.06.17.
 */
public class PlayerInput {
    Seed seed;
    int row;
    int col;

    PlayerInput(Seed seed, int row, int col) {
        this.seed = seed;
        this.row = row;
        this.col = col;
    }

    public Seed getSeed() {
        return seed;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
