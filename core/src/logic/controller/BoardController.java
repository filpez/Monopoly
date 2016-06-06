package logic.controller;

import logic.Board;
import logic.Player;
import logic.states.State;

/**
 * Controls the state of the game
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Checks if the Player using this boardController is the current player.
     * @return true if The players are the same, false otherwise.
     */
    public boolean isPlayer(){
        return board.getCurrentPlayer().getName().equals(player.getName());
    }


}
