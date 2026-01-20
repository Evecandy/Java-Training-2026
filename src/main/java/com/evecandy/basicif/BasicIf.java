package com.evecandy.basicif;

public class BasicIf {
    public static void main(String[] args) {
        int score = 85;
        int passingGrade = 40;
        // Check if student passed
        if (score >= passingGrade) {
            System.out.println("Congratulations! You passed!");
            System.out.println("Your score: " + score);
        }
        // This always executes
        System.out.println("Exam completed.");
    }
}
