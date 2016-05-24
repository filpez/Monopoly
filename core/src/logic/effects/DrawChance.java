package logic.effects;

import logic.Board;
import logic.Card;
import logic.Deck;

/**
 * Created by up201404493 on 17-05-2016.
 */
public class DrawChance extends Effect{
    public DrawChance(int value) {
        super(value);
    }

    @Override
    public boolean apply(Board board) {
        Deck chance = board.getChance();
        Card card = chance.getNextCard();
        chance.shuffle();
        board.getController().addMessageToLog(card.getText());
        return card.getEffect().apply(board);
    }
}
