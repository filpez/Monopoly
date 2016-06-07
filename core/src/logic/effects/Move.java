package logic.effects;

import logic.board.Board;

/**
 * Created by Claudia Marinho on 10/05/2016.
 */
public class Move  extends Effect {
    /**
     * Creates a Move effect
     * @param value - number of houses to move (can be positive or negative)
     */
    public Move(int value) {
        super(value);
    }

    /**
     * Applies effect that moves current player a number of positions equivalent to value
     * @param board - board to which the effect will be applied
     * @return false
     */
    @Override
    public boolean apply(Board board) {
        board.move(getValue(), false);
        return false;
    }
}