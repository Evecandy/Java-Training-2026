package com.evecandy.Assignment2_EveCandy.BasicBankAccount;

public class CheckingAccount extends BankAccount {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, String accountHolder, double initialBalance, double overdraftLimit) {
    
super(accountNumber, accountHolder, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    // Method Overriding: Allows going negative
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println(" Invalid withdrawal amount");
            return;
        }

        double balanceAfterWithdrawal = getBalance() - amount;

        if (balanceAfterWithdrawal < -overdraftLimit) {
            System.out.println(" Exceeds overdraft limit.");
            System.out.println("Overdraft limit: $" + overdraftLimit);
            System.out.println("Available (including overdraft): $" + 
                              (getBalance() + overdraftLimit));
            return;
        }

        super.withdraw(amount);

        if (getBalance() < 0) {
            System.out.println(" Account is overdrawn by: $" + 
                              String.format("%.2f", Math.abs(getBalance())));
        }
    }

    @Override
    public void displayAccountInfo() {
        System.out.println("\n========== Checking Account Information ==========");
        System.out.println("Account Number: " + getAccountNumber());
        System.out.println("Account Holder: " + getAccountHolder());
        System.out.println("Balance: $" + String.format("%.2f", getBalance()));
        System.out.println("Overdraft Limit: $" + overdraftLimit);
        System.out.println("Available (including overdraft): $" + 
                          (getBalance() + overdraftLimit));
        System.out.println("==================================================\n");
    }


}
