package logic.effects;

import logic.Board;
import logic.Player;

/**
 * Created by Claudia Marinho on 10/05/2016.
 */
public class GoToJail extends Effect {
    public GoToJail(int value) {
        super(value);
    }

    @Override
    public boolean apply(Board board) {
        Player currentPlayer = board.getCurrentPlayer();
        currentPlayer.setRemainingArrestedTurns(2);
        currentPlayer.setPosition(10);
        board.addActionToLog(" was sent to Jail!\n");
        return true;
    }
}
