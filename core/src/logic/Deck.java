package logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Random;

/**
 * Created by up201404493 on 17-05-2016.
 */
public class Deck {
    private ArrayList<Card> cards;
    private int currentCardIndex;

    public Deck(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public Card getNextCard(){
        Card nextCard = cards.get(currentCardIndex);
        currentCardIndex = (currentCardIndex + 1) % cards.size();
        return nextCard;
    }

    public void shuffle(){
        Collections.shuffle(cards);
        currentCardIndex = 0;
    }
}
