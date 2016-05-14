package logic.states;

import logic.BoardController;

/**
 * Created by Filipe on 14/05/2016.
 */
public interface State {
    public void next(BoardController boardController);
    public void buy(BoardController boardController);
}
