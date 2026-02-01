package com.evecandy.Assignment3_EveCandy.exceptionHandling;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private static final double MINIMUM_DEPOSIT = 1.0;
    private static final double MINIMUM_WITHDRAWAL = 1.0;
    private static final double WITHDRAWAL_FEE = 2.50;
    private static final double MINIMUM_BALANCE = 0.0;

    private static final List<BankAccount> accountRegistry = new ArrayList<>();

    private final String accountNumber;
    private String accountHolder;
    private double balance;
    private final List<Transaction> transactionHistory;
    private boolean isActive;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance)
            throws InvalidTransactionException {
        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new InvalidTransactionException("Account creation failed: Account number cannot be empty");
        }
        if (accountHolder == null || accountHolder.trim().isEmpty()) {
            throw new InvalidTransactionException("Account creation failed: Account holder name cannot be empty");
        }
        if (initialBalance < 0) {
            throw new InvalidTransactionException("Account creation", initialBalance,
                    "Initial balance cannot be negative");
        }

        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        this.isActive = true;

        accountRegistry.add(this);

        if (initialBalance > 0) {
            recordTransaction("Initial Deposit", initialBalance, balance);
        }

        System.out.println(" Account created successfully!");
        System.out.println("   Account: " + accountNumber);
        System.out.println("   Holder: " + accountHolder);
        System.out.println("   Initial Balance: KES " + String.format("%.2f", initialBalance));
    }

    public void deposit(double amount) throws InvalidTransactionException {
        if (!isActive) {
            throw new InvalidTransactionException("Deposit failed: Account is inactive");
        }
        if (amount < 0) {
            throw new InvalidTransactionException("Deposit", amount,
                    "Deposit amount cannot be negative");
        }
        if (amount == 0) {
            throw new InvalidTransactionException("Deposit", amount,
                    "Deposit amount cannot be zero");
        }
        if (amount < MINIMUM_DEPOSIT) {
            throw new InvalidTransactionException("Deposit", amount,
                    "Deposit amount must be at least KES " + MINIMUM_DEPOSIT);
        }

        balance += amount;
        recordTransaction("Deposit", amount, balance);

        System.out.println(" Deposit successful!");
        System.out.println("   Amount: KES " + String.format("%.2f", amount));
        System.out.println("   New Balance: KES " + String.format("%.2f", balance));
    }

    public void withdraw(double amount) throws InsufficientFundsException, InvalidTransactionException {
        if (!isActive) {
            throw new InvalidTransactionException("Withdrawal failed: Account is inactive");
        }
        if (amount < 0) {
            throw new InvalidTransactionException("Withdrawal", amount,
                    "Withdrawal amount cannot be negative");
        }
        if (amount == 0) {
            throw new InvalidTransactionException("Withdrawal", amount,
                    "Withdrawal amount cannot be zero");
        }
        if (amount < MINIMUM_WITHDRAWAL) {
            throw new InvalidTransactionException("Withdrawal", amount,
                    "Withdrawal amount must be at least KES " + MINIMUM_WITHDRAWAL);
        }

        double totalDeduction = amount + WITHDRAWAL_FEE;

        if (balance < totalDeduction) {
            throw new InsufficientFundsException(balance, totalDeduction);
        }
        if (balance - totalDeduction < MINIMUM_BALANCE) {
            throw new InsufficientFundsException(
                    "Withdrawal would bring balance below minimum required (KES " + MINIMUM_BALANCE + ")");
        }

        balance -= totalDeduction;
        recordTransaction("Withdrawal", -amount, balance);
        recordTransaction("Withdrawal Fee", -WITHDRAWAL_FEE, balance);

        System.out.println("Withdrawal successful!");
        System.out.println("   Amount: KES " + String.format("%.2f", amount));
        System.out.println("   Fee: KES " + String.format("%.2f", WITHDRAWAL_FEE));
        System.out.println("   Total Deducted: KES " + String.format("%.2f", totalDeduction));
        System.out.println("   New Balance: KES " + String.format("%.2f", balance));
    }

    public void transfer(String toAccountNumber, double amount)
            throws AccountNotFoundException, InsufficientFundsException, InvalidTransactionException {
        if (!isActive) {
            throw new InvalidTransactionException("Transfer failed: Source account is inactive");
        }
        if (amount <= 0) {
            throw new InvalidTransactionException("Transfer", amount,
                    "Transfer amount must be positive");
        }

        BankAccount toAccount = findAccount(toAccountNumber);

        if (toAccount == null) {
            throw new AccountNotFoundException(toAccountNumber,
                    "Destination account does not exist");
        }
        if (toAccountNumber.equals(this.accountNumber)) {
            throw new InvalidTransactionException("Transfer failed: Cannot transfer to the same account");
        }
        if (!toAccount.isActive) {
            throw new InvalidTransactionException("Transfer failed: Destination account is inactive");
        }
        if (balance < amount) {
            throw new InsufficientFundsException(balance, amount);
        }

        balance -= amount;
        toAccount.balance += amount;

        recordTransaction("Transfer to " + toAccountNumber, -amount, balance);
        toAccount.recordTransaction("Transfer from " + accountNumber, amount, toAccount.balance);

        System.out.println(" Transfer successful!");
        System.out.println("   From: " + accountNumber + " (" + accountHolder + ")");
        System.out.println("   To: " + toAccountNumber + " (" + toAccount.accountHolder + ")");
        System.out.println("   Amount: KES " + String.format("%.2f", amount));
        System.out.println("   Your New Balance: KES " + String.format("%.2f", balance));
    }

    public void transfer(BankAccount toAccount, double amount)
            throws AccountNotFoundException, InsufficientFundsException, InvalidTransactionException {
        if (toAccount == null) {
            throw new AccountNotFoundException("UNKNOWN", "Destination account object is null");
        }
        transfer(toAccount.getAccountNumber(), amount);
    }

    private static BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accountRegistry) {
            if (account.accountNumber.equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public static BankAccount getAccount(String accountNumber) throws AccountNotFoundException {
        BankAccount account = findAccount(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException(accountNumber, "No account found with this number");
        }
        return account;
    }

    private void recordTransaction(String type, double amount, double balanceAfter) {
        Transaction transaction = new Transaction(type, amount, balanceAfter);
        transactionHistory.add(transaction);
    }

    public void displayAccountInfo() {
        System.out.println("\n========== Account Information ==========");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Current Balance: KES " + String.format("%.2f", balance));
        System.out.println("Status: " + (isActive ? "Active " : "Inactive "));
        System.out.println("Total Transactions: " + transactionHistory.size());
        System.out.println("=========================================\n");
    }

    public void displayTransactionHistory(int limit) {
        System.out.println("\n========== Transaction History ==========");
        System.out.println("Account: " + accountNumber + " (" + accountHolder + ")");
        System.out.println("=========================================");

        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        int count = Math.min(limit, transactionHistory.size());
        System.out.println("Showing last " + count + " transaction(s):\n");

        for (int i = transactionHistory.size() - count; i < transactionHistory.size(); i++) {
            Transaction t = transactionHistory.get(i);
            System.out.println((i + 1) + ". " + t);
        }
        System.out.println("=========================================\n");
    }

    public void closeAccount() {
        isActive = false;
        System.out.println("  Account " + accountNumber + " has been closed.");
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public boolean isActive() {
        return isActive;
    }

    public static int getTotalAccounts() {
        return accountRegistry.size();
    }

    public void setAccountHolder(String accountHolder) {
        if (accountHolder != null && !accountHolder.trim().isEmpty()) {
            this.accountHolder = accountHolder;
        }
    }

    private static class Transaction {
        private final String type;
        private final double amount;
        private final double balanceAfter;
        private final LocalDateTime timestamp;

        public Transaction(String type, double amount, double balanceAfter) {
            this.type = type;
            this.amount = amount;
            this.balanceAfter = balanceAfter;
            this.timestamp = LocalDateTime.now();
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return String.format("%s | %-20s | %s KES %8.2f | Balance: KES %10.2f",
                    timestamp.format(formatter),
                    type,
                    amount >= 0 ? "+" : "",
                    amount,
                    balanceAfter);
        }
    }
}
