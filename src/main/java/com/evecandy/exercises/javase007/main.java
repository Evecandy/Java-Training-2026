package com.evecandy.exercises.javase007;

public class main {

    public static void main(String[] args) {

        System.out.println("=====Bank Account testing=====\n");

        System.out.println("----- Test1:Default constructor -----");
        // create account1, default constructor
        BankAccount account1 = new BankAccount();
        account1.displayBalance();

        System.out.println("\n----- Test2:Constructor with parameters -----");
        // create account2, constructor with account number and holder
        BankAccount account2 = new BankAccount("123456", "John Doe");
        account2.displayBalance();

        System.out.println("\n----- Test3:Constructor with all properties -----");
        // create account3, test constructor with all properties
        BankAccount account3 = new BankAccount("654321", "Laura Smith", 1500.0);
        account3.displayBalance();

        System.out.println("----- Test4:Deposit to account 2 -----");
        // test deposit method
        account2.deposit(5000.0);
        account2.displayBalance();

        System.out.println("\n----- Test5:Deposit to account 3 -----");
        account3.deposit(1000.0);
        account3.displayBalance();

        System.out.println("\n----- Test6:Withdraw from account 3 -----");
        // test withdraw method
        account3.withdraw(600.0);
        account3.displayBalance();

        System.out.println("\n----- Test7:Insufficient funds test -----");
        // insufficient funds test
        account3.withdraw(3000.0);
        account3.displayBalance();

        System.out.println("\n----- Test8:Negative values -----");
        account2.deposit(-5000.0);
        account2.withdraw(-1000);
        account2.displayBalance();

        System.out.println("\n===== Final state =====");
        System.out.println("\n Account:1");
        account1.displayBalance();
        System.out.println("\n Account:2");
        account2.displayBalance();
        System.out.println("\n Account:3");
        account3.displayBalance();
    }
}
