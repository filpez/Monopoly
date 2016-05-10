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
    public boolean apply() {
        Board board = Board.getInstance();
        Player currentPlayer = board.getCurrentPlayer();
        //TO DO - avançar player
        return true;
    }
}