package model;

public enum Seed {
    EMPTY(""),
    CROSS("x"),
    NOUGHT("o");

    String visualisation;

    private Seed(String vis) {
        visualisation = vis;
    }
}
