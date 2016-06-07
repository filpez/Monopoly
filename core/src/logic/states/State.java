package logic.states;

import logic.controller.BoardController;
import logic.controller.BoardControllerClient;

/**
 * Created by Filipe on 03/06/2016.
 */
public interface State {
    /**
     * Returns what next will do.
     * @return a string representing what next will do.
     */
    public String getNextActionName();

    /**
     * Checks and prepares anything necessary.
     * Applies effect of next button.
     * Calls nextEcho for every controller.
     * @param boardController
     */
    public void next(BoardController boardController);

    /**
     * Tries to buy.
     * Calls buyEcho for every controller.
     * @param boardController
     * @param i - current position
     */
    public void buy(BoardController boardController, int i);

    /**
     * Tries to sell.
     * Calls sellEcho for every controller.
     * @param boardController
     * @param i - current position
     */
    public void sell(BoardController boardController, int i);

    /**
     * Applies effect of next button.
     * @param boardController
     * @param a - value of dice if applicable
     * @param b - value of dice if applicable
     */
    public void nextEcho(BoardController boardController, int a, int b);

    /**
     * Current player buys space at int i;
     * @param boardController
     * @param i - current position
     */
    public void buyEcho(BoardController boardController, int i);

    /**
     * Current player sell space at int i;
     * @param boardController
     * @param i - current position
     */
    public void sellEcho(BoardController boardController, int i);
}
