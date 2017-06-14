package model;

import java.util.Arrays;

public class Board {
    private Cell cells[][];
    private BoardTriples boardTriples;

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
        boardTriples = new BoardTriples(this.cells);
    }

    public Cell[][] getCells() {
        return cells;
    }

    public boolean hasWon(Seed seed, int row, int col) {
        cells[row-1][col-1].setContent(seed);
        Seed[] pattern = {seed, seed, seed};
        if (Arrays.equals(boardTriples.getCol(col), pattern) ||
                Arrays.equals(boardTriples.getRow(row), pattern))
              return true;
        if (row == col){
            if (Arrays.equals(boardTriples.getDiagFromLeftUp(), pattern)) {
            return true;
        }
        }
        if (col == 4-row){
            if (Arrays.equals(boardTriples.getDiagFromLeftDown(), pattern))
                return true;
        }
        return false;
    }










}

class BoardTriples {

    Cell[][] cells;

    public BoardTriples(Cell[][] cells) {
        this.cells = cells;
    }

    public Seed[] getRow(int row) {
        Seed[] seedRow = new Seed[3];
        for (int i=0; i<3; i++) {
            seedRow[i] = cells[row-1][i].getContent();
        }
        return seedRow;
    }



    public Seed[] getCol(int col) {
        Seed[] seedCol = new Seed[3];
        for (int i=0; i<3; i++) {
            seedCol[i] = cells[i][col-1].getContent();
        }

        return seedCol;
    }


    public Seed[] getDiagFromLeftUp() {
        Seed[] diag1 = new Seed[3];
        for (int i=0; i<3; i++) {
            diag1[i] = cells[i][i].getContent();
        }
        return diag1;
    }

    public Seed[] getDiagFromLeftDown() {
        Seed[] diag2 = new Seed[3];
        for (int i=0; i<3; i++) {
            diag2[i] = cells[i][2-i].getContent();
        }
        return diag2;
    }
}
