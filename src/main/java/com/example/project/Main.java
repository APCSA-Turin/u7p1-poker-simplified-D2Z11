package com.example.project;

import java.util.Scanner;

public class Main {
    private static void l(String p) {
        System.out.println(p);
    }

    public static void main(String[] args) {
        while (true) {
            l("Would you like to play a game? (y/n)");
            Scanner s = new Scanner(System.in);
            if (s.next().equals("y")) {
                Deck deck = new Deck();
                deck.initializeDeck();
                
                l("I recommend you shuffle first. No funny business, we are playing a game of life or death.");
                l("Shuffle? (y/n)");
                if (s.next().equals("y")) {
                    deck.shuffleDeck();
                }
                while (true) {
                    l("Would you like to: ");
                    l("View player hand (1)");
                    l("View community cards (2)");
                    l("Exit this menu (3)");
                    if (s.next().equals("3")) {
                        break;
                    }
                }

                System.out.print("Now, keep drawing a card for yourself. I will draw after.");
                while (true) {
                    l("\nPress enter to draw...");
                    s.next();
                    Card d = deck.drawCard();
                    System.out.print(d.toString());
                    
                }
            } else {
                l("Ok... I'll wait.\n\n\n\n\n\n\n\n");
            }
        }
    }
}
