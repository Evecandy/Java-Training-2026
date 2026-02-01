package com.evecandy.Assignment3_EveCandy.exceptionHandling;

public class InsufficientFundsException extends Exception {

    private double currentBalance;
    private double requestedAmount;

    public InsufficientFundsException(double currentBalance, double requestedAmount) {
        super("Insufficient funds. Balance: KES " + String.format("%.2f", currentBalance)
                + ", Requested: KES " + String.format("%.2f", requestedAmount)
                + ", Shortfall: KES " + String.format("%.2f", requestedAmount - currentBalance));
        this.currentBalance = currentBalance;
        this.requestedAmount = requestedAmount;
    }

    public InsufficientFundsException(String message) {
        super(message);
        this.currentBalance = 0;
        this.requestedAmount = 0;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public double getRequestedAmount() {
        return requestedAmount;
    }

    // Shortfall = how much MORE money is needed
    public double getShortfall() {
        return requestedAmount - currentBalance;
    }
}
