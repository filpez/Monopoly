package logic;

import logic.states.State;
import logic.states.ThrowingDice;

/**
 * Created by Filipe on 13/05/2016.
 */
public class BoardController {
    private Board board;
    private State state;

    public BoardController(Board board) {
        this.board = board;
        this.state = new ThrowingDice();
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean move(int i, boolean doubles){
        Player currentPlayer = board.getCurrentPlayer();

        if (doubles)
            currentPlayer.setRemainingArrestedTurns(0);

        if (currentPlayer.isArrested()) {
            currentPlayer.setRemainingArrestedTurns(currentPlayer.getRemainingArrestedTurns() - 1);
            return false;
        }
        else{
            if (currentPlayer.getPosition() + i >= 40)
                currentPlayer.receive(2000);
            int nextPosition = (currentPlayer.getPosition() + i) % 40;
            currentPlayer.setPosition(nextPosition);
            return true;
        }


    }

    public void addActionToLog(String log) {
        board.setLog(board.getCurrentPlayer().getName() + log + board.getLog());
    }

    public void addErrorToLog(String log) {
        board.setLog(log + board.getLog());
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
