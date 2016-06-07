package logic.effects;

import logic.board.Board;
import logic.board.Card;
import logic.board.Deck;

/**
 * Represents a effect that draws a  chance card
 */
public class DrawChance extends Effect{
    public DrawChance(int value) {
        super(value);
    }

    /**
     * Applies effect that draws a chance card
     * @param board - board to which the effect will be applied
     * @return true if the card effect was successful, false if otherwise
     */
    @Override
    public boolean apply(Board board) {
        Deck chance = board.getChance();
        Card card = chance.getNextCard();
        board.addMessageToLog(card.getText() + "\n");
        return card.getEffect().apply(board);
    }
}
