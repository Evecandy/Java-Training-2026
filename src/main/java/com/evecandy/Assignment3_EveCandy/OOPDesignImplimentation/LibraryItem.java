package com.evecandy.Assignment3_EveCandy.OOPDesignImplimentation;

import java.time.LocalDate;

public abstract class LibraryItem {
    protected String itemId;
    protected String title;
    protected String author;
    protected boolean isAvailable;
    protected LocalDate dueDate;
    protected String borrowerName;

    // Static counter for tracking total items
    private static int totalItems = 0;

    // Constructor
    public LibraryItem(String itemId, String title, String author) {
        this.itemId = itemId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
        this.dueDate = null;
        this.borrowerName = null;
        totalItems++;
    }

    // Abstract method - each subclass implements differently
    public abstract String getItemType();

    // Concrete method - checkout logic (can be overridden)
    public boolean checkout(String borrowerName, int daysToReturn) {
        if (!isAvailable) {
            System.out.println(getItemType() + " '" + title + "' is already checked out!");
            return false;
        }

        if (daysToReturn <= 0) {
            System.out.println(" Invalid number of days!");
            return false;
        }

        isAvailable = false;
        this.borrowerName = borrowerName;
        dueDate = LocalDate.now().plusDays(daysToReturn);

        System.out.println(getItemType() + " '" + title + "' checked out successfully!");
        System.out.println("   Borrower: " + borrowerName);
        System.out.println("   Due Date: " + dueDate);

        return true;
    }

    // Overloaded checkout - without borrower name
    public boolean checkout(int daysToReturn) {
        return checkout("Anonymous", daysToReturn);
    }

    // Concrete method - return item logic
    public boolean returnItem() {
        if (isAvailable) {
            System.out.println(getItemType() + " '" + title + "' is already available!");
            return false;
        }

        // Check if overdue
        if (isOverdue()) {
            long daysOverdue = java.time.temporal.ChronoUnit.DAYS.between(dueDate, LocalDate.now());
            System.out.println(" Item was overdue by " + daysOverdue + " day(s)!");
            calculateLateFee(daysOverdue);
        }

        isAvailable = true;
        String returner = borrowerName;
        borrowerName = null;
        dueDate = null;

        System.out.println(getItemType() + " '" + title + "' returned successfully!");
        System.out.println("   Returned by: " + returner);

        return true;
    }

    // Check if item is overdue
    public boolean isOverdue() {
        if (isAvailable || dueDate == null) {
            return false;
        }
        return LocalDate.now().isAfter(dueDate);
    }

    // Calculate late fee - can be overridden by subclasses
    protected void calculateLateFee(long daysOverdue) {
        double fee = daysOverdue * 10.0; // KES 10 per day
        System.out.println("Late fee: KES " + fee);
    }

    // Abstract method - each subclass displays differently
    public abstract void displayDetails();

    // Display basic info (helper for subclasses)
    protected void displayBasicInfo() {
        System.out.println("Item ID: " + itemId);
        System.out.println("Title: " + title);
        System.out.println("Author/Creator: " + author);
        System.out.println("Status: " + (isAvailable ? "Available " : "Checked Out 📚"));

        if (!isAvailable) {
            System.out.println("Borrowed by: " + borrowerName);
            System.out.println("Due Date: " + dueDate);
            if (isOverdue()) {
                System.out.println(" OVERDUE!");
            }
        }
    }

    // Getters
    public String getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    // Static method
    public static int getTotalItems() {
        return totalItems;
    }

}
