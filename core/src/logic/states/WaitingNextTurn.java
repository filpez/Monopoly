package logic.states;

import logic.Board;
import logic.BoardController;

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
        boardController.getBoard().addActionToLog(" turn has ended!\n");
        Board board = boardController.getBoard();
        board.setCurrentPlayer(board.nextPlayer());
        boardController.getBoard().addActionToLog(" turn started!\n");
        boardController.setState(new ThrowingDice());
    }
}
