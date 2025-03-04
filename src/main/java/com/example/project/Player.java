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
        String firstSuit = communityCards.get(0).getSuit();
        // Variable to keep track of consecutive suits
        int consecutiveSuits = 1;
        // Variable to keep track of if ranks are consecutive
        int consecutiveRanks = 1;
        // Amount of same suits
        int sameSuits = 0;
        // Amount of same ranks
        int sameRanks = 0;
        // Amount of times the cards match with royal flush
        int firstRank = Utility.getRankValue(communityCards.get(0).getRank());
        boolean isConsecutive = true;
        // ArrayList<Integer> rankingFrequencies = findRankingFrequency();
        // ArrayList<Integer> suitFrequencies = findSuitFrequency();

        int lastIndex = communityCards.size() - 1;
        ArrayList<Integer> oneRanks = new ArrayList<>();
        for (int i = 0; i < communityCards.size(); i++) {
            // Compare current card with royal flush sequence
            System.out.println(firstRank + " -> " + (Utility.getRankValue(communityCards.get(i).getRank())));
            if (isConsecutive && firstRank == (Utility.getRankValue(communityCards.get(i).getRank()))) {
                firstRank++;
                isConsecutive = false;
            }

            // Check if current suit is same as first to count consecutiveSuits
            boolean matchesWithFirstSuit = communityCards.get(i).getSuit().equals(firstSuit);
            boolean matchesWithFirstRank = communityCards.get(i).getSuit().equals(firstRank);
            if (matchesWithFirstSuit) {
                sameSuits++;
            }
            if (matchesWithFirstRank) {
                sameRanks++;
            } else {
                oneRanks.add(sameRanks);
                sameRanks = 0;
            }

            if (i != communityCards.size() - 1) {
                // Check if suits are consecutive
                boolean isConsecutiveSuits = Utility.getSuitValue(communityCards.get(i).getSuit()) == Utility
                        .getSuitValue(communityCards.get(i + 1).getSuit());
                if (isConsecutiveSuits) {
                    consecutiveSuits++;
                }
            }
            // Check for amount of same ranks
            // switch (hand) {
            // case RoyalFlush:
            // // If the card rank matches with the royal flush sequence
            // if (royalFlushValues[i].equals(communityCards.get(i).getRank())) {
            // // Do nothing
            // // continue;
            // } else {
            // // To help with StraightFlush, consecutiveSuits can be continued to use
            // if (consecutiveSuits != i) {
            // // However, if it's not equal to i, we can already rule out that the suits
            // are not the same
            // hand = Hands.FourofaKind;
            // continue;
            // } else {
            // // So in this case we can't be sure consecutive suits will have the correct
            // value
            // }
            // // Reset the loop
            // i = 0;
            // hand = Hands.StraightFlush;
            // }
            // continue;
            // case StraightFlush:
            // // To avoid out of bounds check that it's not last index and check that the
            // ranks are consecutive
            // if (i != lastIndex && Utility.getRankValue(communityCards.get(i).getRank())
            // == Utility.getRankValue(communityCards.get(i + 1).getRank())) {
            // // Nothing
            // } else {
            // // Reset the loop
            // i = 0;
            // hand = Hands.FourofaKind;
            // }
            // continue;
            // case FourofaKind:
            // if () {

            // }
            // return 9;
            // case FullHouse:
            // return 8;
            // case Flush:
            // return 7;
            // case Straight:
            // return 6;
            // case Three of a Kind:
            // return 5;
            // case Two Pair:
            // return 4;
            // case A Pair:
            // return 3;
            // case High Card:
            // return 2;
            // }

            // // If this is the last index, then we need to check the end condition, as an
            // example if there are consecutive suits because if we check later we can't
            // reset the loop
            // if (i == lastIndex) {
            // // If a sequence is found, the loop will end so check ending conditions here
            // switch (hand) {
            // case RoyalFlush:
            // // If consecutive suits has reached the size we know loop handled the
            // rankings so we can return royal flush
            // if (consecutiveSuits == communityCards.size()) {
            // return "Royal Flush";
            // } else {
            // // Reset the loop
            // i = 0;
            // hand = Hands.StraightFlush;
            // }
            // case StraightFlush:
            // if (i == lastIndex) {
            // if (consecutiveSuits == communityCards.size()) {
            // return "Straight Flush";
            // } else {
            // // Reset the loop
            // i = 0;
            // hand = Hands.FourofaKind;
            // }
            // }
            // case FourofaKind:
            // return 9;
            // case FullHouse:
            // return 8;
            // case Flush:
            // return 7;
            // case Straight:
            // return 6;
            // case Three of a Kind:
            // return 5;
            // case Two Pair:
            // return 4;
            // case A Pair:
            // return 3;
            // case High Card:
            // return 2;
            // }
            // }
        }
        System.out.println(communityCards.size() - 1);
        System.out.println(consecutiveSuits + " " + consecutiveRanks);
        if (consecutiveRanks == communityCards.size() - 1 && consecutiveSuits == communityCards.size() - 1) {
            return "Royal Flush";
        }
        if (consecutiveRanks == 5 && sameSuits == 5) {
            return "Straight Flush";
        }
        if (sameRanks == 4) {
            return "Four of a Kind";
        }
        // Ignore full house...
        if (sameSuits == 5) {
            return "Flush";
        }
        if (consecutiveRanks == 5) {
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
