package model;

import exceptions.IncorrectRowOrColInputException;
import ui.Printer;
import ui.UserInterface;


public class PlayerReal extends Player {
    private UserInterface ui;

    public PlayerReal(Seed seed) {
        this.seed = seed;
        ui = new UserInterface();
    }

    @Override
    public PlayerInput getInputFromPlayer(Board board) {
        int row = 0;
        int col = 0;
        while (!board.isEmptyCell(row, col)) {
            row = getRowOrCol(board.getBoardSize(), "row");
            col = getRowOrCol(board.getBoardSize(), "col");
            if (!board.isEmptyCell(row, col)) {
                ui.showNotEmptyCellInfo(row, col);
            }
        }
        return new PlayerInput(seed, row, col);
    }

    private int getRowOrCol(int boardSize, String rowOrColName)  {
        int rowOrColNumber = 0;
        boolean isCorrectRowOrColNumber = false;
        while (!isCorrectRowOrColNumber) {
            try {
                ui.showRowColRequest(rowOrColName, boardSize);
                rowOrColNumber = ui.getNumberFromPlayer(boardSize);
                isCorrectRowOrColNumber = true;

            } catch (IllegalArgumentException e) {
                Printer printer = new Printer();
                printer.illegalArgumentExceptionMessage(boardSize);
            }
        }

        return rowOrColNumber;
    }

}
