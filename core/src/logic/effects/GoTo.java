package logic.effects;

import logic.Board;
import logic.Player;

/**
 * Created by Claudia Marinho on 10/05/2016.
 */
public class GoTo extends Effect {
    public GoTo(int value) {
        super(value);
    }

    @Override
    public boolean apply(Board board) {
        Player currentPlayer = board.getCurrentPlayer();
        currentPlayer.setPosition(getValue());
        //TO DO!! -> aplicar o efeito da casa que calhar
        return true;
    }
}