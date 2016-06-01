package logic.states;

import java.util.Random;

import logic.Board;
import logic.BoardController;

/**
 * Created by Filipe on 14/05/2016.
 */
public class ThrowingDice implements State{
    @Override
    public void buy(BoardController boardController, int i) {
        boardController.getBoard().addMessageToLog("You can't buy right now.");
    }

    @Override
    public void next(BoardController boardController) {
        Board board = boardController.getBoard();
        int a = board.throwDice();
        int b =  board.throwDice();

        boardController.getBoard().move(a+b, (a == b));

        boardController.setState(new ApplyingActions());
    }
}
