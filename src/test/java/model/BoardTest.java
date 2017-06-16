package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;

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

    @Nested
    @DisplayName("Init Tests")
    class InitTests {
        @BeforeEach
        void setUp() {
            board = new Board(3);
        }

        @Test
        void testGetCells_returnsCells() {
            Cell[][] cells = new Cell[3][3];
            board.setCells(cells);
            assertEquals(cells, board.getCells());
        }

        @Test
        void testInit_AfterCall_CellsHaveThreeRows() {
            board.init();
            assertEquals(3, board.getCells().length);
        }

        @Test
        void testInit_AfterCall_CellsHaveThreeCols() {
            board.init();
            assertAll(
                    () -> assertEquals(3, board.getCells()[0].length),
                    () -> assertEquals(3, board.getCells()[1].length),
                    () -> assertEquals(3, board.getCells()[2].length));
        }

        //
        @Test
        void testInit_AfterCall_AllCellsAreEmpty() {
            board.init();
            assertTrue(Arrays.stream(board.getCells()[0]).allMatch(x -> x.getContent().equals(Seed.EMPTY)));
            assertTrue(Arrays.stream(board.getCells()[1]).allMatch(x -> x.getContent().equals(Seed.EMPTY)));
            assertTrue(Arrays.stream(board.getCells()[2]).allMatch(x -> x.getContent().equals(Seed.EMPTY)));
        }

        @Test
        void testInit_AfterCall_AllCellsHaveCorrectColAndRowValue() {
            board.init();
            int boardSize = 3;
            for (int i = 0; i < boardSize; i++) {
                for (int j = 0; j < boardSize; j++) {
                    assertEquals(j + 1, board.getCells()[i][j].getCol());
                    assertEquals(i + 1, board.getCells()[i][j].getRow());
                }
            }
        }

        @Test
        void testGetBoardSize(){
            int expectedBoardSize = 3;
            assertEquals(expectedBoardSize, board.getBoardSize());
        }

        @Test
        void testIsEmptyCell(){
            board.init();
            assertEquals(true, board.isEmptyCell(1,1));
        }

        @Test
        void testGetBoardTriples(){
            board.init();
            assertEquals(BoardTriples.class, board.getBoardTriples().getClass());
        }

        @Test
        void testIsClear_ReturnTrue(){
            board.init();
            assertEquals(true, board.isClear());
        }

        @Test
        void testIsClear_ReturnFalse(){
            board.init();
            board.getCells()[2][2].setContent(Seed.CROSS);

            assertEquals(false , board.isClear());
        }

        @Nested
        class HasWonTests {
            @BeforeEach
            void setupBeforeHasWonTests() {
                rows = 3;
                columns = 3;
                Cell cells[][] = new Cell[rows][columns];
                for (int row = 0; row < rows; row++) {
                    for (int col = 0; col < columns; col++) {
                        cells[row][col] = new Cell(row + 1, col + 1);
                    }
                }
                board.setCells(cells);
                board.init();
            }

            @Test
            void testHasWon_IfFirstTryOnEmptyBoard_returnsFalse() {
                assertEquals(false, board.hasWon(Seed.CROSS, 1, 1));
            }

            @Test
            void testHasWon_IfSeedCountIsLessThanThree_returnsFalse() {

                board.getCells()[2][2].setContent(Seed.CROSS);

                assertEquals(false, board.hasWon(Seed.CROSS, 1, 1));
            }

            @Test
            void testHasWon_IfWinningConditionMetInRow() {

                board.getCells()[1][1].setContent(Seed.CROSS);
                board.getCells()[1][2].setContent(Seed.CROSS);

                assertEquals(true, board.hasWon(Seed.CROSS, 1, 3));
            }


            @Test
            void testHasWon_IfWinningConditionMetInRowMiddle() {

                board.getCells()[0][0].setContent(Seed.CROSS);
                board.getCells()[0][1].setContent(Seed.CROSS);

                assertEquals(true, board.hasWon(Seed.CROSS, 1, 3));
            }


            @Test
            void testHasWon_IfWinningConditionMetInCol() {

                board.getCells()[0][0].setContent(Seed.CROSS);
                board.getCells()[1][0].setContent(Seed.CROSS);

                assertEquals(true, board.hasWon(Seed.CROSS, 3, 1));
            }


            @Test
            void testHasWon_IfWinningConditionMetInDiagonalMiddle() {

                board.getCells()[1][1].setContent(Seed.CROSS);
                board.getCells()[1][2].setContent(Seed.CROSS);

                assertEquals(true, board.hasWon(Seed.CROSS, 1, 3));
            }
        }
    }
}