package logic.states;

import logic.Board;
import logic.controller.BoardController;

/**
 * Created by Filipe on 17/05/2016.
 */
public class WaitingNextTurn implements State{
    @Override
    public void buy(BoardController boardController, int i) {
        boardController.getBoard().addMessageToLog("You can't buy right now.");
    }

    @Override
    public void next(BoardController boardController) {
        Board board = boardController.getBoard();
        board.endTurn();
        boardController.setState(new ThrowingDice());
    }

    @Override
    public void sell(BoardController boardController, int i) {
        boardController.getBoard().addMessageToLog("You can't buy right now.");
    }
}
