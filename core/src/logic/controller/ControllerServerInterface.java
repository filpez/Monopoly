package logic.controller;

/**
 * Created by Filipe on 02/06/2016.
 */
public interface ControllerServerInterface {

    /**
     *  Adds a new player(client) as long the maximum amount of players was not exceeded and there isn't any player with the same name.
     * @param name - name of the new player
     * @param c - the client controller
     * @return true if player was able to join, false otherwise.
     */
    boolean join(String name, ControllerClientInterface c);


    /**
     * Call the buyEcho() function of the current state of the controller on itself and on every client
     */
    void buy();

    /**
     * Call the nextEcho() function of the current state of the controller  on itself and on every client
     * @param a - dicevalue if applicable
     * @param b - dicevalue if applicable
     */
    void next(int a, int b);

    /**
     * Call the sellEcho() function of the current state of the controller  on itself and on every client
     */
    void sell();
}
