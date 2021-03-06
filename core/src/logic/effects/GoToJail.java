package logic.effects;

import logic.board.Board;
import logic.board.Player;

/**
 * Created by Claudia Marinho on 10/05/2016.
 */
public class GoToJail extends Effect {
    public GoToJail(int value) {
        super(value);
    }

    /**
     * Applies effect that moves current player to jail
     * @param board - board to which the effect will be applied
     * @return true
     */
    @Override
    public boolean apply(Board board) {
        Player currentPlayer = board.getCurrentPlayer();
        currentPlayer.setRemainingArrestedTurns(2);
        currentPlayer.setPosition(10);
        board.addActionToLog(" was sent to Jail!\n");
        return true;
    }
}
