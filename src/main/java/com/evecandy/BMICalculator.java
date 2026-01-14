package com.evecandy;

import java.util.Scanner;

public class BMICalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your mass in kg: ");
        double mass = scanner.nextDouble();
        System.out.print("Enter your height in centimeters: ");
        double height = scanner.nextDouble();

        double bmi = mass / (height * height);

        System.out.println("\n=== BMI Calculation Result ===");
        System.out.printf("Mass (kg): %.2f\n", mass);
        System.out.printf("Height (cm): %.2f\n", height);
        System.out.printf("Your BMI is: %.2f\n", bmi);

        System.out.print("Category: ");
        if (bmi < 18.5) {
            System.out.println("Underweight");
        } else if (bmi >= 18.5 && bmi < 24.9) {
            System.out.println("Normal weight");
        } else if (bmi >= 25 && bmi < 29.9) {
            System.out.println("You are Overweight");
        } else {
            System.out.println("You are Obese");
        }
        scanner.close();
    }
}