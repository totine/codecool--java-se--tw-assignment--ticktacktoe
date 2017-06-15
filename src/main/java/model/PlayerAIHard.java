package model;

import java.util.Arrays;

/**
 * Created by joanna on 15.06.17.
 */
public class PlayerAIHard extends Player {

    int winCol;
    int winRow;
    int winDiag;
    int dangerCol;
    int dangerRow;
    int dangerDiag;
    Seed opponentSeed;

    public PlayerAIHard(Seed seed) {
        this.seed = seed;
        opponentSeed = seed.equals(Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
    }

    @Override
    public PlayerInput getInputFromPlayer(Board board) {
        PlayerInput playerInput;
        if (board.isClear()) {
            playerInput = inputIfClearBoard(board);
        }

        else if (isAlmostWin(board)) {
            playerInput = winInput(board);
        }

        else if (isHaveToBlock(board)) {
            playerInput = blockOpponent(board);
        }

        else if (isCenterEmpty(board)) {
            playerInput = centerInput(board);
        }



        else {
            playerInput = getRandomInput(board);
        }
        System.out.println(playerInput.getRow());
        System.out.println(playerInput.getCol());
    return playerInput;
    }

    private PlayerInput centerInput(Board board) {
        int row = 2;
        int col = 2;
        return new PlayerInput(seed, row, col);
    }

    private boolean isCenterEmpty(Board board) {
        return board.isEmptyCell(2,2);
    }

    private PlayerInput blockOpponent(Board board) {
        int row = dangerRow > 0 ? dangerRow : 0;
        int col = dangerCol > 0 ? dangerCol : 0;
        if (row > 0) {
            col = getEmptyCell(board.getBoardTriples().getRow(dangerRow));
        }
        else if (col > 0) {
            row = getEmptyCell(board.getBoardTriples().getRow(dangerCol));
        }
        else {
            if (dangerDiag == 1) {
                int emptyCell = getEmptyCell(board.getBoardTriples().getDiagFromLeftDown());
                col = board.getBoardSize() - emptyCell + 1;
                row = emptyCell;
            }
            else
            {
                int emptyCell = getEmptyCell(board.getBoardTriples().getDiagFromLeftUp());
                row = emptyCell;
                col = emptyCell;
            }
        }
        return new PlayerInput(seed, row, col);
    }

    private PlayerInput winInput(Board board) {
        int row = winRow > 0 ? winRow : 0;
        int col = winCol > 0 ? winCol : 0;
        if (row > 0) {
            col = getEmptyCell(board.getBoardTriples().getRow(winRow));
        }
        else if (col > 0) {
            row = getEmptyCell(board.getBoardTriples().getRow(winCol));
        }
        else {
            if (winDiag == 1) {
                int emptyCell = getEmptyCell(board.getBoardTriples().getDiagFromLeftDown());
                col = board.getBoardSize() - emptyCell+1;
                row = emptyCell;
            }
            else
            {
                int emptyCell = getEmptyCell(board.getBoardTriples().getDiagFromLeftUp());
                row = emptyCell;
                col = emptyCell;
            }
        }
        return new PlayerInput(seed, row, col);
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
        for (int i = 0; i < board.getBoardSize(); i++) {
            if (Arrays.stream(board.getBoardTriples().getCol(i + 1)).filter(x -> x.equals(opponentSeed)).count() == 2
                    & Arrays.stream(board.getBoardTriples().getCol(i + 1)).filter(x -> x.equals(Seed.EMPTY)).count() == 1) {
                dangerCol = i + 1;
                dangerDiag = 0;
                dangerRow = 0;
                return true;
            }
            if (Arrays.stream(board.getBoardTriples().getRow(i + 1)).filter(x -> x.equals(opponentSeed)).count() == 2
                    & Arrays.stream(board.getBoardTriples().getRow(i + 1)).filter(x -> x.equals(Seed.EMPTY)).count() == 1) {
                dangerRow = i + 1;
                dangerCol = 0;
                dangerDiag = 0;
                return true;
            }

            if (Arrays.stream(board.getBoardTriples().getDiagFromLeftDown()).filter(x -> x.equals(opponentSeed)).count() == 2
                    & Arrays.stream(board.getBoardTriples().getDiagFromLeftDown()).filter(x -> x.equals(Seed.EMPTY)).count() == 1) {
                dangerDiag = 1;
                dangerCol = 0;
                dangerRow = 0;
                return true;
            }
            if (Arrays.stream(board.getBoardTriples().getDiagFromLeftUp()).filter(x -> x.equals(opponentSeed)).count() == 2
                    & Arrays.stream(board.getBoardTriples().getDiagFromLeftUp()).filter(x -> x.equals(Seed.EMPTY)).count() == 1) {
                dangerDiag = 2;
                dangerCol = 0;
                dangerRow = 0;
                return true;
            }
        }
            return false;
        }


    private boolean isAlmostWin(Board board) {
        for (int i = 0; i<board.getBoardSize(); i++) {
            if (Arrays.stream(board.getBoardTriples().getCol(i+1)).filter(x -> x.equals(seed)).count()==2
                    & Arrays.stream(board.getBoardTriples().getCol(i + 1)).filter(x -> x.equals(Seed.EMPTY)).count() == 1) {
                winCol = i + 1;
                winDiag = 0;
                winRow = 0;
                return true;
            }
            if (Arrays.stream(board.getBoardTriples().getRow(i+1)).filter(x -> x.equals(seed)).count()==2
                    & Arrays.stream(board.getBoardTriples().getRow(i + 1)).filter(x -> x.equals(Seed.EMPTY)).count() == 1) {
                winRow = i + 1;
                winCol = 0;
                winDiag = 0;
                return true;
            }
        }
        if (Arrays.stream(board.getBoardTriples().getDiagFromLeftDown()).filter(x -> x.equals(seed)).count()==2
                & Arrays.stream(board.getBoardTriples().getDiagFromLeftDown()).filter(x -> x.equals(Seed.EMPTY)).count() == 1 ) {
            winDiag = 1;
            winCol = 0;
            winRow = 0;
            return true;
        }
        if (Arrays.stream(board.getBoardTriples().getDiagFromLeftUp()).filter(x -> x.equals(seed)).count()==2
                & Arrays.stream(board.getBoardTriples().getDiagFromLeftUp()).filter(x -> x.equals(Seed.EMPTY)).count() == 1) {
            winDiag = 2;
            winCol = 0;
            winRow = 0;
            return true;
        }
        return false;
    }

    private PlayerInput inputIfClearBoard(Board board) {
        int row = 1;
        int col = 1;
        return new PlayerInput(seed, row, col);
    }

    private PlayerInput getRandomInput(Board board) {
        int row = (int) (Math.random()*board.getBoardSize()+1);
        int col = (int) (Math.random()*board.getBoardSize()+1);
        while (!board.isEmptyCell(row, col)) {
            row = (int) (Math.random()*board.getBoardSize()+1);
            col = (int) (Math.random()*board.getBoardSize()+1);
        }
        return new PlayerInput(seed, row, col);
    }
}
