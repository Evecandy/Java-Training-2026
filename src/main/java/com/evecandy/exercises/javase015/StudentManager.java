package com.evecandy.exercises.javase015;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentManager {

    // Using List interface instead of ArrayList for flexibility
    private List<Student> students;

    public StudentManager() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        // Check if student with this ID already exists
        if (findStudentById(student.getId()).isPresent()) {
            System.out.println("Student with ID " + student.getId() + " already exists!");
            return;
        }

        students.add(student);
        System.out.println("Added: " + student);
    }

    public void addStudent(int id, String name, String grade) {
        addStudent(new Student(id, name, grade));
    }

    // OPERATIONS - Searching/Finding

    public Optional<Student> findStudentById(int id) {
        return students.stream()
                .filter(student -> student.getId() == id)
                .findFirst();
    }

    public List<Student> findStudentsByName(String name) {
        return students.stream()
                .filter(student -> student.getName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Student> findStudentsByGrade(String grade) {
        return students.stream()
                .filter(student -> student.getGrade().equalsIgnoreCase(grade))
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsSortedByName() {
        return students.stream()
                .sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsSortedById() {
        return students.stream()
                .sorted((s1, s2) -> Integer.compare(s1.getId(), s2.getId()))
                .collect(Collectors.toList());
    }

    // UPDATE OPERATIONS

    public boolean updateGrade(int id, String newGrade) {
        Optional<Student> studentOpt = findStudentById(id);

        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            String oldGrade = student.getGrade();
            student.setGrade(newGrade);
            System.out.println(" Updated student ID " + id + ": "
                    + oldGrade + " → " + newGrade);
            return true;
        } else {
            System.out.println("   Student with ID " + id + " not found!");
            return false;
        }
    }

    // DELETE OPERATIONS

    public boolean removeStudent(int id) {
        // Java 8 way - removeIf with lambda
        boolean removed = students.removeIf(student -> student.getId() == id);

        if (removed) {
            System.out.println("  Removed student with ID: " + id);
        } else {
            System.out.println("  Student with ID " + id + " not found");
        }

        return removed;
    }

    // Removes all students from the system.

    public void clearAllStudents() {
        students.clear();
        System.out.println(" All students cleared from system");
    }

    // UTILITY OPERATIONS

    public boolean studentExists(int id) {
        return findStudentById(id).isPresent();
    }

    public int getTotalStudents() {
        return students.size();
    }

    public boolean isEmpty() {
        return students.isEmpty();
    }

    // DISPLAY OPERATIONS

    public void displayAllStudents() {
        System.out.println("\n========== ALL STUDENTS ==========");

        if (students.isEmpty()) {
            System.out.println("  (No students in the system)");
        } else {
            // Java 8 forEach with lambda
            students.forEach(student -> System.out.println("  " + student));
        }

        System.out.println("==================================\n");
    }

    public void displayGradeDistribution() {
        System.out.println("\n========== GRADE DISTRIBUTION ==========");

        String[] grades = { "A", "B", "C", "D", "F" };

        for (String grade : grades) {
            long count = students.stream()
                    .filter(s -> s.getGrade().equalsIgnoreCase(grade))
                    .count();

            System.out.println("  Grade " + grade + ": " + count + " student(s)");
        }

        System.out.println("========================================\n");
    }

    public void displayStudentList(List<Student> studentList, String title) {
        System.out.println("\n--- " + title + " ---");

        if (studentList.isEmpty()) {
            System.out.println("  (No students)");
        } else {
            studentList.forEach(s -> System.out.println("  " + s));
        }

        System.out.println();
    }
}
