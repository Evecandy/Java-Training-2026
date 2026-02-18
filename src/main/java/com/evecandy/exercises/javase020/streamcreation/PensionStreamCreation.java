package com.evecandy.exercises.javase020.streamcreation;

import java.util.*;
import java.util.stream.*;

public class PensionStreamCreation {
    public static void main(String[] args) {
        // 1. From Collections: List of Pension Plans
        List<String> pensionPlans = Arrays.asList("Retirement Savings", "Old Age Security", "Private Pension");
        Stream<String> planStream = pensionPlans.stream();
        System.out.println("Plans: " + planStream.collect(Collectors.toList()));

        // 2. From Arrays: Member IDs
        Integer[] memberIds = { 101, 102, 103, 104 };
        Stream<Integer> idStream = Arrays.stream(memberIds);
        System.out.println("Member IDs: " + idStream.collect(Collectors.toList()));

        // 3. Stream.of(): Statuses
        Stream<String> statusStream = Stream.of("Active", "Retired", "Deferred");
        System.out.println("Statuses: " + statusStream.collect(Collectors.toList()));

        // 4. Generate Infinite Stream (with limit): Simulate Monthly Contributions
        // Generates a stream of constant contribution amounts (e.g., $500)
        Stream<Double> contributionStream = Stream.generate(() -> 500.00).limit(5);
        System.out.println("Next 5 Contributions: " + contributionStream.collect(Collectors.toList()));

        // 5. Primitive Streams: Years of Service range
        // IntStream.rangeClosed includes the last number (1 to 30 years)
        IntStream serviceYears = IntStream.rangeClosed(1, 30);
        System.out.println("Service Years count: " + serviceYears.count());
    }

}
