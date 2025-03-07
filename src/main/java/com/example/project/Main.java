package com.example.project;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static void l(String p) {
        System.out.println(p);
    }

    private static void newGame(Deck deck, Scanner s) {
        Game game = new Game();
        Player player = new Player();
        Player computer = new Player();
        ArrayList<Card> communityCards = new ArrayList<>();
        l("Press enter to draw...");
        s.nextLine();
        Card d = deck.drawCard();
        player.addCard(d);
        System.out.print("\033[H\033[2J");
        System.out.flush();
        for (int i = 0; i < 1; i++) {
            d = deck.drawCard();
            player.addCard(d);
            System.out.println(d.toString());
            l("Press enter to draw...");
            s.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
        for (int i = 0; i < 2; i++) {
            d = deck.drawCard();
            computer.addCard(d);
            System.out.println(d.toString());
            l("Press enter to draw...");
            s.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
        for (int i = 0; i < 3; i++) {
            d = deck.drawCard();
            communityCards.add(d);
            System.out.println(d.toString());
            l("Press enter to draw...");
            s.nextLine();
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
        l("Let's start, shall we?");
        while (true) {
            while (true) {
                l("Would you like to: ");
                l("View your hand (1)");
                l("View computer's hand (2)");
                l("View community cards (3)");
                l("Exit (4)");
                l("New game (5)");
                l("Play your hand (6)");
                String ans = s.next();
                System.out.print("\033[H\033[2J");
                System.out.flush();
                if (ans.equals("1")) {
                    l(player.getHand().toString());
                } else if (ans.equals("2")) {
                    l(computer.getHand().toString());
                } else if (ans.equals("3")) {
                    l(communityCards.toString());
                } else if (ans.equals("4")) {
                    return;
                } else if (ans.equals("5")) {
                    Deck deck2 = new Deck();
                    deck2.initializeDeck();
                    deck2.shuffleDeck();
                    newGame(deck2, s);
                    return;
                } else if (ans.equals("6")) {
                    l(Game.determineWinner(player, computer, player.playHand(communityCards), computer.playHand(communityCards), communityCards));
                }
                l("Press enter to continue");
                // Twice to avoid problems
                s.nextLine();
                s.nextLine();
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }

        }
    }

    public static void main(String[] args) {
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
            System.out.print("Now, keep drawing a card for yourself. I will draw after.\n");
            newGame(deck, s);
        } else {
            l("Ok... I'll wait.\n\n\n\n\n\n\n\n");
        }
    }
}
