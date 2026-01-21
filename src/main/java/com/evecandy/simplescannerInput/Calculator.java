package com.evecandy.simplescannerInput;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter your second number: ");
        double num2 = scanner.nextDouble();

        double sum = num1 + num2;
        double subtraction = num1 - num2;
        double product = num1 * num2;
        double division = num1 / num2;
        double modulus = num1 % num2;

        System.out.println("\nResults:");
        System.out.println(num1 + " + " + num2 + " = " + sum);
        System.out.println(num1 + " - " + num2 + " = " + subtraction);
        System.out.println(num1 + " * " + num2 + " = " + product);
        System.out.printf("%.0f / %.0f = %.3f\n", num1, num2, division);
        System.out.println(num1 + " % " + num2 + " = " + modulus);

        double squareNum1 = num1 * num1;
        double squareNum2 = num2 * num2;

        System.out.println("\nSquares:");
        System.out.println(num1 + " squared = " + squareNum1);
        System.out.println(num2 + " squared = " + squareNum2);

        double sqrtNum1 = Math.sqrt(num1);
        double sqrtNum2 = Math.sqrt(num2);
        System.out.println("\nSquare Roots:");
        System.out.println("sqrt(" + num1 + ") = " + sqrtNum1);
        System.out.println("sqrt(" + num2 + ") = " + sqrtNum2);

        scanner.close();
    }

}
