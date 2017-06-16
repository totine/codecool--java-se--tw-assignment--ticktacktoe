package model;

import exceptions.OccupiedCellException;
import java.util.Arrays;

public class Board {
    private Cell cells[][];
    private BoardTriples boardTriples;
    private int boardSize;

    void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    Board(int boardSize) {
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

    void update(Seed seed, int row, int col) throws OccupiedCellException {
        if (!cells[row-1][col-1].getContent().equals(Seed.EMPTY))
            throw new OccupiedCellException("Cell is not empty!");
        cells[row-1][col-1].setContent(seed);
    }

    public Cell[][] getCells() {
        return cells;
    }

    boolean hasWon(Seed seed, int row, int col)  {

        Seed[] pattern = new Seed[boardSize];
        Arrays.fill(pattern, seed);
        if (Arrays.equals(boardTriples.getCol(col), pattern)
                || Arrays.equals(boardTriples.getRow(row), pattern))
              return true;
        if (row == col) {
            if (Arrays.equals(boardTriples.getDiagFromLeftUp(), pattern)) {
                return true;
            }
        }
        if (col == (boardSize+1)-row){
            if (Arrays.equals(boardTriples.getDiagFromLeftDown(), pattern)) {
                return true;
            }
        }
        return false;
    }

    boolean isDraw() {
        for (int i = 0; i<boardSize; i++) {
            if (Arrays.stream(cells[i]).anyMatch(x -> x.getContent().equals(Seed.EMPTY)))
                return false;
        }
        return true;
    }

    int getBoardSize() {
        return boardSize;
    }

    boolean isEmptyCell(int row, int col) {
        return !(row == 0 & col == 0) && cells[row-1][col-1].getContent().equals(Seed.EMPTY);
    }

    boolean isClear() {
        for (int i=0;i<boardSize;i++ ) {
            if (Arrays.stream(cells[i]).anyMatch(x -> !x.getContent().equals(Seed.EMPTY)))
            {
                return false;
            }
        }
        return true;
    }

    BoardTriples getBoardTriples() {
        return boardTriples;
    }
}