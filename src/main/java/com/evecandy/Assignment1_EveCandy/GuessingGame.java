package com.evecandy.Assignment1_EveCandy;

import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int SecretNumber = (int) (Math.random() * 100) + 1;
        int trials = 5;

        System.out.println("Welcome 👋🏾");
        System.out.println("I'm thinking of a number between 1-100");
        System.out.println("You have " + trials + " chances to guess it.");

        while (trials > 0) {
            System.out.print("Guess the number: ");
            int number = scanner.nextInt();

            if (number == SecretNumber) {
                System.out.println("Correct !!");
                break;
            } else if (number < SecretNumber) {
                System.out.println("Too low!");
            } else {
                System.out.println("Too high!");
            }
            trials--;

            if (trials > 0) {
                System.out.println("You have " + trials + " trials left.");
            } else {
                System.out.println("Game over. The correct number was " + SecretNumber);
            }

        }

    }
}
