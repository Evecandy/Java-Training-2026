package com.evecandy.exercises.javase019;

/**
 * Custom Functional Interfaces for Business Logic
 * These interfaces are specific to pension member processing
 */

@FunctionalInterface
interface MemberValidator {
    ValidationResult validate(PensionMember member);
}

@FunctionalInterface
interface ContributionCalculator {
    double calculate(PensionMember member);
}

@FunctionalInterface
interface TierEvaluator {
    String evaluateTier(PensionMember member);
}

@FunctionalInterface
interface ReportGenerator {
    String generateReport(PensionMember member);
}
