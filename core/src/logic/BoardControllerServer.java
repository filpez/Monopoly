package logic;

import java.util.ArrayList;

import logic.states.State;
import logic.states.ThrowingDice;

/**
 * Created by Filipe on 13/05/2016.
 */
public class BoardControllerServer extends BoardController{
    ArrayList<Player> players;
    public Board board;
    private State state;

    public BoardControllerServer() {
        super();
        players = new ArrayList<Player>();
    }

    public void addPlayer(String name){
        players.add(new Player(name));
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }


}
