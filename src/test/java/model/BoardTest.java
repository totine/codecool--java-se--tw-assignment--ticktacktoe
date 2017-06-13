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
        cell = mock(Cell.class);

        int columns = 3;
        int rows = 3;
        Cell array[][] = new Cell[columns][rows];
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                array[col][row] = cell;
            }
        }

//        for (Cell col []: array) {
//            System.out.println(col.length);
//            for (Cell row : col) {
//                System.out.println(row.toString());
//
//            }
//        }
        board = new Board();
    }

    @Test
    void test_init(){
        board.init();
        assertEquals(true, Arrays.deepEquals(Board.cells, array));
    }

    @Test // winning conditions needed
    void test_isDraw(){
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row < rows; row++) {
                Cell expectedCell = array[col][row];
                Boolean isExpected = (!expectedCell.getContent().equals(Seed.EMPTY)); // if not EMPTY = true
                Cell actualCell = Board.cells[col][row];
                Boolean isActual = (!actualCell.getContent().equals(Seed.EMPTY)); // if not EMPTY = true

                assertEquals(isExpected, isActual);
            }
        }
    }

}