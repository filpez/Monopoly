package logic.effects;

import logic.Board;
import logic.Player;

/**
 * Created by Claudia Marinho on 10/05/2016.
 */
public class GoToPrison extends Effect {
    public GoToPrison(int value) {
        super(value);
    }

    @Override
    public boolean apply(Board board) {
        Player currentPlayer = board.getCurrentPlayer();
        currentPlayer.setArrested(true);
        currentPlayer.setPosition(10);
        return true;
    }
}
