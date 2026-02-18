package com.evecandy.exercises.javase020.intermediateoperations;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Member {
    String name;
    double fundBalance;
    List<String> beneficiaries;

    public Member(String name, double fundBalance, List<String> beneficiaries) {
        this.name = name;
        this.fundBalance = fundBalance;
        this.beneficiaries = beneficiaries;
    }

    // Getters for stream usage
    public String getName() {
        return name;
    }

    public double getBalance() {
        return fundBalance;
    }

    public List<String> getBeneficiaries() {
        return beneficiaries;
    }

    @Override
    public String toString() {
        return name + " ($" + fundBalance + ")";
    }
}

public class PensionDataProcessing {
    public static void main(String[] args) {
        List<Member> members = Arrays.asList(
                new Member("Alice", 150000, Arrays.asList("Bob", "Charlie")),
                new Member("David", 50000, Arrays.asList("Eva")),
                new Member("Frank", 200000, Arrays.asList("Grace", "Heidi")),
                new Member("Bob", 45000, Arrays.asList()) // Low balance
        );

        // 1. Filter: Find High Value Members (> $100,000)
        List<Member> highValueMembers = members.stream()
                .filter(m -> m.getBalance() > 100000)
                .collect(Collectors.toList());
        System.out.println("High Value Members: " + highValueMembers);

        // 2. Map: Extract just the names of all members
        List<String> memberNames = members.stream()
                .map(Member::getName)
                .collect(Collectors.toList());
        System.out.println("All Names: " + memberNames);

        // 3. FlatMap: List all unique beneficiaries across all members
        // Flattens the List<String> inside each Member into a single stream of Strings
        List<String> allBeneficiaries = members.stream()
                .flatMap(m -> m.getBeneficiaries().stream())
                .distinct() // Remove duplicates if any
                .collect(Collectors.toList());
        System.out.println("All Beneficiaries: " + allBeneficiaries);

        // 4. Sorted & Peek: Sort by balance descending, peek to debug
        List<Member> sortedMembers = members.stream()
                .peek(m -> System.out.println("Processing: " + m.getName())) // Debugging
                .sorted(Comparator.comparingDouble(Member::getBalance).reversed())
                .collect(Collectors.toList());
        System.out.println("Sorted by Balance (High to Low): " + sortedMembers);
    }

}
