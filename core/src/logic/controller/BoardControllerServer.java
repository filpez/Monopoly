package logic.controller;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Random;

import lipermi.exception.LipeRMIException;
import lipermi.handler.CallHandler;
import lipermi.net.Server;
import logic.BoardCreator;
import logic.Player;
import logic.states.ThrowingDiceServer;
import sun.rmi.runtime.Log;

/**
 * Created by Filipe on 13/05/2016.
 */
public class BoardControllerServer extends logic.controller.BoardController implements ControllerServerInterface {
    private HashMap<Player, ControllerClientInterface> clients;
    private ArrayList<Player> players;
    private String IPAddress;

    public static String getIpAddress(){
        try {
            for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf =(NetworkInterface) en.nextElement();
                for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = (InetAddress)enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()&&inetAddress instanceof Inet4Address) {
                        String ipAddress=inetAddress.getHostAddress().toString();
                        //Log.e("IP address",""+ipAddress);
                        return ipAddress;
                    }
                }
            }
        } catch (SocketException ex) {
        }
        return null;
    }


    public BoardControllerServer(String playerName) throws LipeRMIException, IOException{
        super();
        clients = new HashMap<Player, ControllerClientInterface>();
        player = new Player(playerName);
        players = new ArrayList<Player>();
        players.add(player);

        //Initiate Server
        CallHandler callHandler = new CallHandler();
        callHandler.registerGlobal(ControllerServerInterface.class, this);
        Server server = new Server();
        int thePortIWantToBind = 4456;
        server.bind(thePortIWantToBind, callHandler);
        InetAddress IP = InetAddress.getLocalHost();
        //IPAddress = IP.getHostAddress();
        IPAddress = getIpAddress();
    }

    public HashMap<Player, ControllerClientInterface> getClients() {
        return clients;
    }

    public String getIPAddress() {
        return IPAddress;
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
        board = BoardCreator.createBoard(players);

        //Select Random Player
        Random rand = new Random();
        int i = rand.nextInt(players.size());
        Player firstPlayer = players.get(i);
        board.setCurrentPlayer(firstPlayer);
        board.addActionToLog(" turn started!\n");
        ArrayList<String> playerNames = new ArrayList<String>();
        for (Player p: players)
            playerNames.add(p.getName());

        for (Player p: clients.keySet())
            clients.get(p).start(playerNames, i);

        //Set state
        setState(new ThrowingDiceServer());
    }


}
