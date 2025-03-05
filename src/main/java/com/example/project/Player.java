package com.example.project;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; // the current community cards + hand
    String[] suits = Utility.getSuits();
    String[] ranks = Utility.getRanks();

    public Player() {
        allCards = new ArrayList<>();
        hand = new ArrayList<>();
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public ArrayList<Card> getAllCards() {
        return allCards;
    }

    public void addCard(Card c) {
        hand.add(c);
        allCards.add(c);
    }

    public String playHand(ArrayList<Card> communityCards) {
        if (allCards.size() < 5) {
            allCards.addAll(communityCards);
        }
        // In simplified poker, two players are dealt two private cards each, and there
        // are three community cards that all players can use.
        // The goal is to create the best possible 5-card hand using any combination of
        // the **two cards in hand and the three community cards.**
        int[] rFreq = findRankingFrequency();
        // int[] sFreq = findSuitFrequency();

        // Check 10 to A values and whether there is enough for a royal flush
        for (int i = 8; i < 13; i++) {
            // If the ranking frequency is zero then you can just exit
            if (rFreq[i] == 0) {
                break;
            }
            // If it's the end of the loop we can check more conditions
            if (i == 12) {
                String first = allCards.get(0).getSuit();
                for (int z = 1; z < allCards.size(); z++) {
                    if (!allCards.get(z).getSuit().equals(first)) {
                        break;
                    }
                    if (z == allCards.size() - 1) {
                        return "Royal Flush";
                    }
                }
            }
        }
        int h = 1;
        // Loop through array and find out if there is a 5 consecutive
        for (int i = 0; i < rFreq.length; i++) {
            if (rFreq[i] >= 1) {
                h++;
                if (h >= 5) {
                    String first = allCards.get(0).getSuit();
                    for (int z = 1; z < allCards.size(); z++) {
                        if (!allCards.get(z).getSuit().equals(first)) {
                            break;
                        }
                        if (z == allCards.size() - 1) {
                            return "Straight Flush";
                        }
                    }
                }
            } else {
                h = 0;
            }
        }
        // Find 4 cards of same rank
        for (int i = 0; i < rFreq.length; i++) {
            if (rFreq[i] == 4) {
                return "Four of a Kind";
            }
        }
        boolean three = false;
        boolean two = false;
        // Find three cards of one rank and two cards of another rank.
        for (int i = 0; i < rFreq.length; i++) {
            // System.out.println(rFreq[i]);
            if (rFreq[i] == 3) {
                if (three) {
                    two = true;
                }
                three = true;
            }
            if (rFreq[i] == 2) {
                two = true;
            }
        }
        if (three && two) {
            return "Full House";
        }
        String first = allCards.get(0).getSuit();
        for (int z = 1; z < allCards.size(); z++) {
            if (!allCards.get(z).getSuit().equals(first)) {
                break;
            }
            if (z == allCards.size() - 1) {
                return "Flush";
            }
        }
        h = 1;
        // Loop through array and find out if there is a 5 consecutive
        for (int i = 0; i < rFreq.length; i++) {
            System.out.println(rFreq[i]);
            if (rFreq[i] >= 1) {
                h++;
                if (h >= 5) {
                    System.out.println("wtf");
                    return "Straight";
                }
            } else {
                h = 0;
            }
        }
        for (int i = 0; i < rFreq.length; i++) {
            if (rFreq[i] == 3) {
                return "Three of a Kind";
            }
        }
        int count = 0;
        for (int i = 0; i < rFreq.length; i++) {
            if (rFreq[i] == 2) {
                count++;
            }
            if (count >= 2) {
                return "Two Pair";
            }
        }
        for (int i = 0; i < rFreq.length; i++) {
            // System.out.println(rFreq[i]);
            if (rFreq[i] == 2) {
                return "A Pair";
            }
        }
        int max = getHighestCardValue(hand);
        int max2 = getHighestCardValue(communityCards);
        if (max > max2) {
            return "High Card";
        }
        return "Nothing";
    }

    public int getHighestCardValue(ArrayList<Card> cards) {
        int max = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (Utility.getRankValue(cards.get(i).getRank()) > max) {
                max = Utility.getRankValue(cards.get(i).getRank());
            }
        }
        return max;
    }

    public void sortAllCards() {
    }

    public int[] findRankingFrequency() {
        int[] frequencies = new int[13];
        // Based on the ranking value switch statement in Utility.java: ["2", "3", "4",
        // "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"]
        // Loop through all the cards
        for (int i = 0; i < allCards.size(); i++) {
            // System.out.println(allCards.get(i));
            // Get the ranking of the current card as a String and then get the value as a
            // number/index
            int index = Utility.getRankValue(allCards.get(i).getRank());
            // System.out.println(index);
            // Ensure that the index in frequencie is not null to prevent errors
            // if (frequencies[index] != null) {
            // Increment frequency
            frequencies[index] = frequencies[index] + 1;
            // } else {
            // // Set to zero if it was previously null
            // frequencies[index] = 0;
            // }
        }
        return frequencies;
    }

    public int[] findSuitFrequency() {
        int[] frequencies = new int[4];
        // Based on the ranking value switch statement in Utility.java: ["2", "3", "4",
        // "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"]
        // Loop through all the cards
        for (int i = 0; i < allCards.size(); i++) {
            // Get the ranking of the current card as a String and then get the value as a
            // number/index
            int index = Utility.getSuitValue(allCards.get(i).getSuit());
            // Ensure that the index in frequencie is not null to prevent errors
            // if (frequencies[index] != null) {
            // Increment frequency
            frequencies[index] = frequencies[index] + 1;
            // } else {
            // // Set to zero if it was previously null
            // frequencies[index] = 0;
            // }
        }
        return frequencies;
    }

    @Override
    public String toString() {
        return hand.toString();
    }

}
