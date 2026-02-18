package com.evecandy.exercises.javase020.terminaloperations;

import java.util.*;
import java.util.stream.*;

class Transaction {
    String type; // "CONTRIBUTION" or "WITHDRAWAL"
    double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return type + ": " + amount;
    }
}

public class PensionReporting {
    public static void main(String[] args) {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("CONTRIBUTION", 500.00),
                new Transaction("WITHDRAWAL", 200.00),
                new Transaction("CONTRIBUTION", 600.00),
                new Transaction("CONTRIBUTION", 450.00),
                new Transaction("WITHDRAWAL", 100.00));

        // 1. Reduce: Calculate total net flow (Contributions - Withdrawals)
        // Note: This logic assumes we treat withdrawals as negatives in the reduction,
        // or we filter first. Here is a simple sum of ALL amounts.
        Double totalVolume = transactions.stream()
                .map(Transaction::getAmount)
                .reduce(0.0, Double::sum);
        System.out.println("Total Transaction Volume: $" + totalVolume);

        // 2. GroupingBy: Group transactions by Type
        Map<String, List<Transaction>> transactionsByType = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getType));
        System.out.println("Transactions by Type: " + transactionsByType);

        // 3. PartitioningBy: Split into "High Value" (> 400) and "Low Value"
        Map<Boolean, List<Transaction>> partitionedTransactions = transactions.stream()
                .collect(Collectors.partitioningBy(t -> t.getAmount() > 400));
        System.out.println("High Value Transactions: " + partitionedTransactions.get(true));

        // 4. Statistics: Get count, sum, min, max, average of amounts
        DoubleSummaryStatistics stats = transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .summaryStatistics();

        System.out.println("Transaction Stats:");
        System.out.println("  Count: " + stats.getCount());
        System.out.println("  Sum:   " + stats.getSum());
        System.out.println("  Max:   " + stats.getMax());
        System.out.println("  Avg:   " + stats.getAverage());
    }

}
