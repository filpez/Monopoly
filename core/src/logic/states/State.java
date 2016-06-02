package logic.states;

import logic.controller.BoardController;

/**
 * Created by Filipe on 14/05/2016.
 */
public interface State {
    public void next(BoardController boardController);
    public void buy(BoardController boardController, int i);
    public void sell(BoardController boardController, int i);
}
