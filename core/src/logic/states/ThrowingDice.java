package logic.states;

import logic.Board;
import logic.controller.BoardController;
import logic.controller.BoardControllerClient;

/**
 * Created by Filipe on 14/05/2016.
 */
public class ThrowingDice implements State {
    @Override
    public void buy(BoardController boardController, int i) {
        boardController.getBoard().addMessageToLog("You can't buy right now.");
    }

    @Override
    public void buyEcho(BoardController boardController, int i) {
    }

    @Override
    public void next(BoardController boardController) {
        Board board = boardController.getBoard();
        int a = board.throwDice();
        int b =  board.throwDice();

        BoardControllerClient client = (BoardControllerClient)boardController;
        client.getProxy().next(a+b, a==b);
    }

    @Override
    public void nextEcho(BoardController boardController, int i, boolean doubles) {
        boardController.getBoard().move(i, doubles);
        boardController.setState(new ApplyingActions());
    }

    @Override
    public void sell(BoardController boardController, int i) {
        boardController.getBoard().addMessageToLog("You can't sell right now.");
    }

    @Override
    public void sellEcho(BoardController boardController, int i) {
    }

}
