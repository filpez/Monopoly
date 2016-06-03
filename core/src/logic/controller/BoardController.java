package logic.controller;

import logic.Board;
import logic.states.State;

/**
 * Created by Filipe on 13/05/2016.
 */
public class BoardController {
    protected Board board;
    protected State state;

    public BoardController() {
    }


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }


}
