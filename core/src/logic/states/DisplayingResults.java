package logic.states;

import logic.controller.BoardController;

/**
 * Created by Filipe on 05/06/2016.
 */

//TO DO
public class DisplayingResults implements State{
    /**
     * Does nothing
     * @param boardController
     * @param i - current position
     */
    @Override
    public void sellEcho(BoardController boardController, int i) {

    }

    /**
     * Does nothing
     * @param boardController
     * @param i - current position
     */
    @Override
    public void buyEcho(BoardController boardController, int i) {

    }

    /**
     * Does nothing
     * @param boardController
     * @param a - value of dice if applicable
     * @param b - value of dice if applicable
     */
    @Override
    public void nextEcho(BoardController boardController, int a, int b) {

    }

    /**
     * Does nothing
     * @param boardController
     * @param i - current position
     */
    @Override
    public void sell(BoardController boardController, int i) {

    }

    /**
     * Does nothing
     * @param boardController
     * @param i - current position
     */
    @Override
    public void buy(BoardController boardController, int i) {

    }

    /**
     * Does nothing
     * @param boardController
     */
    @Override
    public void next(BoardController boardController) {

    }

    @Override
    public String getNextActionName() {
        return "Finish";
    }
}
