package logic.effects;

import logic.Board;
import logic.Player;

/**
 * Created by Claudia Marinho on 10/05/2016.
 */
public class Move  extends Effect {
    public Move(int value) {
        super(value);
    }

    @Override
    public boolean apply(Board board) {
        board.move(getValue(), false);
        return false;
    }
}