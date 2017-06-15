package model;

/**
 * Created by joanna on 15.06.17.
 */
public class PlayerAIEasy extends Player {

    public PlayerAIEasy() {

    }

    public PlayerAIEasy(Seed seed) {
        this.seed = seed;
    }

    @Override
    public PlayerInput getInputFromPlayer(Board board) {
        return getRandomInput(board);
    }

    protected PlayerInput getRandomInput(Board board) {
        int row = (int) (Math.random()*board.getBoardSize()+1);
        int col = (int) (Math.random()*board.getBoardSize()+1);
        while (!board.isEmptyCell(row, col)) {
            row = (int) (Math.random()*board.getBoardSize()+1);
            col = (int) (Math.random()*board.getBoardSize()+1);
        }
        return new PlayerInput(seed, row, col);
    }
}
