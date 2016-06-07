package logic.effects;

import logic.board.Board;
import logic.board.Player;

/**
 * Created by Claudia Marinho on 10/05/2016.
 */
public class GoTo extends Effect {
    /**
     * Creates a GoTo effect
     * @param value - position of the destination
     */
    public GoTo(int value) {
        super(value);
    }

    /**
     * Applies effect that moves current player to a new position (given by value)
     * @param board - board to which the effect will be applied
     * @return false
     */
    @Override
    public boolean apply(Board board) {
        Player currentPlayer = board.getCurrentPlayer();
        currentPlayer.setPosition(getValue());
        board.addActionToLog(" moved to " + board.getSpace(getValue()).getName() + "!\n");
        return false;
    }
}