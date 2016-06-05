package logic.states;

import logic.controller.BoardController;

/**
 * Created by Filipe on 05/06/2016.
 */

//TO DO
public class DisplayingResults implements State{
    @Override
    public void sellEcho(BoardController boardController, int i) {

    }

    @Override
    public void buyEcho(BoardController boardController, int i) {

    }

    @Override
    public void nextEcho(BoardController boardController, int a, int b) {

    }

    @Override
    public void sell(BoardController boardController, int i) {

    }

    @Override
    public void buy(BoardController boardController, int i) {

    }

    @Override
    public void next(BoardController boardController) {

    }

    @Override
    public String getNextActionName() {
        return "Finish";
    }
}
