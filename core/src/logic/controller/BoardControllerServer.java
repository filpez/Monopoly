package logic.controller;

import java.util.ArrayList;

import logic.Board;
import logic.Player;
import logic.states.State;

/**
 * Created by Filipe on 13/05/2016.
 */
public class BoardControllerServer extends logic.controller.BoardController implements ControllerServerInterface {
    ArrayList<Player> players;
    public Board board;
    private State state;

    public BoardControllerServer(String playerName) {
        super();
        players = new ArrayList<Player>();
        players.add(new Player(playerName));
    }

    @Override
    public void buy() {
        state.buy(this, board.getCurrentPlayer().getPosition());
    }

    @Override
    public boolean join(String name, ControllerClientInterface c) {
        if (players.size() >= 4)
            return false;
        for (Player p: players)
            if (p.getName().equals(name))
                return false;
        players.add(new Player(name));
        return true;
    }

    @Override
    public void next() {
        state.next(this);
    }

    @Override
    public void sell() {
        state.sell(this, board.getCurrentPlayer().getPosition());
    }




}
