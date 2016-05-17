package logic;

import logic.states.State;
import logic.states.ThrowingDice;

/**
 * Created by Filipe on 13/05/2016.
 */
public class BoardController {
    private Board board;
    private State state;
    private int lastMovement;

    public BoardController(Board board) {
        this.board = board;
        this.state = new ThrowingDice();
        this.lastMovement = 0;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public boolean move(int i, boolean doubles){
        Player currentPlayer = board.getCurrentPlayer();
        lastMovement = 0;

        if (doubles) {
            currentPlayer.setRemainingArrestedTurns(0);
            addActionToLog(" got doubles!\n");
        }

        if (currentPlayer.isArrested()) {
            currentPlayer.setRemainingArrestedTurns(currentPlayer.getRemainingArrestedTurns() - 1);
            addActionToLog(" is jailed!\n");
            return false;
        }
        else{
            if (currentPlayer.getPosition() + i >= 40)
                currentPlayer.receive(2000);
            int nextPosition = (currentPlayer.getPosition() + i) % 40;
            lastMovement = i;
            currentPlayer.setPosition(nextPosition);
            addActionToLog(" moved to " + board.getSpace(nextPosition).getName() + ".\n");
            return true;
        }
    }

    public void addActionToLog(String log) {
        board.setLog(board.getCurrentPlayer().getName() + log + board.getLog());
    }

    public void addMessageToLog(String log) {
        board.setLog(log + board.getLog());
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getLastMovement() {
        return lastMovement;
    }

    public void setLastMovement(int lastMovement) {
        this.lastMovement = lastMovement;
    }
}
