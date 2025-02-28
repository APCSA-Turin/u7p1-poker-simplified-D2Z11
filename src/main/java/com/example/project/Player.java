package com.example.project;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; // the current community cards + hand
    String[] suits = Utility.getSuits();
    String[] ranks = Utility.getRanks();

    public Player() {
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public void addCard(Card c) {
        allCards.add(c);
    }

    public String playHand(ArrayList<Card> communityCards) {
        
        return "Nothing";
    }

    public void SortCards() {
    }

    public ArrayList<Integer> findRankingFrequency() {
        ArrayList<Integer> frequencies = new ArrayList<>();
        // Based on the ranking value switch statement in Utility.java: ["2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"]
        // Loop through the hand
        for (int i = 0; i < hand.size(); i++) {
            // Get the ranking of the current card as a String and then get the value as a number/index
            int index = Utility.getRankValue(hand.get(i).getRank());
            // Ensure that the index in frequencie is not null to prevent errors
            if (frequencies.get(index) != null) {
                // Increment frequency
                frequencies.set(index, frequencies.get(index) + 1);
            } else {
                // Set to zero if it was previously null
                frequencies.set(index, 0);
            }
        }
        return frequencies;
    }

    public ArrayList<Integer> findSuitFrequency() {
        ArrayList<Integer> frequencies = new ArrayList<>();
        // Based on the ranking value switch statement in Utility.java: ["♠","♥","♣", "♦”]
        // Loop through the hand
        for (int i = 0; i < hand.size(); i++) {
            // Get the suit of the current card as a String and then get the value as a number/index
            int index = Utility.getSuitValue(hand.get(i).getSuit());
            // Ensure that the index in frequencie is not null to prevent errors
            if (frequencies.get(index) != null) {
                // Increment frequency
                frequencies.set(index, frequencies.get(index) + 1);
            } else {
                // Set to zero if it was previously null
                frequencies.set(index, 0);
            }
        }
        return frequencies;
    }

    @Override
    public String toString() {
        return hand.toString();
    }

}
