package com.evecandy;

import java.util.Scanner;

public class ATM {
    public static void main(String[] args) {
        int age;
        String name;

        double balance;
        double depositAmount;
        double withdrawAmount;
        final double WITHDRAWAL_FEE = 2.50;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        name = scanner.nextLine();
        System.out.print("Enter your age: ");
        age = scanner.nextInt();
        if (age < 18) {
            System.out.println("Sorry, you must be at least 18 years old to use this ATM.");
            scanner.close();
            return;
        }

        System.out.println("Welcome, " + name + "! You are " + age + " years old.");
        System.out.print("Enter your starting balance: $");
        double balance = scanner.nextDouble();
        System.out.println("Your current balance is: $" + balance);
        System.out.print("Enter amount to deposit: $");
        depositAmount = scanner.nextDouble();
        if (depositAmount > 0) {
            balance += depositAmount;
            System.out.println("Deposited: $" + depositAmount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
        System.out.println("Your new balance is: $" + balance);

        System.out.print("Enter amount to withdraw: $");
        withdrawAmount = scanner.nextDouble();
        if (withdrawAmount + WITHDRAWAL_FEE > balance) {
            System.out.println("Insufficient funds for this withdrawal including fees.");
        } else if (withdrawAmount <= 0) {
            System.out.println("Invalid withdrawal amount.");
        } else {
            balance = balance - (withdrawAmount + WITHDRAWAL_FEE);
            System.out.println("Withdrew: $" + withdrawAmount + " (including fee of $" + WITHDRAWAL_FEE + ")");

        }
        System.out.println("Your final balance is: $" + balance);
        scanner.close();
    }
}
