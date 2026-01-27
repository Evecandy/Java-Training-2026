package com.evecandy.exercises.javase011polymorphism;

public class EmailNotification extends Notification {
    private String subject;
    private String senderEmail;

    // Constructor
    public EmailNotification(String recipientEmail, String message, String subject) {
        super(recipientEmail, message);
        this.subject = subject;
        this.senderEmail = "evecandy@org.com";
    }

    // Implement abstract method
    @Override
    public boolean validate() {
        // Check if email format is valid
        return recipient.contains("@") && recipient.contains(".");
    }

    // Implement abstract method
    @Override
    public void send() {
        if (!validate()) {
            System.out.println(" Invalid email address: " + recipient);
            return;
        }

        System.out.println("\n Sending Email...");
        System.out.println("From: " + senderEmail);
        System.out.println("To: " + recipient);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
        System.out.println("Email sent successfully!\n");
    }

    // Additional method specific to email
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }
}
