package com.evecandy.exercises.javase015.ArrayListandList;

import java.util.Optional;
import java.util.List;

public class StudentManagementSystem {

    public static void main(String[] args) {

        System.out.println("========== STUDENT MANAGEMENT SYSTEM ==========\n");

        StudentManager manager = new StudentManager();

        // TEST 1: Add students

        System.out.println("--- Test 1: Adding Students ---");
        manager.addStudent(1, "Alice", "A");
        manager.addStudent(2, "Bob", "B");
        manager.addStudent(3, "Charlie", "A");
        manager.addStudent(4, "Diana", "C");
        manager.addStudent(5, "Eve", "B");

        // Try to add duplicate ID
        manager.addStudent(2, "Bob Clone", "A"); // It Should fail

        System.out.println("\nTotal students: " + manager.getTotalStudents());
        manager.displayAllStudents();

        // TEST 2: Find student by ID
        System.out.println("--- Test 2: Find by ID ---");

        Optional<Student> found = manager.findStudentById(3);
        if (found.isPresent()) {
            System.out.println("  Found: " + found.get());
        } else {
            System.out.println("  Not found");
        }

        // Trying to find a non-existent student
        Optional<Student> notFound = manager.findStudentById(99);
        if (notFound.isPresent()) {
            System.out.println("  Found: " + notFound.get());
        } else {
            System.out.println("  Student ID 99 not found");
        }
        System.out.println();

        // TEST 3: Find students by name

        System.out.println("--- Test 3: Find by Name (partial match) ---");

        List<Student> aliceStudents = manager.findStudentsByName("ali"); // Case-insensitive
        System.out.println("  Students with 'ali' in name:");
        aliceStudents.forEach(s -> System.out.println("    " + s));
        System.out.println();

        // TEST 4: Find students by grade

        System.out.println("--- Test 4: Find by Grade ---");

        List<Student> aStudents = manager.findStudentsByGrade("A");
        System.out.println("  Students with grade A:");
        aStudents.forEach(s -> System.out.println("    " + s));
        System.out.println();

        // TEST 5: Update student grade

        System.out.println("--- Test 5: Update Grade ---");
        manager.updateGrade(2, "A"); // Bob: B → A
        manager.updateGrade(99, "A"); // Non-existent student
        manager.displayAllStudents();

        // TEST 6: Grade distribution

        System.out.println("--- Test 6: Grade Distribution ---");
        manager.displayGradeDistribution();

        // TEST 7: Sort students

        System.out.println("--- Test 7: Sorted Lists ---");

        List<Student> byName = manager.getStudentsSortedByName();
        manager.displayStudentList(byName, "Sorted by Name");

        List<Student> byId = manager.getStudentsSortedById();
        manager.displayStudentList(byId, "Sorted by ID");

        // TEST 8: Remove student

        System.out.println("--- Test 8: Remove Student ---");
        manager.removeStudent(3); // Remove Charlie
        manager.removeStudent(99); // Try to remove non-existent
        manager.displayAllStudents();

        // TEST 9: ArrayList operations demonstration

        System.out.println("--- Test 9: ArrayList Operations Demo ---");

        System.out.println("  Current size: " + manager.getTotalStudents());
        System.out.println("  Is empty? " + manager.isEmpty());
        System.out.println("  Student ID 1 exists? " + manager.studentExists(1));
        System.out.println("  Student ID 3 exists? " + manager.studentExists(3)); // Was removed earlier
        System.out.println();

        // TEST 10: Add more students and test

        System.out.println("--- Test 10: Adding More Students ---");
        manager.addStudent(6, "Frank", "D");
        manager.addStudent(7, "Grace", "A");
        manager.addStudent(8, "Henry", "F");
        manager.displayAllStudents();
        manager.displayGradeDistribution();

        // TEST 11: Clearing all students

        System.out.println("--- Test 11: Clear All Students ---");
        manager.clearAllStudents();
        System.out.println("  Is empty now? " + manager.isEmpty());
        manager.displayAllStudents();

        System.out.println("========== END OF DEMONSTRATION ==========");
    }
}
