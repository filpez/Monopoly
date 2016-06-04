package logic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import logic.BoardCreator;
import logic.Player;
import logic.states.ThrowingDiceServer;

/**
 * Created by Filipe on 13/05/2016.
 */
public class BoardControllerServer extends logic.controller.BoardController implements ControllerServerInterface {
    private HashMap<Player, ControllerClientInterface> clients;
    private ArrayList<Player> players;


    public BoardControllerServer(String playerName) {
        super();
        clients = new HashMap<Player, ControllerClientInterface>();
        player = new Player(playerName);
        players = new ArrayList<Player>();
        players.add(player);
    }

    public HashMap<Player, ControllerClientInterface> getClients() {
        return clients;
    }

    @Override
    public void buy() {
        for (Player p: clients.keySet())
            clients.get(p).buy();
        state.buyEcho(this, board.getCurrentPlayer().getPosition());
    }

    @Override
    public boolean join(String name, ControllerClientInterface c) {
        if (players.size() >= 4)
            return false;
        for (Player p: players)
            if (p.getName().equals(name))
                return false;
        Player p = new Player(name);
        players.add(p);
        clients.put(p, c);
        return true;
    }

    @Override
    public void next(int a, int b) {
        for (Player p: clients.keySet())
            clients.get(p).next(a, b);
        state.nextEcho(this, a, b);
    }

    @Override
    public void sell() {
        for (Player p: clients.keySet())
            clients.get(p).sell();
        state.sellEcho(this, board.getCurrentPlayer().getPosition());
    }

    public void start(){
        /*// Read Board TO DO
        Space[] spaces = new Space[40];
        for (int i = 0; i < 40; i++)
            spaces[i] = new TransactionSpace("Lisbon", 1);

        // Create Board
        board = new Board(players, spaces);*/
        board = BoardCreator.createBoard(players);

        //Select Random Player
        Random rand = new Random();
        Player firstPlayer = players.get(rand.nextInt(players.size()));
        board.setCurrentPlayer(firstPlayer);

        //Set state
        setState(new ThrowingDiceServer());
    }


}
