package logic.states;

import logic.Board;
import logic.BoardController;
import logic.Player;

/**
 * Created by Filipe on 17/05/2016.
 */
public class WaitingNextTurn implements State{
    @Override
    public void buy(BoardController boardController) {

    }

    @Override
    public void next(BoardController boardController) {
        boardController.addActionToLog(" turn has ended!\n");
        Board board = boardController.getBoard();
        board.setCurrentPlayer(board.nextPlayer());
        boardController.addActionToLog(" turn started!\n");
        boardController.setState(new ThrowingDice());
    }
}
