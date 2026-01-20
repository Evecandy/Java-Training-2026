package com.evecandy.Assignment1_EveCandy;

import java.util.Scanner;

public class StudentInformation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();

        System.out.print("Enter grade (e.g., A, B, C, D, F): ");
        char grade = scanner.next().charAt(0);
        System.out.print("Enter GPA: ");
        double gpa = scanner.nextDouble();
        System.out.print("Is Graduating (true/false): ");
        boolean isGraduating = scanner.nextBoolean();

        System.out.println("n=== Student Information ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Grade: " + grade);
        System.out.println("GPA: " + gpa);
        System.out.println("Graduating: " + isGraduating);
        scanner.close();

    }

}
