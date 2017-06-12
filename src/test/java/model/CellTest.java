package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {


    @Test
    void testCellConstructor_SetRow() {
        int row = 1;
        int col = 2;
        Cell cell = new Cell(row, col);
        assertEquals(row, cell.getRow());
    }

    @Test
    void testCellConstructor_SetCol() {
        int row = 1;
        int col = 2;
        Cell cell = new Cell(row, col);
        assertEquals(col, cell.getCol());
    }
}