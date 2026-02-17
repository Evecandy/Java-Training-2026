package com.evecandy.exercises.javase019;

/**
 * Domain Model: Pension Member
 * Represents a member in the pension system
 */
public class PensionMember {
    private String memberId;
    private String name;
    private int age;
    private double salary;
    private double contributionRate;
    private double currentBalance;
    private String membershipTier; // BASIC, STANDARD, PREMIUM
    private boolean isActive;

    public PensionMember(String memberId, String name, int age, double salary,
            double contributionRate, double currentBalance,
            String membershipTier, boolean isActive) {
        this.memberId = memberId;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.contributionRate = contributionRate;
        this.currentBalance = currentBalance;
        this.membershipTier = membershipTier;
        this.isActive = isActive;
    }

    // Getters
    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public double getContributionRate() {
        return contributionRate;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public String getMembershipTier() {
        return membershipTier;
    }

    public boolean isActive() {
        return isActive;
    }

    // Setters
    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public void setMembershipTier(String membershipTier) {
        this.membershipTier = membershipTier;
    }

    // Calculated fields
    public double getMonthlyContribution() {
        return salary * contributionRate;
    }

    public int getYearsToRetirement() {
        return Math.max(0, 60 - age); // Assuming retirement age is 60
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - Age: %d, Salary: KES %.2f, Balance: KES %.2f, Tier: %s",
                name, memberId, age, salary, currentBalance, membershipTier);
    }
}
