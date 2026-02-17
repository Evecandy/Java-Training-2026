package com.evecandy.exercises.javase019;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

/**
 * Practice Exercise 4: Complete Functional System
 * 
 * This exercise demonstrates:
 * - Data processing pipeline with multiple steps
 * - Custom functional interfaces for domain logic
 * - Method references for common operations
 * - Combination of Predicate, Function, Consumer
 * - Error handling and validation
 * - Best practices
 */
public class PracticeExercise4 {

    // Sample data generator using Supplier
    private static Supplier<List<PensionMember>> dataSupplier = () -> Arrays.asList(
            new PensionMember("PM001", "Alice Wanjiku", 35, 85000, 0.15, 450000, "STANDARD", true),
            new PensionMember("PM002", "Bob Kimani", 28, 55000, 0.10, 120000, "BASIC", true),
            new PensionMember("PM003", "Catherine Achieng", 52, 120000, 0.20, 980000, "PREMIUM", true),
            new PensionMember("PM004", "David Mwangi", 45, 95000, 0.18, 650000, "STANDARD", true),
            new PensionMember("PM005", "Eve Njeri", 33, 48000, 0.08, 95000, "BASIC", false),
            new PensionMember("PM006", "Frank Omondi", 58, 150000, 0.25, 1500000, "PREMIUM", true),
            new PensionMember("PM007", "Grace Wambui", 41, 78000, 0.12, 320000, "STANDARD", true),
            new PensionMember("PM008", "Henry Otieno", 29, 62000, 0.11, 145000, "BASIC", true),
            new PensionMember("PM009", "Irene Mutiso", 55, 110000, 0.22, 1200000, "PREMIUM", true),
            new PensionMember("PM010", "John Kariuki", 38, 72000, 0.14, 280000, "STANDARD", false));

