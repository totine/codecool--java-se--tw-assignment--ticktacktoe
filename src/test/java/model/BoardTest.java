package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.intThat;
import static org.mockito.Mockito.mock;

class BoardTest {
    private Board board;
    private Cell array[][];
    private int columns;
    private int rows;
    private Cell cell;

    @BeforeEach
    void setUp() {
        board = new Board();

//        int columns = 3;
//        int rows = 3;
//        Cell array[][] = new Cell[columns][rows];
//        for (int col = 0; col < columns; col++) {
//            for (int row = 0; row < rows; row++) {
//                array[col][row] = cell;
//            }
        }

//        for (Cell col []: array) {
//            System.out.println(col.length);
//            for (Cell row : col) {
//                System.out.println(row.toString());
//
//            }
//        }


    @Test
    void testGetCells_returnsCells(){
        Cell[][] cells = new Cell[3][3];
        board.setCells(cells);
        assertEquals(cells, board.getCells());
    }

    @Test
    void testInit_AfterCall_CellsHaveThreeRows(){
        board.init();
        assertEquals(3, board.getCells().length);
    }

    @Test
    void testInit_AfterCall_CellsHaveThreeCols(){
        board.init();
        assertEquals(3, board.getCells()[0].length);
        assertEquals(3, board.getCells()[1].length);
        assertEquals(3, board.getCells()[2].length);
    }
//
    @Test
    void testInit_AfterCall_AllCellsAreEmpty(){
        board.init();
        assertTrue(Arrays.stream(board.getCells()[0]).allMatch(x -> x.getContent().equals(Seed.EMPTY)));
        assertTrue(Arrays.stream(board.getCells()[1]).allMatch(x -> x.getContent().equals(Seed.EMPTY)));
        assertTrue(Arrays.stream(board.getCells()[2]).allMatch(x -> x.getContent().equals(Seed.EMPTY)));
    }

    @Test
    void testInit_AfterCall_AllCellsHaveCorrectColAndRowValue() {
        board.init();
        int boardSize = 3;
        for (int i=0; i<boardSize; i++) {
            for(int j=0; j<boardSize; j++) {
                assertEquals(j+1, board.getCells()[i][j].getCol());
                assertEquals(i+1, board.getCells()[i][j].getRow());
            }
        }
    }

    @Test // winning conditions needed
    void test_isDraw(){
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                Cell expectedCell = array[col][row];
                Boolean isExpected = (!expectedCell.getContent().equals(Seed.EMPTY)); // if not EMPTY = true
                Cell actualCell = board.getCells()[col][row];
                Boolean isActual = (!actualCell.getContent().equals(Seed.EMPTY)); // if not EMPTY = true

                assertEquals(isExpected, isActual);
            }
        }
    }
}