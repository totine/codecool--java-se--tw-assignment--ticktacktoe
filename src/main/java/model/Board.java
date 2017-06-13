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

    public boolean hasWon(Seed seed, int row, int col) {
        if(isSeedCountLessThanThree(seed)){
            return false;
        }
        return true;
    }

    private boolean isSeedCountLessThanThree(Seed seed){
        int flag = 3;
        int seedCounter = 0;
        for (int i=0; i<cells.length;i++) {
            for (int j=0; j<cells[i].length; j++) {
                if(cells[i][j].getContent().equals(seed)) {
                    seedCounter++;
                    if (flag < seedCounter){
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
