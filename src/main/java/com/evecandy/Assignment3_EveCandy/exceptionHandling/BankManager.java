package com.evecandy.Assignment3_EveCandy.exceptionHandling;

public class BankManager {
    public static void main(String[] args) {
        System.out.println("========== Bank Transaction System Test ==========\n");
        System.out.println("--- Test 1: Creating Valid Accounts ---\n");
        try {
            BankAccount account1 = new BankAccount("ACC001", "Alice Wanjiku", 1000);
            BankAccount account2 = new BankAccount("ACC002", "Bob Kimani", 500);
            BankAccount account3 = new BankAccount("ACC003", "Charlie Omondi", 2000);
            System.out.println("\n All accounts created successfully!");
            System.out.println("Total accounts: " + BankAccount.getTotalAccounts());
        } catch (InvalidTransactionException e) {
            System.out.println(" Account creation failed: " + e.getMessage());
        }

        System.out.println("\n--- Test 2: Invalid Account Creation ---\n");
        try {
            new BankAccount("ACC004", "Test", -500);
        } catch (InvalidTransactionException e) {
            System.out.println(" Expected error caught: " + e.getMessage());
        }

        System.out.println("\n--- Test 3: Negative Deposit ---\n");
        try {
            BankAccount account1 = BankAccount.getAccount("ACC001");
            account1.deposit(-100);
        } catch (InvalidTransactionException e) {
            System.out.println(" Transaction error: " + e.getMessage());
        } catch (AccountNotFoundException e) {
            System.out.println(" Account error: " + e.getMessage());
        }

        System.out.println("\n--- Test 4: Insufficient Funds ---\n");
        try {
            BankAccount account1 = BankAccount.getAccount("ACC001");
            account1.withdraw(2000);
        } catch (InsufficientFundsException e) {
            System.out.println(" Funds error: " + e.getMessage());
            System.out.println("   Current Balance: KES " + e.getCurrentBalance());
            System.out.println("   Requested Amount: KES " + e.getRequestedAmount());
            System.out.println("   Shortfall: KES " + e.getShortfall());
        } catch (InvalidTransactionException e) {
            System.out.println(" Transaction error: " + e.getMessage());
        } catch (AccountNotFoundException e) {
            System.out.println(" Account error: " + e.getMessage());
        }

        System.out.println("\n--- Test 5: Transfer to Non-existent Account ---\n");
        try {
            BankAccount account1 = BankAccount.getAccount("ACC001");
            account1.transfer("INVALID", 100);
        } catch (AccountNotFoundException e) {
            System.out.println(" Account error: " + e.getMessage());
            System.out.println("   Attempted Account: " + e.getAccountNumber());
        } catch (InsufficientFundsException e) {
            System.out.println(" Funds error: " + e.getMessage());
        } catch (InvalidTransactionException e) {
            System.out.println(" Transaction error: " + e.getMessage());
        }

        System.out.println("\n--- Test 6: Valid Operations ---\n");
        try {
            BankAccount account1 = BankAccount.getAccount("ACC001");
            BankAccount account2 = BankAccount.getAccount("ACC002");
            System.out.println("Depositing to ACC001:");
            account1.deposit(500);
            System.out.println("\nWithdrawing from ACC001:");
            account1.withdraw(200);
            System.out.println("\nTransferring from ACC001 to ACC002:");
            account1.transfer("ACC002", 300);
            System.out.println("\n--- Account Balances After Transactions ---");
            account1.displayAccountInfo();
            account2.displayAccountInfo();
        } catch (AccountNotFoundException e) {
            System.out.println(" Account error: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println(" Funds error: " + e.getMessage());
        } catch (InvalidTransactionException e) {
            System.out.println("Transaction error: " + e.getMessage());
        }

        System.out.println("\n--- Test 7: Comprehensive Exception Handling ---\n");
        try {
            BankAccount account1 = BankAccount.getAccount("ACC001");
            account1.deposit(-100);
            account1.withdraw(5000);
            account1.transfer("FAKE", 50);
        } catch (InsufficientFundsException e) {
            System.out.println(" Funds error: " + e.getMessage());
        } catch (InvalidTransactionException e) {
            System.out.println(" Transaction error: " + e.getMessage());
        } catch (AccountNotFoundException e) {
            System.out.println(" Account error: " + e.getMessage());
        } finally {
            System.out.println(" Transaction processing completed");
        }

        System.out.println("\n--- Test 8: Transaction History ---");
        try {
            BankAccount account1 = BankAccount.getAccount("ACC001");
            account1.displayTransactionHistory(10);
        } catch (AccountNotFoundException e) {
            System.out.println(" Account error: " + e.getMessage());
        }

        System.out.println("\n--- Test 9: Zero Amount Transactions ---\n");
        try {
            BankAccount account1 = BankAccount.getAccount("ACC001");
            account1.deposit(0);
        } catch (InvalidTransactionException e) {
            System.out.println(" Expected error: " + e.getMessage());
        } catch (AccountNotFoundException e) {
            System.out.println(" Account error: " + e.getMessage());
        }

        System.out.println("\n--- Test 10: Transfer to Same Account ---\n");
        try {
            BankAccount account1 = BankAccount.getAccount("ACC001");
            account1.transfer("ACC001", 100);
        } catch (InvalidTransactionException e) {
            System.out.println(" Expected error: " + e.getMessage());
        } catch (AccountNotFoundException | InsufficientFundsException e) {
            System.out.println(" Account error: " + e.getMessage());
        }

        System.out.println("\n--- Test 11: Closed Account Operations ---\n");
        try {
            BankAccount account3 = BankAccount.getAccount("ACC003");
            account3.closeAccount();
            account3.deposit(100);
        } catch (InvalidTransactionException e) {
            System.out.println(" Expected error: " + e.getMessage());
        } catch (AccountNotFoundException e) {
            System.out.println(" Account error: " + e.getMessage());
        }

        System.out.println("\n========== System Summary ==========");
        System.out.println("Total accounts in system: " + BankAccount.getTotalAccounts());
        try {
            System.out.println("\nFinal Account Balances:");
            BankAccount.getAccount("ACC001").displayAccountInfo();
            BankAccount.getAccount("ACC002").displayAccountInfo();
            BankAccount.getAccount("ACC003").displayAccountInfo();
        } catch (AccountNotFoundException e) {
            System.out.println("Error displaying accounts: " + e.getMessage());
        }
    }
}