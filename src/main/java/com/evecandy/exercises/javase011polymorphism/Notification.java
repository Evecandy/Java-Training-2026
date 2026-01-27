package com.evecandy.exercises.javase010polymorphism;

// Abstract base class
public abstract class Notification {
    protected String recipient;
    protected String message;
    protected String timestamp;

    // Constructor
    public Notification(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    // Abstract methods - must be implemented by children
    public abstract void send();

    public abstract boolean validate();

    // Concrete method - shared by all notifications
    public void displayInfo() {
        System.out.println("\n========== Notification Info ==========");
        System.out.println("Recipient: " + recipient);
        System.out.println("Message: " + message);
        System.out.println("Timestamp: " + timestamp);
        System.out.println("==================================\n");
    }

    // Getters
    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}