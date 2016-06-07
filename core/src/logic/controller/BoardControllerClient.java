package logic.controller;

import java.io.IOException;
import java.util.ArrayList;

import lipermi.exception.LipeRMIException;
import lipermi.handler.CallHandler;
import lipermi.net.Client;
import logic.BoardCreator;
import logic.Player;
import logic.states.ThrowingDiceClient;

/**
 * Board Controller in Client Mode
 */
public class BoardControllerClient extends BoardController implements ControllerClientInterface {
    private ControllerServerInterface proxy; // Server

    public BoardControllerClient(String playerName, String IPAddress ) throws LipeRMIException, IOException {
        super();

        player = new Player(playerName);

        CallHandler callHandler = new CallHandler();
        int portWasBinded = 4456;
        Client client = new Client(IPAddress, portWasBinded, callHandler);
        proxy = ( ControllerServerInterface)client.getGlobal(ControllerServerInterface.class);
        callHandler.exportObject(ControllerClientInterface.class, this);

        proxy.join(playerName, this);
    }


    @Override
    public void buy() {
        state.buyEcho(this, board.getCurrentPlayer().getPosition());
    }

    @Override
    public void next(int a, int b) {
        state.nextEcho(this, a, b);
    }

    @Override
    public void sell() {
        state.sellEcho(this, board.getCurrentPlayer().getPosition());
    }

    @Override
    public void start(ArrayList<String> playerNames, int i) {
        ArrayList<Player> players = new ArrayList<Player>();
        for (String name: playerNames)
            players.add(new Player(name));
        board = BoardCreator.createBoard(players);
        Player firstPlayer = players.get(i);
        board.setCurrentPlayer(firstPlayer);
        board.addActionToLog(" turn started!\n");
        setState(new ThrowingDiceClient());

    }

    public ControllerServerInterface getProxy() {
        return proxy;
    }

    public void setProxy(ControllerServerInterface proxy) {
        this.proxy = proxy;
    }
}
