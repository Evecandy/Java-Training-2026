package com.evecandy.exercises.javase008;

import java.time.LocalDate;

public class Book {
    // Private fields
    private final String isbn;
    private String title;
    private String author;
    private boolean isAvailable;
    private LocalDate dueDate;

    // Constructor with validation (prints warnings instead of throwing exceptions)
    public Book(String isbn, String title, String author) {
        // Store values (validation done before calling constructor)
        this.isbn = isbn;
        this.title = title.trim();
        this.author = author.trim();
        this.isAvailable = true;
        this.dueDate = null;
    }

    // Method: Borrow a book
    public boolean borrowBook(int daysToReturn) {
        if (!isAvailable) {
            System.out.println(" This book is already borrowed");
            return false;
        }

        if (daysToReturn <= 0) {
            System.out.println(" Invalid number of days");
            return false;
        }

        isAvailable = false;
        dueDate = LocalDate.now().plusDays(daysToReturn);

        System.out.println(" Book borrowed successfully");
        System.out.println(" Due dateis: " + dueDate);
        return true;
    }

    // Method: Return a book
    public boolean returnBook() {
        if (isAvailable) {
            System.out.println(" Book is already available (not borrowed)");
            return false;
        }

        // Check if overdue before returning
        if (isOverdue()) {
            long daysOverdue = java.time.temporal.ChronoUnit.DAYS.between(
                    dueDate, LocalDate.now());
            System.out.println(" Book was overdue by " + daysOverdue + " day(s)");
        }

        isAvailable = true;
        dueDate = null;

        System.out.println(" Book returned successfully");
        return true;
    }

    // Method: Check if book is overdue
    public boolean isOverdue() {
        if (isAvailable) {
            return false;
        }

        if (dueDate == null) {
            return false;
        }

        return LocalDate.now().isAfter(dueDate);
    }

    // Method: Display book information
    public void displayInfo() {
        System.out.println("\n========== Book Information ==========");
        System.out.println("ISBN: " + isbn);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Status: " + (isAvailable ? "Available " : "Borrowed "));

        if (!isAvailable && dueDate != null) {
            System.out.println("Due Date: " + dueDate);
            if (isOverdue()) {
                System.out.println(" OVERDUE.");
            }
        }

        System.out.println("======================================\n");
    }

    // Getters
    public String getIsbn() {
        return isbn;
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

    // Setters (with validation)
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println(" Title cannot be empty.");
            return;
        }
        this.title = title.trim();
        System.out.println(" Title updated successfully.");
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            System.out.println(" Author cannot be empty.");
            return;
        }
        this.author = author.trim();
        System.out.println(" Author updated successfully.");
    }
}