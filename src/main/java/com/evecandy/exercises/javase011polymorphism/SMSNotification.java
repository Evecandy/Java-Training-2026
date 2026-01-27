package com.evecandy.exercises.javase010polymorphism;

public class SMSNotification extends Notification {
    private String phoneNumber;
    private int characterCount;

    // Constructor
    public SMSNotification(String phoneNumber, String message) {
        super(phoneNumber, message);
        this.phoneNumber = phoneNumber;
        this.characterCount = message.length();
    }

    // abstract method
    @Override
    public boolean validate() {
        // Checking if phone number is valid
        String cleaned = phoneNumber.replaceAll("[^0-9]", "");
        return cleaned.length() >= 10 && cleaned.length() <= 15;
    }

    // Implementing abstract method
    @Override
    public void send() {
        if (!validate()) {
            System.out.println(" Invalid phone number: " + phoneNumber);
            return;
        }

        if (characterCount > 160) {
            System.out.println("  Message exceeds 160 characters. Will be sent as multiple SMS.");
        }

        System.out.println("\n Sending SMS...");
        System.out.println("To: " + phoneNumber);
        System.out.println("Message: " + message);
        System.out.println("Character count: " + characterCount + "/160");
        System.out.println(" SMS sent successfully!\n");
    }

    // Additional method specific to SMS
    public int getCharacterCount() {
        return characterCount;
    }
}
