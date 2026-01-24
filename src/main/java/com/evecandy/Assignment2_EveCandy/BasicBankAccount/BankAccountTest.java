package com.evecandy.Assignment2_EveCandy.BasicBankAccount;

public class BankAccountTest {
    public static void main(String[] args) {
        System.out.println("========== Bank Account Assessment Test ==========\n");

        // Test 1: Constructor Overloading
        System.out.println("--- Test 1: Constructor Overloading ---");
        BankAccount account1 = new BankAccount(); // Default
        BankAccount account2 = new BankAccount("ACC001", "Alice"); // 2-Args
        BankAccount account3 = new BankAccount("ACC002", "Bob", 5000); // 3-Args

        account1.displayAccountInfo();
        account2.displayAccountInfo();
        account3.displayAccountInfo();

        // Test 2: Encapsulation
        System.out.println("--- Test 2: Encapsulation ---");
        // account3.balance = 9999; // This would throw an error because balance is
        // private!
        System.out.println("Must use: account3.getBalance() = $" + account3.getBalance());
        System.out.println(" Encapsulation working.\n");

        // Test 3: Deposit and Withdraw
        System.out.println("--- Test 3: Deposit and Withdraw ---");
        account2.deposit(1000);
        account2.withdraw(300);
        account2.withdraw(900);
        account2.displayAccountInfo();

        // Test 4: Method Overloading (Transfer)
        System.out.println("--- Test 4: Method Overloading ---");
        account3.transfer(account2, 500); // Version 1
        System.out.println();
        account3.transfer(account2, 300, "Payment for services"); // Version 2

        // Test 5: Inheritance (Savings)
        System.out.println("\n--- Test 5: Inheritance - SavingsAccount ---");
        SavingsAccount savings = new SavingsAccount("SAV001", "Charlie", 2000, 0.05);
        savings.addInterest();
        savings.withdraw(1950); // Should fail (hits min balance)
        savings.displayAccountInfo();

        // Test 6: Inheritance (Checking)
        System.out.println("--- Test 6: Inheritance - CheckingAccount ---");
        CheckingAccount checking = new CheckingAccount("CHK001", "Diana", 500, 1000);
        checking.withdraw(800);
        checking.displayAccountInfo();

        // Test 7: Polymorphism
        System.out.println("\n--- Test 7: Polymorphism ---");
        BankAccount[] accounts = { account3, savings, checking };

        double totalBalance = 0;
        for (BankAccount acc : accounts) {

            System.out.println(acc.getAccountHolder() + ": $" + String.format("%.2f", acc.getBalance()));
            totalBalance += acc.getBalance();
        }
        System.out.println("\n Total Bank Reserve: $" + String.format("%.2f", totalBalance));

    }
}
