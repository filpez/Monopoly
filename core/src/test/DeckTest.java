package test;

import org.junit.Test;

import java.util.ArrayList;

import logic.Card;
import logic.Deck;
import logic.effects.Effect;
import logic.effects.Move;

import static org.junit.Assert.assertEquals;

/**
 * Created by Cláudia Marinho on 06/06/2016.
 */
public class DeckTest {
    @Test
    public void testCardBuilder() {
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
        Deck d = new Deck(cards);

        assertEquals(cards, d.getCards());

        ArrayList<Card> c = new ArrayList<Card>();
        Card m = new Card("Move 3 positions", "Move", 3);
        Card g = new Card("Go to Jail", "GoToJail", 0);
        c.add(m);
        c.add(g);

        d.setCards(c);
        assertEquals(c, d.getCards());

        /*assertEquals(c.getText(), d.getNextCard().getText());
        assertEquals(g.getText(), d.getNextCard().getText());*/
    }
}
