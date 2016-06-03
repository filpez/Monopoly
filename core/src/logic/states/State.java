package logic.states;

import logic.controller.BoardController;
import logic.controller.BoardControllerClient;

/**
 * Created by Filipe on 03/06/2016.
 */
public interface State {
    public void next(BoardController boardController);
    public void buy(BoardController boardController, int i);
    public void sell(BoardController boardController, int i);

    public void nextEcho(BoardController boardController, int i, boolean doubles);
    public void buyEcho(BoardController boardController, int i);
    public void sellEcho(BoardController boardController, int i);
}
