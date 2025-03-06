package com.example.project;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void initializeDeck() { // hint.. use the utility class
        String[] r = Utility.getRanks();
        String[] s = Utility.getSuits();
        for (int i = 0; i < r.length; i++) {
            for (int z = 0; z < s.length; z++) {
                Card newCard = new Card(r[i], s[z]);
                cards.add(newCard);
            }
        }
    }

    public void shuffleDeck() { // You can use the Collections library or another method. You do not have to
                                // create your own shuffle algorithm
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        Card r = cards.remove(0);
        return r;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

}