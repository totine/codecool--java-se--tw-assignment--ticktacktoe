package model;

public class Cell {
    private Seed content;
    private int row;
    private int col;

    Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.content = Seed.EMPTY;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    void clear(){
        this.content = Seed.EMPTY;
    }

    public Seed getContent() {
        return content;
    }

    void setContent(Seed seed) {
        this.content = seed;
    }
}