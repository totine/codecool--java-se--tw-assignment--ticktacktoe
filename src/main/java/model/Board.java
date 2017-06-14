package model;


import exceptions.OccupiedCellException;

import java.util.Arrays;

public class Board {
    private Cell cells[][];
    private BoardTriples boardTriples;
    private int boardSize;

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public Board(int boardSize) {
        this.boardSize = boardSize;
    }

    void init() {
        cells = new Cell[boardSize][boardSize];
        for (int i=0; i<boardSize;i++) {
            for (int j=0; j<boardSize; j++) {
                cells[i][j] = new Cell(i+1, j+1);
            }
        }
        boardTriples = new BoardTriples(this.cells, boardSize);
    }

    public void update(Seed seed, int row, int col) throws OccupiedCellException {
        if (!cells[row-1][col-1].getContent().equals(Seed.EMPTY))
            throw new OccupiedCellException("Cell is not empty!");
        cells[row-1][col-1].setContent(seed);
    }

    public Cell[][] getCells() {
        return cells;
    }

    public boolean hasWon(Seed seed, int row, int col)  {

        Seed[] pattern = new Seed[boardSize];
        Arrays.fill(pattern, seed);
        if (Arrays.equals(boardTriples.getCol(col), pattern) ||
                Arrays.equals(boardTriples.getRow(row), pattern))
              return true;
        if (row == col) {

            if (Arrays.equals(boardTriples.getDiagFromLeftUp(), pattern)) {
                return true;

            }
        }
        if (col == (boardSize+1)-row){

                if (Arrays.equals(boardTriples.getDiagFromLeftDown(), pattern))
                    return true;

        }
        return false;
    }

    public boolean isDraw() {
        for (int i = 0; i<boardSize; i++) {
            if (Arrays.stream(cells[i]).anyMatch(x -> x.getContent().equals(Seed.EMPTY)))
                return false;
        }
        return true;
    }


//    public boolean isDraw() {
//        return
//    }
//
//    private Seed[] toOneDimensionArray(Seed[][] arr) {
//        Seed[]
//    }
}

class BoardTriples {

    Cell[][] cells;
    int cellsSize;

    public BoardTriples(Cell[][] cells, int cellsSize) {
        this.cells = cells;
        this.cellsSize = cellsSize;
    }

    public Seed[] getRow(int row) {
        Seed[] seedRow = new Seed[cellsSize];
        for (int i=0; i<cellsSize; i++) {
            seedRow[i] = cells[row-1][i].getContent();
        }
        return seedRow;
    }



    public Seed[] getCol(int col) {
        Seed[] seedCol = new Seed[cellsSize];
        for (int i=0; i<cellsSize; i++) {
            seedCol[i] = cells[i][col-1].getContent();
        }

        return seedCol;
    }


    public Seed[] getDiagFromLeftUp()  {

        Seed[] diag1 = new Seed[cellsSize];
        for (int i=0; i<cellsSize; i++) {
            diag1[i] = cells[i][i].getContent();
        }
        return diag1;
    }

    public Seed[] getDiagFromLeftDown()  {

        Seed[] diag2 = new Seed[cellsSize];
        for (int i=0; i<3; i++) {
            diag2[i] = cells[i][(cellsSize-1)-i].getContent();
        }
        return diag2;
    }
}
