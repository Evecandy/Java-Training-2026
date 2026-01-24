package com.evecandy.Assignment2_EveCandy.BasicBankAccount;

public class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    // 2. Constructor Overloading
    // Default Constructor
    public BankAccount() {
        this.accountNumber = "UNKNOWN";
        this.accountHolder = "UNKNOWN";
        this.balance = 0.0;
    }

    // Two-parameter Constructor
    public BankAccount(String accountNumber, String accountHolder) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = 0.0;
    }

    // Full Constructor
    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance >= 0 ? initialBalance : 0;
    }

    // 3. Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    // Only setAccountHolder is allowed; balance has no setter for security
    public void setAccountHolder(String accountHolder) {
        if (accountHolder != null && !accountHolder.trim().isEmpty()) {
            this.accountHolder = accountHolder;
        }
    }

    public double getBalance() {
        return balance;
    }

    // 4. Core Business Logic Methods
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: $" + amount);
            System.out.println("New balance: $" + balance);
        } else {
            System.out.println(" Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println(" Invalid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println(" Insufficient funds.");
            System.out.println("Available balance: $" + balance);
        } else {
            balance -= amount;
            System.out.println(" Withdrew: $" + amount);
            System.out.println("New balance: $" + balance);
        }
    }

    // 5. Method Overloading (Transfer)
    // Version 1: Simple transfer
    public boolean transfer(BankAccount destination, double amount) {
        if (amount <= 0) {
            System.out.println(" Invalid transfer amount");
            return false;
        }
        if (amount > balance) {
            System.out.println(" Insufficient funds for transfer.");
            return false;
        }

        this.balance -= amount;

        destination.balance += amount;

        System.out.println(" Transferred $" + amount + " to " + destination.accountHolder);
        return true;
    }

    // Version 2: Transfer with message
    public boolean transfer(BankAccount destination, double amount, String message) {
        if (transfer(destination, amount)) { // Reuse the logic from the first method
            System.out.println(" Message: " + message);
            return true;
        }
        return false;
    }

    public void displayAccountInfo() {
        System.out.println("\n========== Account Information ==========");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Balance: $" + String.format("%.2f", balance));
        System.out.println("=========================================\n");
    }
}
