package com.evecandy.Assignment1_EveCandy;

import java.util.Scanner;

public class JavaPatterns {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a pattern: ");
        System.out.println("1. Rectangle");
        System.out.println("2. Right-Angled Triangle");
        System.out.println("3. Pyramid");
        System.out.print("Enter your choice (1-3): ");
        int choice = scanner.nextInt();

        System.out.println("Enter the number of rows: ");
        int num = scanner.nextInt();

        if (choice == 1) { // rectangle

            for (int row = 1; row <= num; row++) {
                for (int column = 1; column <= num; column++) {
                    System.out.print("* ");
                }
                System.out.println();
            }
        } else if (choice == 2) { // right-angled triangle

            for (int row = 1; row <= num; row++) {
                for (int column = 1; column <= row; column++) {
                    System.out.print("* ");
                }
                System.out.println();
            }
        } else if (choice == 3) { // pyramid

            for (int row = 1; row <= num; row++) {
                for (int space = 1; space <= num - row; space++) {
                    System.out.print(" ");
                }
                for (int star = 1; star <= (2 * row - 1); star++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        } else {
            System.out.println("Invalid choice.");
        }

    }

}
