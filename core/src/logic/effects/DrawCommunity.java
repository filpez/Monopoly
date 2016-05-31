package logic.effects;

import logic.Board;
import logic.Card;
import logic.Deck;

/**
 * Created by up201404493 on 17-05-2016.
 */
public class DrawCommunity extends Effect{
    public DrawCommunity(int value) {
        super(value);
    }

    @Override
    public boolean apply(Board board) {
        Deck community = board.getCommunity();
        Card card = community.getNextCard();
        community.shuffle();
        board.addMessageToLog(card.getText());
        return card.getEffect().apply(board);
    }
}
