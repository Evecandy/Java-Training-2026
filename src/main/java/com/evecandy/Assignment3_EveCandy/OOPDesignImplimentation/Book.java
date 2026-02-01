package com.evecandy.Assignment3_EveCandy.OOPDesignImplimentation;

public class Book extends LibraryItem {
    private String isbn;
    private String genre;

    // Standard checkout period for books
    private static final int DEFAULT_CHECKOUT_DAYS = 14;

    // Constructor
    public Book(String itemId, String title, String author, String isbn, String genre) {
        super(itemId, title, author);
        this.isbn = isbn;
        this.genre = genre;
    }

    // Implement abstract method
    @Override
    public String getItemType() {
        return "Book";
    }

    // Override checkout to use default days for books
    public boolean checkout(String borrowerName) {
        return super.checkout(borrowerName, DEFAULT_CHECKOUT_DAYS);
    }

    // Implement abstract method
    @Override
    public void displayDetails() {
        System.out.println("\n==========  Book Details ==========");
        displayBasicInfo();
        System.out.println("ISBN: " + isbn);
        System.out.println("Genre: " + genre);
        System.out.println("Standard Checkout: " + DEFAULT_CHECKOUT_DAYS + " days");
        System.out.println("====================================\n");
    }

    // Override late fee calculation for books
    @Override
    protected void calculateLateFee(long daysOverdue) {
        double fee = daysOverdue * 5.0; // KES 5 per day for books
        System.out.println(" Late fee (Book rate): KES " + fee);
    }

    // Getters
    public String getIsbn() {
        return isbn;
    }

    public String getGenre() {
        return genre;
    }

    // Setters
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
