package logic.states;

import java.util.Random;

import logic.Board;
import logic.BoardController;

/**
 * Created by Filipe on 14/05/2016.
 */
public class ThrowingDice implements State{
    @Override
    public void buy(BoardController boardController) {

    }

    @Override
    public void next(BoardController boardController) {
        Random rand = new Random();
        int a = rand.nextInt(6) + 1;
        int b = rand.nextInt(6) + 1;
        boolean doubles = (a == b);
        String s = new String("has throwed the dice for ");
        s = " has throwed the dice for " + a + " and " + b + "!\n";
        boardController.addActionToLog(s);

        if (doubles) {
            s = " got doubles!\n";
            boardController.addActionToLog(s);
        }

        if(boardController.move(a+b, doubles)) {
            Board board = boardController.getBoard();
            int newPos = board.getCurrentPlayer().getPosition();
            s = " moved to " + board.getSpace(newPos).getName() + ".\n";
        }
        else{
            s = " is jailed!\n";
        }
        boardController.addActionToLog(s);

        boardController.setState(new ApplyingActions());
    }
}
