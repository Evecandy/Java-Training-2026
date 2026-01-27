package com.evecandy.exercises.javase010polymorphism;

public class NotificationManager {
    public static void main(String[] args) {
        System.out.println("========== Notification System ==========\n");

        // Test 1: Email Notification
        System.out.println("--- Test 1: Email Notification ---");
        EmailNotification email = new EmailNotification(
                "cathy@example.com",
                "Your account has been created successfully!",
                "Welcome to Our Service");
        email.displayInfo();
        email.send();

        // Test 2: SMS Notification
        System.out.println("--- Test 2: SMS Notification ---");
        SMSNotification sms = new SMSNotification(
                "+254712345678",
                "Your verification code is: 123456");
        sms.displayInfo();
        sms.send();

        // Test 3: Invalid Email
        System.out.println("--- Test 3: Invalid Email ---");
        EmailNotification invalidEmail = new EmailNotification(
                "not-an-email",
                "This should fail",
                "Test");
        invalidEmail.send();

        // Test 4: Invalid Phone Number
        System.out.println("--- Test 4: Invalid Phone Number ---");
        SMSNotification invalidSMS = new SMSNotification(
                "123",
                "This should fail");
        invalidSMS.send();

        // Test 5: Polymorphism - Array of Notifications
        System.out.println("--- Test 5: Polymorphic Notification Processing ---");
        Notification[] notifications = new Notification[3];
        notifications[0] = new EmailNotification("Mark@test.com", "Hi Mark.", "Greeting");
        notifications[1] = new SMSNotification("+254700000000", "Hi via SMS.");
        notifications[2] = new EmailNotification("charbel@test.com", "Update available", "System Update");

        System.out.println("Sending all notifications:");
        for (Notification notification : notifications) {
            notification.send(); // Polymorphic call
        }

        // Test 6: Long SMS Message
        System.out.println("--- Test 6: Long SMS Message ---");
        String longMessage = "This is a very long message that exceeds the standard 160 character limit for SMS messages. "
                +
                "It will need to be split into multiple parts when sent.Will it really have to be split to different parts when sent? I'll have to confirm that, give me a minute. Yeap! It does exceed the limit.";
        SMSNotification longSMS = new SMSNotification("+254712345678", longMessage);
        longSMS.send();
    }
}
