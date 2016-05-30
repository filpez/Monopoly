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
        boardController.addMessageToLog("You can't buy right now.");
    }

    @Override
    public void next(BoardController boardController) {
        Random rand = new Random();
        int a = rand.nextInt(6) + 1;
        int b = rand.nextInt(6) + 1;
        String s = new String(" has throwed the dice for " + a + " and " + b + "!\n");
        //s = " has throwed the dice for " + a + " and " + b + "!\n";
        boardController.addActionToLog(s);

        boardController.move(a+b, (a == b));

        boardController.setState(new ApplyingActions());
    }
}
