package test;

import org.junit.Test;

import java.util.ArrayList;

import logic.Card;
import logic.effects.Effect;
import logic.effects.Move;

import static org.junit.Assert.assertEquals;

/**
 * Created by Cláudia Marinho on 06/06/2016.
 */
public class DeckTest {
    @Test
    public void testCardBuilder() {
        Effect e = new Move(3);
        Card c = new Card("Move 3 positions", "Move", 3);

        assertEquals("Move 3 positions", c.getText());
        assertEquals(3, c.getEffect().getValue());

        c.setEffect(new Move(2));
        assertEquals(2, c.getEffect().getValue());

        c.setText("Move 2 positions");
        assertEquals("Move 2 positions", c.getText());
    }

    @Test
    public void testDeckBuilder() {
        ArrayList<Card> cards = new ArrayList<Card>();
        Card c = new Card("Move 3 positions", "Move", 3);
        Card g = new Card("Go to Jail", "GoToJail", 0);
        cards.add(c);
        cards.add(g);

        /*Deck d = new Deck(cards);
        assertEquals(c.getText(), d.getNextCard().getText());
        assertEquals(g.getText(), d.getNextCard().getText());*/
    }
}
