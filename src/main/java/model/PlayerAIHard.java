package model;

import ui.Printer;

import java.util.Arrays;

public class PlayerAIHard extends PlayerAIEasy {
    int colToInput;
    int rowToInput;
    Seed opponentSeed;

    public PlayerAIHard(Seed seed) {
        this.seed = seed;
        opponentSeed = seed.equals(Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
    }

    @Override
    public PlayerInput getInputFromPlayer(Board board) {
        PlayerInput playerInput;
        if (board.isClear()) {
            playerInput = inputIfClearBoard();
        } else if (isAlmostWin(board)) {
            playerInput = winInput();
        } else if (isHaveToBlock(board)) {
            playerInput = blockOpponent();
        } else if (isCenterEmpty(board)) {
            playerInput = centerInput(board);
        } else {
            playerInput = getRandomInput(board);
        }
        Printer printer = new Printer();
        printer.artificialInteligenceMoveDisplay(playerInput.getRow(), playerInput.getCol());
        return playerInput;
    }

    private PlayerInput centerInput(Board board) {
        int row = 2;
        int col = 2;
        return new PlayerInput(seed, row, col);
    }

    private boolean isCenterEmpty(Board board) {
        int centerRow = (int) Math.ceil(board.getBoardSize()/2.0);
        int centerCol = (int) Math.ceil(board.getBoardSize()/2.0);
        return board.isEmptyCell(centerRow,centerCol);
    }

    private PlayerInput blockOpponent() {
        return new PlayerInput(seed, rowToInput, colToInput);
    }

    private PlayerInput winInput() {
        return new PlayerInput(seed, rowToInput, colToInput);
        }

    private int getEmptyCell(Seed[] cellsline) {
        for (int i=0;i<cellsline.length;i++) {
            if (cellsline[i].equals(Seed.EMPTY)) {
                System.out.println(i+1);
                return i+1;
            }
        }
        return 0;
    }

    private boolean isHaveToBlock(Board board) {
        return checkBoard(board, opponentSeed);
    }

    private boolean checkBoard(Board board, Seed seed) {
        for (int i = 0; i < board.getBoardSize(); i++) {
            if (Arrays.stream(board.getBoardTriples().getCol(i + 1))
                                .filter(x -> x.equals(seed))
                                    .count() == 2
                    & Arrays.stream(board.getBoardTriples().getCol(i + 1))
                                        .filter(x -> x.equals(Seed.EMPTY))
                                            .count() == 1) {
                colToInput = i + 1;
                rowToInput = getEmptyCell(board.getBoardTriples().getCol(colToInput));
                return true;
            }
            if (Arrays.stream(board.getBoardTriples().getRow(i + 1))
                                .filter(x -> x.equals(seed))
                                    .count() == 2
                    & Arrays.stream(board.getBoardTriples().getRow(i + 1))
                                .filter(x -> x.equals(Seed.EMPTY))
                                    .count() == 1) {
                rowToInput = i + 1;
                colToInput = getEmptyCell(board.getBoardTriples().getRow(rowToInput));
                return true;
            }

            if (Arrays.stream(board.getBoardTriples().getDiagFromLeftDown())
                                .filter(x -> x.equals(seed))
                                    .count() == 2
                    & Arrays.stream(board.getBoardTriples().getDiagFromLeftDown())
                                        .filter(x -> x.equals(Seed.EMPTY))
                                            .count() == 1) {
                colToInput = getEmptyCell(board.getBoardTriples().getDiagFromLeftDown());
                rowToInput = board.getBoardSize() + 1 - colToInput;
                return true;
            }
            if (Arrays.stream(board.getBoardTriples().getDiagFromLeftUp())
                                .filter(x -> x.equals(seed))
                                    .count() == 2
                    & Arrays.stream(board.getBoardTriples().getDiagFromLeftUp())
                                        .filter(x -> x.equals(Seed.EMPTY))
                                            .count() == 1) {
                colToInput = getEmptyCell(board.getBoardTriples().getDiagFromLeftUp());
                rowToInput = colToInput;
                return true;
            }
        }
        return false;
    }

    private boolean isAlmostWin(Board board) {
        return checkBoard(board, seed);
    }

    private PlayerInput inputIfClearBoard() {
        int row = 1;
        int col = 1;
        return new PlayerInput(seed, row, col);
    }
}