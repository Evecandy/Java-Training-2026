package com.evecandy.exercises.javase007;

public class BankAccount {
    // properties
    public String accountNumber;
    public String accountHolder;
    private double balance;

    // constructor with default values
    public BankAccount() {
        this.accountNumber = "000000";
        this.accountHolder = "Unknown";
        this.balance = 0.0;
    }

    // constructor
    public BankAccount(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
    }

    // constructor with all the properties
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
            System.out.println("New Balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // method to withdraw money
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            System.out.println("Withdrew: " + amount);
            System.out.println("New Balance: " + balance);
        }

    }

    public void displayBalance() {
        System.out.println("\n===========================");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Current Balance:KSH " + balance);
    }
}
