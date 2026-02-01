package com.evecandy.Assignment3_EveCandy.exceptionHandling;

public class AccountNotFoundException extends Exception {

    private String accountNumber; // the account ID that wasn't found

    // Constructor: account number + message
    public AccountNotFoundException(String accountNumber, String message) {
        super(message);
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
