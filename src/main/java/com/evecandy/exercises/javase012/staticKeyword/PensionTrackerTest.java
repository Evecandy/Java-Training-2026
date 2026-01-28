package com.evecandy.exercises.javase012.staticKeyword;

import java.math.BigDecimal;

public class PensionTrackerTest {

    public static void main(String[] args) {
        System.out.println("========== Systech Pension Tracking System ==========\n");

        // Display system info (using static method)
        PensionTracker.displaySystemInfo();

        // Test 1: Create pension account for an employee
        System.out.println("--- Test 1: Creating Pension Account ---");
        PensionTracker jamesPension = new PensionTracker(
                "EMP001",
                "James Turing",
                150000.00 // Monthly salary: KES 150,000
        );

        // Test 2: Set interest rates for different years
        System.out.println("\n--- Test 2: Setting Interest Rates ---");
        jamesPension.addInterestRate(2026, 0.08); // 8% for 2026
        jamesPension.addInterestRate(2027, 0.085); // 8.5% for 2027
        jamesPension.addInterestRate(2028, 0.09);
        jamesPension.addInterestRate(2029, 0.087);
        jamesPension.addInterestRate(2030, 0.092);

        // Test 3: Calculate pension growth over 5 years
        System.out.println("\n--- Test 3: Calculating Pension Growth (2026-2030) ---");
        BigDecimal finalBalance = jamesPension.calculateTotal(2026, 2030);

        System.out.println("\n Final Pension Balance after 5 years: KES " + finalBalance);

        // Test 4: Display account summary
        System.out.println("\n--- Test 4: Account Summary ---");
        jamesPension.displayAccountSummary();

        // Test 5: Display contribution history (first 10 and last entry)
        System.out.println("\n--- Test 5: Contribution History ---");
        jamesPension.displayContributionHistory();

        // Test 6: creating multiple pension accounts
        System.out.println("\n--- Test 6: Multiple Pension Accounts ---");

        PensionTracker bobPension = new PensionTracker(
                "EMP002",
                "Bob Sterling",
                200000.00);
        bobPension.addInterestRate(2026, 0.08);
        bobPension.addInterestRate(2027, 0.085);

        PensionTracker charliePension = new PensionTracker(
                "EMP003",
                "Charlie Wise",
                180000.00);
        charliePension.addInterestRate(2026, 0.08);
        charliePension.addInterestRate(2027, 0.085);

        // Calculate for different periods
        System.out.println("\n Calculating Bob's pension (2 years):");
        BigDecimal bobBalance = bobPension.calculateTotal(2026, 2027);
        System.out.println("Bob's Balance: KES " + bobBalance);

        System.out.println("\n Calculating Charlie's pension (2 years):");
        BigDecimal charlieBalance = charliePension.calculateTotal(2026, 2027);
        System.out.println("Charlie's Balance: KES " + charlieBalance);

        // Test 7: Using static method to show total accounts
        System.out.println("\n--- Test 7: System Statistics ---");
        System.out.println("Total pension accounts created: " + PensionTracker.getTotalAccountsCreated());

        // Test 8: Using Static Nested Class for Interest Rate Info
        System.out.println("\n--- Test 8: Interest Rate Information (Static Nested Class) ---");
        PensionTracker.InterestRateInfo[] historicalRates = {
                new PensionTracker.InterestRateInfo(2026, 0.08, "Central Bank of Kenya"),
                new PensionTracker.InterestRateInfo(2027, 0.085, "Central Bank of Kenya"),
                new PensionTracker.InterestRateInfo(2028, 0.09, "Central Bank of Kenya"),
                new PensionTracker.InterestRateInfo(2029, 0.087, "Central Bank of Kenya"),
                new PensionTracker.InterestRateInfo(2030, 0.092, "Central Bank of Kenya")
        };

        System.out.println("\nHistorical Interest Rates:");
        for (PensionTracker.InterestRateInfo rate : historicalRates) {
            rate.display();
        }

        // Test 9: Compare pension accounts
        System.out.println("\n--- Test 9: Pension Account Comparison ---");
        System.out.println("\n Account Comparison:");
        System.out.printf("%-20s %-15s %-20s %-20s%n",
                "Employee", "Salary", "Contribution", "Balance");
        System.out.println("─".repeat(80));

        PensionTracker[] accounts = { jamesPension, bobPension, charliePension };
        for (PensionTracker account : accounts) {
            BigDecimal monthlyContribution = account.getMonthlySalary()
                    .multiply(new BigDecimal("0.10"));
            System.out.printf("%-20s KES %-11s KES %-16s KES %-16s%n",
                    account.getEmployeeName(),
                    account.getMonthlySalary(),
                    monthlyContribution,
                    account.getCurrentBalance());
        }

        // Test 10: Invalid scenarios
        System.out.println("\n--- Test 10: Error Handling ---");

        System.out.println("\nTrying invalid interest rate:");
        jamesPension.addInterestRate(2031, 1.5); // Invalid - greater than 100%

        System.out.println("\nTrying invalid year range:");
        jamesPension.calculateTotal(2030, 2026); // Invalid - end before start

        // Final Summary
        System.out.println("\n========== Final System Summary ==========");
        PensionTracker.displaySystemInfo();

        System.out.println("All Employee Summaries:");
        for (PensionTracker account : accounts) {
            account.displayAccountSummary();
        }
    }

}
