package logic.controller;

import logic.Board;
import logic.Player;
import logic.states.State;

/**
 * Created by Filipe on 13/05/2016.
 */
public class BoardController {
    protected Board board;
    protected State state;
    protected Player player;

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

    public boolean isPlayer(){
        return board.getCurrentPlayer().getName().equals(player.getName());
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