    public static void main(String[] args) {
        System.out.println("=== PRACTICE EXERCISE 4: COMPLETE FUNCTIONAL SYSTEM ===\n");

        // Get data using Supplier
        List<PensionMember> members = dataSupplier.get();

        // ===== PART 1: VALIDATION PIPELINE =====
        System.out.println("--- PART 1: VALIDATION PIPELINE ---\n");

        // Define validators using custom functional interface
        MemberValidator ageValidator = member -> {
            boolean valid = member.getAge() >= 18 && member.getAge() <= 65;
            String message = valid ? "Age valid" : "Age must be between 18 and 65";
            return new ValidationResult(valid, message);
        };

        MemberValidator salaryValidator = member -> {
            boolean valid = member.getSalary() > 0;
            String message = valid ? "Salary valid" : "Salary must be positive";
            return new ValidationResult(valid, message);
        };

        MemberValidator contributionValidator = member -> {
            boolean valid = member.getContributionRate() >= 0.05 &&
                    member.getContributionRate() <= 0.30;
            String message = valid ? "Contribution rate valid" : "Contribution rate must be between 5% and 30%";
            return new ValidationResult(valid, message);
        };

        // Validate all members
        Consumer<PensionMember> validateMember = member -> {
            System.out.println("Validating: " + member.getName());
            ValidationResult ageResult = ageValidator.validate(member);
            ValidationResult salaryResult = salaryValidator.validate(member);
            ValidationResult contribResult = contributionValidator.validate(member);

            if (ageResult.isValid() && salaryResult.isValid() && contribResult.isValid()) {
                System.out.println("✓ All validations passed");
            } else {
                System.out.println("✗ Validation failed:");
                if (!ageResult.isValid())
                    System.out.println("  - " + ageResult.getMessage());
                if (!salaryResult.isValid())
                    System.out.println("  - " + salaryResult.getMessage());
                if (!contribResult.isValid())
                    System.out.println("  - " + contribResult.getMessage());
            }
            System.out.println();
        };

        // Process first 3 members for validation demo
        members.stream().limit(3).forEach(validateMember);

        // ===== PART 2: FILTERING WITH PREDICATES =====
        System.out.println("\n--- PART 2: FILTERING WITH PREDICATES ---\n");

        // Predicate: Active members only
        Predicate<PensionMember> isActive = PensionMember::isActive;

        // Predicate: Premium members
        Predicate<PensionMember> isPremium = member -> "PREMIUM".equals(member.getMembershipTier());

        // Predicate: Near retirement (within 5 years)
        Predicate<PensionMember> nearRetirement = member -> member.getYearsToRetirement() <= 5;

        // Predicate: High contributors (>15%)
        Predicate<PensionMember> highContributor = member -> member.getContributionRate() > 0.15;

        // Predicate: Large balance (>500K)
        Predicate<PensionMember> largeBalance = member -> member.getCurrentBalance() > 500000;

        // Combined predicates
        Predicate<PensionMember> premiumAndActive = isPremium.and(isActive);
        Predicate<PensionMember> retirementReady = nearRetirement.and(largeBalance);

        // Filter and display
        System.out.println("Active Premium Members:");
        filterAndDisplay(members, premiumAndActive);

        System.out.println("\nNear Retirement with Large Balance:");
        filterAndDisplay(members, retirementReady);

        System.out.println("\nHigh Contributors:");
        filterAndDisplay(members, highContributor);

        // ===== PART 3: TRANSFORMATION WITH FUNCTIONS =====
        System.out.println("\n--- PART 3: TRANSFORMATION WITH FUNCTIONS ---\n");

        // Function: Extract member names
        Function<PensionMember, String> nameExtractor = PensionMember::getName;

        // Function: Calculate annual contribution
        Function<PensionMember, Double> annualContribution = member -> member.getMonthlyContribution() * 12;

        // Function: Create member summary
        Function<PensionMember, String> memberSummary = member -> String.format(
                "%s: Balance KES %.0f, Monthly Contribution KES %.0f",
                member.getName(),
                member.getCurrentBalance(),
                member.getMonthlyContribution());

        // Function: Calculate projected balance after 5 years (simple interest)
        Function<PensionMember, Double> projectedBalance5Years = member -> {
            double annualContrib = annualContribution.apply(member);
            double totalContrib = annualContrib * 5;
            double interest = member.getCurrentBalance() * 0.08 * 5; // 8% annual
            return member.getCurrentBalance() + totalContrib + interest;
        };

        // Function composition: Name to uppercase then add title
        Function<String, String> toUpperCase = String::toUpperCase;
        Function<String, String> addTitle = name -> "Mr./Ms. " + name;
        Function<PensionMember, String> formalName = nameExtractor.andThen(toUpperCase).andThen(addTitle);

        // Apply transformations
        System.out.println("Member Names:");
        members.stream()
                .map(nameExtractor)
                .forEach(name -> System.out.println("  - " + name));

        System.out.println("\nAnnual Contributions:");
        members.stream()
                .map(member -> member.getName() + ": KES " +
                        String.format("%.2f", annualContribution.apply(member)))
                .forEach(System.out::println);

        System.out.println("\n5-Year Balance Projections:");
        members.stream()
                .filter(isActive)
                .forEach(member -> System.out.println(String.format("%s: Current KES %.0f → Projected KES %.0f",
                        member.getName(),
                        member.getCurrentBalance(),
                        projectedBalance5Years.apply(member))));

        // ===== PART 4: PROCESSING WITH CONSUMERS =====
        System.out.println("\n\n--- PART 4: PROCESSING WITH CONSUMERS ---\n");

        // Consumer: Print member details
        Consumer<PensionMember> detailsPrinter = member -> System.out.println("  → " + member);

        // Consumer: Log contribution info
        Consumer<PensionMember> contributionLogger = member -> System.out
                .println(String.format("  [LOG] %s contributes KES %.2f monthly",
                        member.getName(),
                        member.getMonthlyContribution()));

        // Consumer: Send notification (simulated)
        Consumer<PensionMember> notificationSender = member -> System.out
                .println(String.format("  [NOTIFY] Reminder sent to %s regarding contribution",
                        member.getName()));

        // Consumer: Update tier (with side effect)
        Consumer<PensionMember> tierUpdater = member -> {
            if (member.getCurrentBalance() >= 1000000) {
                member.setMembershipTier("PREMIUM");
            } else if (member.getCurrentBalance() >= 300000) {
                member.setMembershipTier("STANDARD");
            } else {
                member.setMembershipTier("BASIC");
            }
            System.out.println(String.format("  [UPDATE] %s tier set to %s",
                    member.getName(),
                    member.getMembershipTier()));
        };

        // Consumer chaining
        Consumer<PensionMember> fullProcessing = detailsPrinter
                .andThen(contributionLogger)
                .andThen(notificationSender);

        System.out.println("Processing Active Members:");
        members.stream()
                .filter(isActive)
                .limit(3)
                .forEach(fullProcessing);

        // ===== PART 5: COMPLEX PIPELINE =====
        System.out.println("\n\n--- PART 5: COMPLETE PROCESSING PIPELINE ---\n");

        // Custom functional interfaces
        ContributionCalculator monthlyCalc = PensionMember::getMonthlyContribution;

        TierEvaluator tierEval = member -> {
            double balance = member.getCurrentBalance();
            if (balance >= 1000000)
                return "PREMIUM";
            if (balance >= 300000)
                return "STANDARD";
            return "BASIC";
        };

        ReportGenerator reportGen = member -> String.format(
                "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                        "PENSION MEMBER REPORT\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                        "Member ID    : %s\n" +
                        "Name         : %s\n" +
                        "Age          : %d years\n" +
                        "Salary       : KES %,.2f\n" +
                        "Contribution : %.1f%% (KES %,.2f/month)\n" +
                        "Balance      : KES %,.2f\n" +
                        "Tier         : %s\n" +
                        "Status       : %s\n" +
                        "Years to 60  : %d years\n" +
                        "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n",
                member.getMemberId(),
                member.getName(),
                member.getAge(),
                member.getSalary(),
                member.getContributionRate() * 100,
                monthlyCalc.calculate(member),
                member.getCurrentBalance(),
                tierEval.evaluateTier(member),
                member.isActive() ? "Active" : "Inactive",
                member.getYearsToRetirement());

        // Complete pipeline: Filter → Validate → Transform → Process
        System.out.println("Complete Pipeline for Premium Active Members:");

        members.stream()
                .filter(isActive) // Step 1: Filter active
                .filter(isPremium) // Step 2: Filter premium
                .peek(m -> System.out.println("\nProcessing: " + m.getName())) // Debug
                .filter(m -> { // Step 3: Validate
                    ValidationResult result = salaryValidator.validate(m);
                    return result.isValid();
                })
                .map(reportGen::generateReport) // Step 4: Transform to report
                .forEach(System.out::println); // Step 5: Output

        // ===== PART 6: STATISTICS AND AGGREGATION =====
        System.out.println("\n--- PART 6: STATISTICS ---\n");

        // Using method references
        DoubleSummaryStatistics balanceStats = members.stream()
                .filter(isActive)
                .mapToDouble(PensionMember::getCurrentBalance)
                .summaryStatistics();

        System.out.println("Balance Statistics (Active Members):");
        System.out.println("  Count   : " + balanceStats.getCount());
        System.out.println("  Average : KES " + String.format("%,.2f", balanceStats.getAverage()));
        System.out.println("  Min     : KES " + String.format("%,.2f", balanceStats.getMin()));
        System.out.println("  Max     : KES " + String.format("%,.2f", balanceStats.getMax()));
        System.out.println("  Total   : KES " + String.format("%,.2f", balanceStats.getSum()));

        // Group by tier
        Map<String, Long> membersByTier = members.stream()
                .collect(Collectors.groupingBy(PensionMember::getMembershipTier,
                        Collectors.counting()));

        System.out.println("\nMembers by Tier:");
        membersByTier.forEach((tier, count) -> System.out.println("  " + tier + ": " + count + " members"));

        // ===== PART 7: ERROR HANDLING =====
        System.out.println("\n--- PART 7: ERROR HANDLING ---\n");

        // Function with error handling
        Function<PensionMember, Optional<Double>> safeProjection = member -> {
            try {
                if (member.getAge() > 60) {
                    return Optional.empty(); // Already retired
                }
                double projection = projectedBalance5Years.apply(member);
                return Optional.of(projection);
            } catch (Exception e) {
                System.out.println("Error calculating projection for " + member.getName());
                return Optional.empty();
            }
        };

        // Process with error handling
        System.out.println("Safe Projections:");
        members.stream()
                .filter(isActive)
                .forEach(member -> {
                    Optional<Double> projection = safeProjection.apply(member);
                    projection.ifPresentOrElse(
                            value -> System.out.println(String.format("  %s: KES %.0f",
                                    member.getName(), value)),
                            () -> System.out.println("  " + member.getName() + ": N/A"));
                });

        System.out.println("\n=== EXERCISE 4 COMPLETE ===");
    }

    // Utility method using Predicate and Consumer
    private static void filterAndDisplay(List<PensionMember> members,
            Predicate<PensionMember> filter) {
        members.stream()
                .filter(filter)
                .forEach(member -> System.out.println("  • " + member.getName() +
                        " (Balance: KES " +
                        String.format("%.0f", member.getCurrentBalance()) +
                        ")"));
    }
}
