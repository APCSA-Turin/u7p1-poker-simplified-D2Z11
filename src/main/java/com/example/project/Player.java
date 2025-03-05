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
        // Add the card to both your hand and all the cards
        hand.add(c);
        allCards.add(c);
    }

    // In simplified poker, two players are dealt two private cards each, and there
    // are three community cards that all players can use.
    // The goal is to create the best possible 5-card hand using any combination of
    // the **two cards in hand and the three community cards.**
    public String playHand(ArrayList<Card> communityCards) {
        // In case the cards haven't already been added to the community cards, add
        // them!
        if (allCards.size() < 5) {
            allCards.addAll(communityCards);
        }

        // Ranking frequencies
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
                    // Just exit if suit is not consistent
                    if (!allCards.get(z).getSuit().equals(first)) {
                        break;
                    }
                    if (z == allCards.size() - 1) {
                        return "Royal Flush";
                    }
                }
            }
        }
        // Use h as a count variable to check for consecutive amounts
        int h = 1;
        // Loop through array and find out if there is a 5 consecutive
        for (int i = 0; i < rFreq.length; i++) {
            if (rFreq[i] >= 1) {
                h++;
                // If 5 consecutive ranks
                if (h >= 5) {
                    // Then compare suits using similar strategy to before
                    String first = allCards.get(0).getSuit();
                    for (int z = 1; z < allCards.size(); z++) {
                        // Break if inconsistent suit
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
            // Just look for frequency of 4
            if (rFreq[i] == 4) {
                return "Four of a Kind";
            }
        }
        // Three is to check three cards of one ranks and two is for two cards of
        // another rank
        boolean three = false;
        boolean two = false;
        // Find three cards of one rank and two cards of another rank.
        for (int i = 0; i < rFreq.length; i++) {
            // Check for frequency of 3
            if (rFreq[i] == 3) {
                if (three) {
                    two = true;
                }
                three = true;
            }
            // Check for frequency of 2
            if (rFreq[i] == 2) {
                two = true;
            }
        }
        // Satisfies full house
        if (three && two) {
            return "Full House";
        }
        String first = allCards.get(0).getSuit();
        for (int z = 1; z < allCards.size(); z++) {
            // If suit inconsistent break
            if (!allCards.get(z).getSuit().equals(first)) {
                break;
            }
            // If last item check condition (5 same suits)
            if (z == allCards.size() - 1) {
                return "Flush";
            }
        }
        // Use h again like before
        h = 1;
        // Loop through array and find out if there is a 5 consecutive ranks
        for (int i = 0; i < rFreq.length; i++) {
            // Use same strategy as mentioned earlier
            if (rFreq[i] >= 1) {
                h++;
                if (h >= 5) {
                    return "Straight";
                }
            } else {
                h = 0;
            }
        }
        // Find frequency of 3
        for (int i = 0; i < rFreq.length; i++) {
            if (rFreq[i] == 3) {
                return "Three of a Kind";
            }
        }
        // Count for a frequency of 2 cards twice
        int count = 0;
        for (int i = 0; i < rFreq.length; i++) {
            if (rFreq[i] == 2) {
                count++;
            }
            if (count >= 2) {
                return "Two Pair";
            }
        }
        // Count for a frequency of two cards once
        for (int i = 0; i < rFreq.length; i++) {
            // System.out.println(rFreq[i]);
            if (rFreq[i] == 2) {
                return "A Pair";
            }
        }
        // Get highest card value for hand and community and determine if high card or
        // nothing
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
            // Check if the rank value is greater than max and if so set it to max rank
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
            // Get the ranking of the current card as a String and then get the value as a
            // number/index
            int index = Utility.getRankValue(allCards.get(i).getRank());
            // Increment frequency
            frequencies[index] = frequencies[index] + 1;
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
            // Increment frequency
            frequencies[index] = frequencies[index] + 1;
        }
        return frequencies;
    }

    @Override
    public String toString() {
        return hand.toString();
    }

}
