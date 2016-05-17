package logic.states;

import java.util.Random;

import logic.Board;
import logic.BoardController;

/**
 * Created by Filipe on 14/05/2016.
 */
public class ApplyingActions implements State{
    @Override
    public void buy(BoardController boardController) {

    }

    @Override
    public void next(BoardController boardController) {
        Board board = boardController.getBoard();
        int currPos = board.getCurrentPlayer().getPosition();
        board.getSpace(currPos).applyEffect(board, boardController.getLastMovement());

        boardController.setState(new ThrowingDice());

    }
}
