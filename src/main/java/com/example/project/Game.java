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

        // Check each scenario
        switch (p1Hand) {
            case "Royal Flush":
                break;
            case "Straight Flush":
                break;
            case "Four of a Kind":
                break;
            case "Full House":
                break;
            case "Flush":
                break;
            case "Straight":
                break;
            case "Three of a Kind":
                break;
            case "Two Pair":
                break;
            case "Pair":
                break;
            case "High Card":
                break;
        }

        return "Error";
    }

    public static void play() { // simulate card playing

    }

}