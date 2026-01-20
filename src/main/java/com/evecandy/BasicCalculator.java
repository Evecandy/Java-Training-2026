package com.evecandy;

import java.util.Scanner;

public class BasicCalculator {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter your second number: ");
        double num2 = scanner.nextDouble();

        System.out.println("\nResults:");
        System.out.println(num1 + "+" + num2 + "=" + sum(num1, num2));
        System.out.println(num1 + "-" + num2 + "=" + subtraction(num1, num2));
        System.out.println(num1 + "+" + num2 + "=" + product(num1, num2));
        System.out.println(num1 + "/" + num2 + "=" + division(num1, num2));
        System.out.println(num1 + "%" + num2 + "=" + modulus(num1, num2));

        scanner.close();
    }

    public static double sum(double num1, double num2) {
        return num1 + num2;
    }

    public static double subtraction(double num1, double num2) {
        return num1 - num2;
    }

    public static double product(double num1, double num2) {
        return num1 * num2;
    }

    public static double division(double num1, double num2) {
        return num1 / num2;
    }

    public static double modulus(double num1, double num2) {
        return num1 % num2;
    }

}
