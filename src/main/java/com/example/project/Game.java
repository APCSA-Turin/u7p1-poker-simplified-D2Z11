package com.example.project;

import java.util.ArrayList;

public class Game {
    public static String determineWinner(Player p1, Player p2, String p1Hand, String p2Hand,
            ArrayList<Card> communityCards) {
        // This method should be developed to compare hand rankings in the correct
        // order, from the highest hand (e.g., Royal Flush) to the lowest (e.g., High
        // Card).
        // Tiebreakers (e.g., comparing the highest card in case of two players having
        // the same hand rank) should be accounted for.
                
        int one = Utility.getHandRanking(p1Hand);
        int two = Utility.getHandRanking(p2Hand);
        if (one > two) {
            return "Player 1 wins!";
        } else if (one < two) {
            return "Player 2 wins!";
        } else {
            if (p1.getHighestCardValue(p1.getHand()) > p2.getHighestCardValue(p2.getHand())) {
                return "Player 1 wins!";
            } else if (p1.getHighestCardValue(p1.getHand()) < p2.getHighestCardValue(p2.getHand())) {
                return "Player 2 wins!";
            }
            return "Tie!";
        }
    }

    public static void play() { // simulate card playing

    }

}