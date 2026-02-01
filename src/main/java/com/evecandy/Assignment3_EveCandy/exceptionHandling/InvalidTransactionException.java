package com.evecandy.Assignment3_EveCandy.exceptionHandling;

public class InvalidTransactionException extends RuntimeException {

    private String operation; // e.g. "Deposit", "Withdrawal"
    private double amount; // the bad amount that was passed in

    public InvalidTransactionException(String message) {
        super(message);
        this.operation = "Unknown";
        this.amount = 0;
    }

    public InvalidTransactionException(String operation, double amount, String message) {
        super(message);
        this.operation = operation;
        this.amount = amount;
    }

    public String getOperation() {
        return operation;
    }

    public double getAmount() {
        return amount;
    }
}
