package logic.controller;

import java.util.ArrayList;

/**
 * Created by Filipe on 02/06/2016.
 */
public interface ControllerClientInterface {
    /**
     * Call the buyEcho() function of the current state of the controller
     */
    void buy();

    /**
     * Call the nextEcho() function of the current state of the controller
     * @param a - dicevalue if applicable
     * @param b - dicevalue if applicable
     */
    void next(int a, int b);

    /**
     * Call the sellEcho() function of the current state of the controller
     */
    void sell();

    /**
     * Start the game in the client.
     * Creates a board with the players from playerNames, and assigns the i'th player as the first one.
     * @param playerNames - the names of the players
     * @param i - index of the first player in playerNames
     */
    void start(ArrayList<String> playerNames, int i);
}
