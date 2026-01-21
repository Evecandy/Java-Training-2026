package com.evecandy.Assignment1_EveCandy;

import java.util.Scanner;

public class Games {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            System.out.println("\n========================");
            System.out.println("WELCOME TO THE GAMES MENU 🎉");
            System.out.println("========================\n");
            System.out.println("1. Guessing Game");
            System.out.println("2. Factorial Calculator");
            System.out.println("3. Java patterns");
            System.out.println("0. Exit");
            System.out.print("Wanna play? Choose (1-3): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> GuessingGame.main(new String[0]);
                case 2 -> Factorial.main(new String[0]);
                case 3 -> JavaPatterns.main(new String[0]);
                case 0 -> {
                    System.out.println("Goodbye! 🫡");
                    playAgain = false;
                }

            }

        }
        scanner.close();

    }
}

// Out of everything I've done so far, I think this Games Menu is the best.
// It ties everything together and makes it easy to access each game
// or functionality from one place. Also I feel like making the UI more
// beautiful 😊.
// Just realized the guessing game says between 1-50 but the random number is
// between 1-100.