package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Random;

/**
 * Represents a deck of cards.
 */
public class Deck {
    private ArrayList<Card> cards;
    private int currentCardIndex = 0;

    /**
     * Creates a new deck of cards
     * @param cards - array of cards
     */
    public Deck(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }
    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    /**
     * Returns the next card in the deck
     * @return next card in the deck of cards
     */
    public Card getNextCard(){
        Card nextCard = cards.get(currentCardIndex);
        currentCardIndex = (currentCardIndex + 1) % cards.size();
        return nextCard;
    }
}
