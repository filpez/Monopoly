package logic.effects;

import logic.board.Board;
import logic.board.Card;
import logic.board.Deck;

/**
 * Represents a effect that draws a community card
 */
public class DrawCommunity extends Effect{
    public DrawCommunity(int value) {
        super(value);
    }

    /**
     * Applies effect that draws a community card
     * @param board - board to which the effect will be applied
     * @return true if the card effect was successful, false if otherwise
     */
    @Override
    public boolean apply(Board board) {
        Deck community = board.getCommunity();
        Card card = community.getNextCard();
        //community.shuffle();
        board.addMessageToLog(card.getText() + "\n");
        return card.getEffect().apply(board);
    }
}
