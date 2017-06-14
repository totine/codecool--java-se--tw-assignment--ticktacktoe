package model;

public enum Seed {
    EMPTY(" "),
    CROSS("X"),
    NOUGHT("O");

    String visualisation;

    Seed(String vis) {
        visualisation = vis;
    }
    public String visualisation() {
        return visualisation;
    }
}
