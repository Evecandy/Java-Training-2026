package com.evecandy.Assignment3_EveCandy.OOPDesignImplimentation;

public class DVD extends LibraryItem {
    // DVD-specific fields
    private int duration; // in minutes
    private String rating; // e.g., PG, PG-13, R

    private static final int DEFAULT_CHECKOUT_DAYS = 7;

    public DVD(String itemId, String title, String author, int duration, String rating) {
        super(itemId, title, author);
        this.duration = duration;
        this.rating = rating;
    }

    @Override
    public String getItemType() {
        return "DVD";
    }

    // Override checkout to use default days for DVDs
    public boolean checkout(String borrowerName) {
        return super.checkout(borrowerName, DEFAULT_CHECKOUT_DAYS);
    }

    // Implement abstract method
    @Override
    public void displayDetails() {
        System.out.println("\n==========  DVD Details ==========");
        displayBasicInfo();
        System.out.println("Duration: " + duration + " minutes (" + formatDuration() + ")");
        System.out.println("Rating: " + rating);
        System.out.println("Standard Checkout: " + DEFAULT_CHECKOUT_DAYS + " days");
        System.out.println("===================================\n");
    }

    // Helper method that is used to format duration
    private String formatDuration() {
        int hours = duration / 60;
        int minutes = duration % 60;
        return hours + "h " + minutes + "m";
    }

    // Override late fee calculation for DVDs (higher rate)
    @Override
    protected void calculateLateFee(long daysOverdue) {
        double fee = daysOverdue * 20.0; // KES 20 per day for DVDs
        System.out.println(" Late fee (DVD rate): KES " + fee);
    }

    // Getters
    public int getDuration() {
        return duration;
    }

    public String getRating() {
        return rating;
    }

    // Setters
    public void setRating(String rating) {
        this.rating = rating;
    }
}
