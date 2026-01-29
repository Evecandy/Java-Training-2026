package com.evecandy.exercises.javase013.exceptionhandling.calculator;

import java.util.Scanner;

public class ExceptionCalculator {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m"; // Nice for warnings

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        System.out.println("=== Robust Calculator 3000 ===");
        System.out.println("Operations: +  -  * /");
        System.out.println("Type 'exit' to quit.\n");

        while (keepRunning) {
            try {

                System.out.print("Enter first number: ");
                String input1 = scanner.next();

                if (input1.equalsIgnoreCase("exit")) {
                    keepRunning = false;
                    continue;
                }

                double num1 = Double.parseDouble(input1);

                System.out.print("Enter operator (+, -, *, /): ");
                String operator = scanner.next();
                validateOperator(operator);

                System.out.print("Enter second number: ");
                String input2 = scanner.next();
                double num2 = Double.parseDouble(input2);

                double result = performOperation(num1, num2, operator);

                System.out.println(ANSI_GREEN + "Result: " + result + ANSI_RESET);
                System.out.println("----------------------------");

            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED + "Error: That's not a valid number. Please enter digits.");
                System.out.println("  (Technical details: " + e.getMessage() + ")" + ANSI_RESET);

            } catch (ArithmeticException e) {
                System.out.println(ANSI_RED + "Math Error: " + e.getMessage() + ANSI_RESET);

            } catch (CalculationException e) {
                System.out.println(ANSI_YELLOW + "Business Rule Violation: " + e.getMessage() + ANSI_RESET);

            } catch (Exception e) {
                System.out.println("Unexpected Error: Something went wrong.");
                e.printStackTrace();
            }
        }

        System.out.println("Calculator closed. Goodbye!");
        scanner.close();
    }

    public static double performOperation(double n1, double n2, String op)
            throws ArithmeticException, CalculationException {

        double result = 0;

        switch (op) {
            case "+":
                result = n1 + n2;
                break;
            case "-":
                result = n1 - n2;
                break;
            case "*":
                result = n1 * n2;
                break;
            case "/":
                if (n2 == 0) {
                    // Manually throwing an ArithmeticException for double division
                    // (Java doubles usually return 'Infinity', so we force an error here)
                    throw new ArithmeticException(ANSI_RED + "Cannot divide by zero." + ANSI_RESET);
                }
                result = n1 / n2;
                break;
        }

        if (Double.isInfinite(result) || Double.isNaN(result)) {
            throw new CalculationException(
                    ANSI_RED + "Overflow. Result is too large for this calculator." + ANSI_RESET);
        }

        if (result < 0) {
            throw new CalculationException(
                    ANSI_YELLOW + "Result cannot be negative (Business Policy 101)." + ANSI_RESET);
        }

        return result;
    }

    public static void validateOperator(String op) throws CalculationException {
        if (!op.matches("[+\\-*/]")) {
            throw new CalculationException(
                    ANSI_RED + "Invalid operator: '" + op + "'. Only +, -, *, / are allowed." + ANSI_RESET);
        }
    }

}
