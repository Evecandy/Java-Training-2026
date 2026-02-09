
//Pension contributions every month, 5% from salary, total contribution will be 10%. 
// Need a class that can track the interest rates annually.

package com.evecandy.exercises.javase012.staticKeyword;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class PensionTracker {

    private static final BigDecimal EMPLOYEE_CONTRIBUTION_RATE = new BigDecimal("0.05");
    private static final BigDecimal EMPLOYER_CONTRIBUTION_RATE = new BigDecimal("0.05");

    private static final BigDecimal TOTAL_CONTRIBUTION_RATE = new BigDecimal("0.10");
    private static final int MONTHS_IN_YEAR = 12;
    private static final int DECIMAL_SCALE = 2;
    private static final int CALCULATION_SCALE = 10;

    // tracks total number of pension accounts created
    private static int totalAccountsCreated = 0;

    // create instance variables
    private final String employeeName; // can't change after creation
    private final String employeeId;
    private BigDecimal monthlySalary; // Can change (promotions)
    private final TreeMap<Integer, Double> annualInterestRates;
    private final List<MonthlyContribution> contributionHistory;
    private BigDecimal currentBalance;
    private final int accountNumber;
    private int numberOfAccounts;

    static {
        System.out.println("=== Pension Manager ===");
        System.out.println(
                "Employee Contribution Rate: " + EMPLOYEE_CONTRIBUTION_RATE.multiply(new BigDecimal("100")) + "%");
        System.out.println(
                "Employer Contribution Rate: " + EMPLOYER_CONTRIBUTION_RATE.multiply(new BigDecimal("100")) + "%");
        System.out.println("Total Contribution Rate: " + TOTAL_CONTRIBUTION_RATE.multiply(new BigDecimal("100")) + "%");
        System.out.println("========================\n");
    }

    // constructor
    public PensionTracker(String employeeName, String employeeId, BigDecimal monthlySalary, int accountNumber) {
        this.employeeName = employeeName;
        this.employeeId = employeeId;
        this.monthlySalary = monthlySalary.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP); // Round to 2 decimal
        this.annualInterestRates = new TreeMap<>(); // Empty at start
        this.contributionHistory = new ArrayList<>(); // Empty list
        this.currentBalance = BigDecimal.ZERO;
        this.accountNumber = // Starts at 0
                this.numberOfAccounts = ++totalAccountsCreated; // Auto-increments: 1,2,3 ...

        System.out.println(" Pension account created for: " + employeeName + " (Account :" + accountNumber + ")");
    }

    public PensionTracker(String employeeName, String employeeId, BigDecimal monthlySalary) {
        this(employeeName, employeeId, monthlySalary, 0);
    }

    public static int getTotalAccountsCreated() {
        return totalAccountsCreated;
    }

    public static void displaySystemInfo() {
        System.out.println("\n==========  Pension System Info ==========");
        System.out.println("Total Pension Accounts: " + totalAccountsCreated);
        System.out
                .println("Employee Contribution: " + EMPLOYEE_CONTRIBUTION_RATE.multiply(new BigDecimal("100")) + "%");
        System.out
                .println("Employer Contribution: " + EMPLOYER_CONTRIBUTION_RATE.multiply(new BigDecimal("100")) + "%");
        System.out.println(
                "Total Monthly Contribution: " + TOTAL_CONTRIBUTION_RATE.multiply(new BigDecimal("100")) + "%");
        System.out.println("==========================\n");
    }

    // method to add interest rate for a specific year
    public void addInterestRate(int year, double annualRate) {
        if (annualRate < 0 || annualRate > 1) {
            System.out.println(" Invalid interest rate! Must be between 0 and 1.");
            return;
        }
        annualInterestRates.put(year, annualRate);
        System.out.println("Interest rate for " + year + " set to: " + (annualRate * 100) + "%");
    }

    public BigDecimal calculateTotal(int startYear, int endYear) {
        if (startYear > endYear) {
            System.out.println(" Start year must be before or equal to end year");
            return BigDecimal.ZERO;
        }
        BigDecimal balance = currentBalance;
        BigDecimal monthlyContribution = monthlySalary.multiply(TOTAL_CONTRIBUTION_RATE);

        System.out.println("\nCalculating pension growth from " + startYear + " to " + endYear + "...");
        System.out.println(
                "Monthly Contribution: KES " + monthlyContribution.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP));
        System.out.println("Starting Balance: KES " + balance.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP));
        System.out.println();

        for (int year = startYear; year <= endYear; year++) {
            // Get interest rate for current year (default to 0 if not set)
            double yearlyRate = annualInterestRates.getOrDefault(year, 0.0);
            BigDecimal monthlyRate = BigDecimal.valueOf(yearlyRate)
                    .divide(BigDecimal.valueOf(MONTHS_IN_YEAR),
                            CALCULATION_SCALE, RoundingMode.HALF_UP);

            BigDecimal yearStartBalance = balance;

            // Process each month
            for (int month = 1; month <= MONTHS_IN_YEAR; month++) {
                // 1. Add monthly contribution
                balance = balance.add(monthlyContribution);

                // 2. Calculate and add interest
                BigDecimal interest = balance.multiply(monthlyRate);
                balance = balance.add(interest);

                // 3. Record contribution (using nested class)
                MonthlyContribution contribution = new MonthlyContribution(
                        year, month, monthlyContribution, interest, balance);
                contributionHistory.add(contribution);
            }

            BigDecimal yearEndBalance = balance;
            BigDecimal yearlyGrowth = yearEndBalance.subtract(yearStartBalance);

            System.out.println("Year " + year + " (" + (yearlyRate * 100) + "% annual rate):");
            System.out.println("  Start: KES " + yearStartBalance.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP));
            System.out.println("  End:   KES " + yearEndBalance.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP));
            System.out.println("  Growth: KES " + yearlyGrowth.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP));
            System.out.println();
        }

        currentBalance = balance;
        return balance.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP);
    }

    // Display detailed contribution history
    public void displayContributionHistory() {
        System.out.println("\n========== Contribution History ==========");
        System.out.println("Employee: " + employeeName + " (ID: " + employeeId + ")");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Total Contributions: " + contributionHistory.size());
        System.out.println("==========================================\n");

        if (contributionHistory.isEmpty()) {
            System.out.println("No contributions recorded yet.");
            return;
        }

        System.out.printf("%-10s %-10s %-15s %-15s %-15s%n",
                "Year", "Month", "Contribution", "Interest", "Balance");
        System.out.println("─".repeat(70));

        int displayCount = Math.min(10, contributionHistory.size());
        for (int i = 0; i < displayCount; i++) {
            MonthlyContribution mc = contributionHistory.get(i);
            mc.display();
        }

        if (contributionHistory.size() > 10) {
            System.out.println("... (" + (contributionHistory.size() - 10) + " more entries)");
        }

        // Show last entry if there are many
        if (contributionHistory.size() > 10) {
            System.out.println("\nLast Entry:");
            contributionHistory.get(contributionHistory.size() - 1).display();
        }
    }

    // Display account summary
    public void displayAccountSummary() {
        BigDecimal totalContributions = BigDecimal.valueOf(contributionHistory.size())
                .multiply(monthlySalary)
                .multiply(TOTAL_CONTRIBUTION_RATE);
        BigDecimal totalInterestEarned = currentBalance.subtract(totalContributions);

        System.out.println("\n========== Pension Account Summary ==========");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Employee Name: " + employeeName);
        System.out.println("Monthly Salary: KES " + monthlySalary);
        System.out.println("Monthly Contribution: KES " +
                monthlySalary.multiply(TOTAL_CONTRIBUTION_RATE).setScale(DECIMAL_SCALE, RoundingMode.HALF_UP));
        System.out.println("─".repeat(50));
        System.out.println("Total Months Contributed: " + contributionHistory.size());
        System.out.println(
                "Total Contributions: KES " + totalContributions.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP));
        System.out.println(
                "Total Interest Earned: KES " + totalInterestEarned.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP));
        System.out.println("Current Balance: KES " + currentBalance.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP));
        System.out.println("=============================================\n");
    }

    // Getters
    public String getEmployeeId() {
        return employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public BigDecimal getMonthlySalary() {
        return monthlySalary;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    // Nested Class - Represents a single monthly contribution
    public class MonthlyContribution {
        private final int year;
        private final int month;
        private final BigDecimal contributionAmount;
        private final BigDecimal interestEarned;
        private final BigDecimal balanceAfter;
        private final LocalDate contributionDate;

        // Constructor
        public MonthlyContribution(int year, int month, BigDecimal contribution,
                BigDecimal interest, BigDecimal balance) {
            this.year = year;
            this.month = month;
            this.contributionAmount = contribution;
            this.interestEarned = interest;
            this.balanceAfter = balance;
            this.contributionDate = LocalDate.of(year, month, 1);
        }

        // Display this contribution
        public void display() {
            System.out.printf("%-10d %-10d KES %-11s KES %-11s KES %-11s%n",
                    year, month,
                    contributionAmount.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP),
                    interestEarned.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP),
                    balanceAfter.setScale(DECIMAL_SCALE, RoundingMode.HALF_UP));
        }

        // Getters
        public int getYear() {
            return year;
        }

        public int getMonth() {
            return month;
        }

        public BigDecimal getContributionAmount() {
            return contributionAmount;
        }

        public BigDecimal getInterestEarned() {
            return interestEarned;
        }

        public BigDecimal getBalanceAfter() {
            return balanceAfter;
        }

        public LocalDate getContributionDate() {
            return contributionDate;
        }
    }

    // Static Nested Class - For interest rate information
    public static class InterestRateInfo {
        private final int year;
        private final double rate;
        private final String source;

        public InterestRateInfo(int year, double rate, String source) {
            this.year = year;
            this.rate = rate;
            this.source = source;
        }

        public void display() {
            System.out.printf("%-10d %-10.2f%% %-20s%n", year, rate * 100, source);
        }

        public int getYear() {
            return year;
        }

        public double getRate() {
            return rate;
        }

        public String getSource() {
            return source;
        }
    }

}
