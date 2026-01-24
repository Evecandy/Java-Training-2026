package com.evecandy.exercises.javase007;

public class Student {
    private String name;
    private String studentId;
    private double[] grades;
    private int gradeCount;
    private double gpa;

    // constructor1: default constructor
    public Student() {
        this.name = "Unknown";
        this.studentId = "00000";
        this.grades = new double[10]; // this is space for ten grades
        this.gradeCount = 0;
        this.gpa = 0.0;
    }

    // constructor2: constructor with name and studentId
    public Student(String name, String studentID) {
        this.name = name;
        this.studentId = studentID;
        this.grades = new double[10];
        this.gradeCount = 0;
        this.gpa = 0.0;
    }

    // constructor3: constructor with all properties
    public Student(String name, String studentId, int maxGrades) {
        this.name = name;
        this.studentId = studentId;
        this.grades = new double[maxGrades];
        this.gradeCount = 0;
        this.gpa = 0.0;
    }

    // method: add a grade
    public void addGrade(double grade) {
        if (grade < 0 || grade > 100) {
            System.out.println("Invalid grade! Grade must be between 0 and 100.");
            return;

        }

        if (gradeCount < grades.length) {
            grades[gradeCount] = grade;
            gradeCount++;
            calculateGPA();
            System.out.println("Grade " + grade + "added successfully.");
        } else {
            System.out.println("Array list is full! Cannot add more grades.");
        }
    }

    // method: calculate GPA
    private void calculateGPA() {
        if (gradeCount == 0) {
            gpa = 0.0;
            return;
        }

        double sum = 0;
        for (int i = 0; i < gradeCount; i++) {
            sum += grades[i];
        }
        gpa = sum / gradeCount;
    }

    public void displayInfo() {

        System.out.println("\n===== Student Info =====");
        System.out.println("Name: " + name);
        System.out.println("Student ID: " + studentId);
        System.out.println("Number of Grades: " + gradeCount);

        System.out.println("Grades: ");
        if (gradeCount == 0) {
            System.out.println("No grades available.");
        } else {

            for (int i = 0; i < gradeCount; i++) {
                System.out.println(grades[i]);
                if (i < gradeCount - 1) {
                    // System.out.print(", ");
                }

            }
            System.out.println();
        }
        System.out.printf("GPA: %.2f\n", gpa);
        System.out.println("===============\n");
    }

    // getters
    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public double getGPA() {
        return gpa;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

}
