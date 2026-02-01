package com.evecandy.Assignment3_EveCandy.OOPDesignImplimentation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Magazine extends LibraryItem {
    // Magazine-specific fields
    private int issueNumber;
    private LocalDate publicationDate;

    // Standard checkout period for magazines
    private static final int DEFAULT_CHECKOUT_DAYS = 3;

    // Constructor
    public Magazine(String itemId, String title, String author, int issueNumber, String publicationDate) {
        super(itemId, title, author);
        this.issueNumber = issueNumber;
        this.publicationDate = LocalDate.parse(publicationDate);
    }

    // Implement abstract method
    @Override
    public String getItemType() {
        return "Magazine";
    }

    // Override checkout to use default days for magazines
    public boolean checkout(String borrowerName) {
        return super.checkout(borrowerName, DEFAULT_CHECKOUT_DAYS);
    }

    // Implement abstract method
    @Override
    public void displayDetails() {
        System.out.println("\n==========  Magazine Details ==========");
        displayBasicInfo();
        System.out.println("Issue Number: #" + issueNumber);
        System.out.println("Publication Date: " + publicationDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));
        System.out.println("Standard Checkout: " + DEFAULT_CHECKOUT_DAYS + " days");
        System.out.println("========================================\n");
    }

    // Override late fee calculation for magazines (lowest rate)
    @Override
    protected void calculateLateFee(long daysOverdue) {
        double fee = daysOverdue * 2.0; // KES 2 per day for magazines
        System.out.println(" Late fee (Magazine rate): KES " + fee);
    }

    // Check if magazine is current
    public boolean isCurrent() {
        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
        return publicationDate.isAfter(threeMonthsAgo);
    }

    // Override checkout to prevent checkout of old magazines
    @Override
    public boolean checkout(String borrowerName, int daysToReturn) {
        if (!isCurrent()) {
            System.out.println("  This magazine is archived and cannot be checked out!");
            return false;
        }
        return super.checkout(borrowerName, daysToReturn);
    }

    // Getters
    public int getIssueNumber() {
        return issueNumber;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }
}
