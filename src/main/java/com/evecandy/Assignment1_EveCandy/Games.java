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
            System.out.print("Wanna play choose (1-3): ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> GuessingGame.main(new String[0]);
                case 2 -> Factorial.main(new String[0]);
                case 3 -> JavaPatterns.main(new String[0]);
                case 0 -> {
                    System.out.println("Thank you for playing! Goodbye!");
                    playAgain = false;
                }

            }

        }
        scanner.close();

    }
}
