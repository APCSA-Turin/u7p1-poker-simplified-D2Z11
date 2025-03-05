package com.example.project;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    private ArrayList<Card> hand;
    private ArrayList<Card> allCards; // the current community cards + hand
    String[] suits = Utility.getSuits();
    String[] ranks = Utility.getRanks();
    // private String[] royalFlushValues = { "10", "J", "Q", "K", "A" };

    // private enum Hands {
    //     RoyalFlush,
    //     StraightFlush,
    //     FourofaKind
    // };

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
        allCards.add(c);
    }

    public String playHand(ArrayList<Card> communityCards) {
        // Current hand that is being checked
        // Hands hand = Hands.RoyalFlush;
        // Variable to allow the loop to check each card for if its the same as the
        // first suit
        int firstSuit = Utility.getSuitValue(communityCards.get(0).getSuit());
        // Variable to keep track of consecutive suits
        int consecutiveSuits = 1;
        // Amount of same suits
        int sameSuits = 0;
        // Amount of same ranks
        int sameRanks = 0;
        // Amount of times the cards match with royal flush
        int firstRank = Utility.getRankValue(communityCards.get(0).getRank());
        int firstRankCopy = Utility.getRankValue(communityCards.get(0).getRank());
        // Keep track of if ranks are consecutive
        boolean isConsecutiveRanks = true;

        // ArrayList<Integer> rankingFrequencies = findRankingFrequency();
        // ArrayList<Integer> suitFrequencies = findSuitFrequency();

        int lastIndex = communityCards.size() - 1;
        ArrayList<Integer> oneRanks = new ArrayList<>();
        for (int i = 0; i < communityCards.size(); i++) {
            // Compare current card with royal flush sequence
            System.out.println(firstRankCopy + " -> " + (Utility.getRankValue(communityCards.get(i).getRank())));
            if (isConsecutiveRanks && firstRankCopy == (Utility.getRankValue(communityCards.get(i).getRank()))) {
                firstRankCopy++;
            } else {
                isConsecutiveRanks = false;
            }
            if (firstSuit == (Utility.getSuitValue(communityCards.get(i).getSuit()))) {
                sameSuits++;
            }
            if (firstRank == (Utility.getRankValue(communityCards.get(i).getRank()))) {
                sameRanks++;
            }

            // Check if current suit is same as first to count consecutiveSuits
            // boolean matchesWithFirstSuit = communityCards.get(i).getSuit().equals(firstSuit);
            // boolean matchesWithFirstRank = communityCards.get(i).getSuit().equals(firstRank);
            // if (matchesWithFirstSuit) {
            //     sameSuits++;
            // }
            // if (matchesWithFirstRank) {
            //     sameRanks++;
            // } else {
            //     oneRanks.add(sameRanks);
            //     sameRanks = 0;
            // }
        }
        System.out.println(sameRanks + " " + communityCards.size());
        System.out.println(consecutiveSuits + " " + isConsecutiveRanks);
        if (isConsecutiveRanks && consecutiveSuits == communityCards.size()) {
            return "Royal Flush";
        }
        if (isConsecutiveRanks && sameSuits == communityCards.size()) {
            return "Straight Flush";
        }
        if (sameRanks == 4) {
            return "Four of a Kind";
        }
        // Ignore full house...
        if (sameSuits == 5) {
            return "Flush";
        }
        if (isConsecutiveRanks) {
            return "Straight";
        }
        // Ignore Three of a Kind
        // Ignore two pair and one pair
        return "Nothing";
    }

    public void sortAllCards() {
    }

    public ArrayList<Integer> findRankingFrequency() {
        ArrayList<Integer> frequencies = new ArrayList<>();
        // Based on the ranking value switch statement in Utility.java: ["2", "3", "4",
        // "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"]
        // Loop through the hand
        for (int i = 0; i < hand.size(); i++) {
            // Get the ranking of the current card as a String and then get the value as a
            // number/index
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
        // Based on the ranking value switch statement in Utility.java: ["♠","♥","♣",
        // "♦”]
        // Loop through the hand
        for (int i = 0; i < hand.size(); i++) {
            // Get the suit of the current card as a String and then get the value as a
            // number/index
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
