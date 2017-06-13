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

    @Test
    void testGetContent() {
        int row = 1;
        int col = 2;
        Cell cell = new Cell(row, col);
        assertEquals(Seed.EMPTY, cell.getContent());
    }

    @Test
    void testSetContent() {
        int row = 1;
        int col = 2;
        Cell cell = new Cell(row, col);
        cell.setContent(Seed.CROSS);
        assertEquals(col, cell.getCol());
    }

    @Test
    void clear() {
        int row = 1;
        int col = 2;
        Cell cell = new Cell(row, col);
        cell.setContent(Seed.CROSS);
        cell.clear();
        assertEquals(Seed.EMPTY, cell.getContent());
    }
}