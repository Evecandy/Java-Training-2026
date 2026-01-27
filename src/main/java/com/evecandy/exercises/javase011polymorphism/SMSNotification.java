package com.evecandy.exercises.javase011polymorphism;

import java.util.ArrayList;
import java.util.List;

public class SMSNotification extends Notification {
    private String phoneNumber;
    private int characterCount;
    private static final int MAX_SMS_LENGTH = 160;

    // Constructor
    public SMSNotification(String phoneNumber, String message) {
        super(phoneNumber, message);
        this.phoneNumber = phoneNumber;
        this.characterCount = message.length();
    }

    // Implementing abstract method
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

        // Checking if message needs splitting
        if (characterCount > MAX_SMS_LENGTH) {
            sendLongMessage();
        } else {
            sendSingleMessage();
        }
    }

    // Send single SMS
    private void sendSingleMessage() {
        System.out.println("\n Sending SMS...");
        System.out.println("To: " + phoneNumber);
        System.out.println("Message: " + message);
        System.out.println("Character count: " + characterCount + "/" + MAX_SMS_LENGTH);
        System.out.println(" SMS sent successfully!\n");
    }

    // Send long message and split it into multiple parts
    private void sendLongMessage() {

        List<String> messageParts = splitMessage(message);

        System.out.println("\n Sending Long SMS...");
        System.out.println("To: " + phoneNumber);
        System.out.println("Total length: " + characterCount + " characters");
        System.out.println("  Message exceeds " + MAX_SMS_LENGTH + " characters.");
        System.out.println(" Splitting into " + messageParts.size() + " parts...\n");

        // Send each part
        for (int i = 0; i < messageParts.size(); i++) {
            System.out.println("--- Part " + (i + 1) + "/" + messageParts.size() + " ---");
            System.out.println("Message: " + messageParts.get(i));
            System.out.println("Length: " + messageParts.get(i).length() + "/" + MAX_SMS_LENGTH);
            System.out.println(" Part " + (i + 1) + " sent!");
            System.out.println();
        }

        System.out.println(" All " + messageParts.size() + " parts sent successfully!\n");
    }

    // Split message into chunks of MAX_SMS_LENGTH
    private List<String> splitMessage(String msg) {
        List<String> parts = new ArrayList<>();

        int length = msg.length();
        int index = 0;

        while (index < length) {

            int endIndex = Math.min(index + MAX_SMS_LENGTH, length);

            // Try to split at a space to avoid cutting words
            if (endIndex < length && msg.charAt(endIndex) != ' ') {
                // Look backwards for the last space
                int lastSpace = msg.lastIndexOf(' ', endIndex);
                if (lastSpace > index) {
                    endIndex = lastSpace;
                }
            }

            // Extract the part and add to list
            String part = msg.substring(index, endIndex).trim();
            parts.add(part);

            // Move to the next chunk
            index = endIndex;

            // Skip any spaces at the start of the next chunk
            while (index < length && msg.charAt(index) == ' ') {
                index++;
            }
        }

        return parts;
    }

    // Additional method specific to SMS
    public int getCharacterCount() {
        return characterCount;
    }

    public int getNumberOfParts() {
        return (int) Math.ceil((double) characterCount / MAX_SMS_LENGTH);
    }
}
