package model;

public class Board {
    private Cell cells[][];

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    void init() {
        int boardSize = 3;
        cells = new Cell[boardSize][boardSize];
        for (int i=0; i<boardSize;i++) {
            for (int j=0; j<boardSize; j++) {
                cells[i][j] = new Cell(i+1, j+1);
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }
}
