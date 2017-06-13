package model;

public class Cell {
    private Seed content;
    private int row;
    private int col;

    public Cell(int row, int col) {
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

    public void clear(){
        this.content = Seed.EMPTY;
    }

    public Seed getContent() {
        return content;
    }

    public void setContent(Seed seed) {
        this.content = seed;
    }
}
