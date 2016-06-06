package logic.controller;

import java.util.ArrayList;

import logic.BoardCreator;
import logic.Player;
import logic.states.ThrowingDiceOffline;

/**
 * Board Controler in Client Mode
 */
public class BoardControllerOffline extends BoardController {
    public BoardControllerOffline(int numPlayers) {
        super();

        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player("Red"));
        players.add(new Player("Blue"));
        if (numPlayers >= 3)
            players.add(new Player("Green"));
        if (numPlayers >= 4)
            players.add(new Player("Yellow"));

        board = BoardCreator.createBoard(players);
        board.setCurrentPlayer(players.get(0));
        setState(new ThrowingDiceOffline());
    }

    @Override
    public Player getPlayer() {
        return board.getCurrentPlayer();
    }
}
