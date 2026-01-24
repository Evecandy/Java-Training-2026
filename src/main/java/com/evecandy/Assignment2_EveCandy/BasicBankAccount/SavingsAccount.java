package com.evecandy.Assignment2_EveCandy.BasicBankAccount;

public class SavingsAccount extends BankAccount {
    private double interestRate;
    private static final double MINIMUM_BALANCE = 100.0;

    // Constructor using super to initialize parent class fields
    public SavingsAccount(String accountNumber, String accountHolder,
            double initialBalance, double interestRate) {
        super(accountNumber, accountHolder, initialBalance);
        this.interestRate = interestRate;
    }

    public void addInterest() {
        double interest = getBalance() * interestRate;
        deposit(interest); // Inherited method
        System.out.println(" Interest added: $" + String.format("%.2f", interest));
    }

    // Method Overriding: withdraw
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println(" Invalid withdrawal amount!");
            return;
        }

        double balanceAfterWithdrawal = getBalance() - amount;

        if (balanceAfterWithdrawal < MINIMUM_BALANCE) {
            System.out.println(" Cannot withdraw! Minimum balance is $" + MINIMUM_BALANCE);
            System.out.println("Available for withdrawal: $" + (getBalance() - MINIMUM_BALANCE));
            return;
        }

        super.withdraw(amount); // Reuse parent logic for the actual deduction
    }

    @Override
    public void displayAccountInfo() {
        System.out.println("\n========== Savings Account Information ==========");
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Account Holder: " + getAccountHolder());
        System.out.println("Balance: $" + String.format("%.2f", getBalance()));
        System.out.println("Interest Rate: " + (interestRate * 100) + "%");
        System.out.println("Minimum Balance: $" + MINIMUM_BALANCE);
        System.out.println("=================================================\n");
    }
}
