package com.evecandy.exercises.javase019;

import java.util.Date;

/**
 * Practice Exercise 1: Custom Functional Interfaces
 * 
 * This exercise demonstrates:
 * - Calculator interface with math operations
 * - Validator interface for input validation
 * - Transformer interface for data transformation
 * - Logger interface for logging messages
 * - Testing with lambda expressions
 */
public class PracticeExercise1 {

    public static void main(String[] args) {
        System.out.println("=== PRACTICE EXERCISE 1: CUSTOM FUNCTIONAL INTERFACES ===\n");

        // ===== CALCULATOR EXAMPLES =====
        System.out.println("--- Calculator Operations ---");

        // Lambda for addition
        Calculator add = (a, b) -> a + b;
        System.out.println("5000 + 500 = " + add.compute(5000, 500));

        // Lambda for pension contribution calculation (10%)
        Calculator pensionContribution = (salary, rate) -> salary * rate;
        System.out.println("Pension (10% of 5000) = " +
                pensionContribution.compute(5000, 0.10));

        // Lambda for compound interest
        Calculator compoundInterest = (principal, rate) -> principal * Math.pow(1 + rate, 5); // 5 years
        System.out.println("Investment after 5 years (5% rate) = " +
                compoundInterest.compute(10000, 0.05));

        // Lambda for retirement fund growth
        Calculator monthlyGrowth = (balance, monthlyContribution) -> balance + monthlyContribution;
        System.out.println("Balance after monthly contribution = " +
                monthlyGrowth.compute(50000, 1000));

        // ===== VALIDATOR EXAMPLES =====
        System.out.println("\n--- Validator Operations ---");

        // Lambda for positive number validation
        Validator isPositive = salary -> salary > 0;
        System.out.println("Is 5000 valid salary? " + isPositive.isValid(5000));
        System.out.println("Is -500 valid salary? " + isPositive.isValid(-500));

        // Lambda for minimum contribution validation
        Validator meetsMinimum = contribution -> contribution >= 100;
        System.out.println("Does 500 meet minimum? " + meetsMinimum.isValid(500));
        System.out.println("Does 50 meet minimum? " + meetsMinimum.isValid(50));

        // Lambda for retirement age validation
        Validator isRetirementAge = age -> age >= 55 && age <= 70;
        System.out.println("Is 60 valid retirement age? " + isRetirementAge.isValid(60));
        System.out.println("Is 45 valid retirement age? " + isRetirementAge.isValid(45));

        // Lambda for contribution percentage validation
        Validator validPercentage = rate -> rate >= 0.05 && rate <= 0.30;
        System.out.println("Is 15% valid contribution rate? " +
                validPercentage.isValid(0.15));
        System.out.println("Is 50% valid contribution rate? " +
                validPercentage.isValid(0.50));

        // ===== TRANSFORMER EXAMPLES =====
        System.out.println("\n--- Transformer Operations ---");

        // Lambda for currency formatting
        Transformer currencyFormatter = amount -> String.format("KES %.2f", amount);
        System.out.println("Formatted: " + currencyFormatter.convert(5000.5));

        // Lambda for percentage formatting
        Transformer percentageFormatter = rate -> String.format("%.1f%%", rate * 100);
        System.out.println("Rate as percentage: " +
                percentageFormatter.convert(0.15));

        // Lambda for account summary
        Transformer accountSummary = balance -> String.format("Account Balance: KES %.2f (Status: %s)",
                balance,
                balance > 100000 ? "Premium" : "Standard");
        System.out.println(accountSummary.convert(150000));
        System.out.println(accountSummary.convert(50000));

        // Lambda for contribution tier
        Transformer contributionTier = amount -> {
            if (amount < 500)
                return "Tier 1: Basic";
            if (amount < 2000)
                return "Tier 2: Standard";
            return "Tier 3: Premium";
        };
        System.out.println("Contribution 300: " + contributionTier.convert(300));
        System.out.println("Contribution 1500: " + contributionTier.convert(1500));
        System.out.println("Contribution 3000: " + contributionTier.convert(3000));

        // ===== LOGGER EXAMPLES =====
        System.out.println("\n--- Logger Operations ---");

        // Lambda for simple console logging
        Logger consoleLogger = message -> System.out.println("[LOG] " + message);
        consoleLogger.print("Pension calculation started");

        // Lambda for error logging
        Logger errorLogger = message -> System.out.println("[ERROR] " + message);
        errorLogger.print("Invalid contribution amount detected");

        // Lambda for info logging with timestamp
        Logger timestampLogger = message -> System.out.println("[" + new Date() + "] INFO: " + message);
        timestampLogger.print("Monthly contribution processed successfully");

        // Lambda for formatted logging
        Logger detailedLogger = message -> {
            System.out.println("=".repeat(50));
            System.out.println("PENSION SYSTEM LOG");
            System.out.println(message);
            System.out.println("=".repeat(50));
        };
        detailedLogger.print("Annual statement generated for member #12345");

        // ===== COMBINED EXAMPLE: PENSION PROCESSING PIPELINE =====
        System.out.println("\n--- Complete Pension Processing Example ---");

        double salary = 8000.0;
        double contributionRate = 0.12;

        // Step 1: Validate salary
        Validator salaryValidator = s -> s > 0 && s < 1000000;

        if (salaryValidator.isValid(salary)) {
            consoleLogger.print("Salary validation passed");

            // Step 2: Calculate contribution
            Calculator contribution = (s, rate) -> s * rate;
            double amount = contribution.compute(salary, contributionRate);

            // Step 3: Validate contribution
            Validator contributionValidator = c -> c >= 100;

            if (contributionValidator.isValid(amount)) {
                consoleLogger.print("Contribution validation passed");

                // Step 4: Transform to display format
                Transformer display = a -> String.format("Monthly Contribution: %s (%s of salary)",
                        currencyFormatter.convert(a),
                        percentageFormatter.convert(contributionRate));

                // Step 5: Log final result
                detailedLogger.print(display.convert(amount));
            } else {
                errorLogger.print("Contribution below minimum threshold");
            }
        } else {
            errorLogger.print("Invalid salary amount");
        }

        // ===== ADDITIONAL REAL-WORLD SCENARIOS =====
        System.out.println("\n--- Real-World Scenarios ---");

        // Scenario 1: Retirement Readiness Calculator
        Calculator retirementReadiness = (currentAge, retirementAge) -> retirementAge - currentAge;
        double yearsToRetirement = retirementReadiness.compute(35, 60);
        consoleLogger.print("Years until retirement: " + yearsToRetirement);

        // Scenario 2: Validate multiple conditions
        Validator comprehensiveValidator = value -> {
            boolean positive = value > 0;
            boolean reasonable = value <= 1000000;
            boolean notZero = value != 0;
            return positive && reasonable && notZero;
        };
        consoleLogger.print("Complex validation result: " +
                comprehensiveValidator.isValid(75000));

        // Scenario 3: Multi-step transformation
        Transformer riskProfile = balance -> {
            String risk;
            if (balance < 50000)
                risk = "Aggressive";
            else if (balance < 200000)
                risk = "Moderate";
            else
                risk = "Conservative";

            return String.format("Balance: %s | Recommended Profile: %s",
                    currencyFormatter.convert(balance), risk);
        };
        consoleLogger.print(riskProfile.convert(75000));
        consoleLogger.print(riskProfile.convert(250000));

        System.out.println("\n=== EXERCISE 1 COMPLETE ===");
    }
}
