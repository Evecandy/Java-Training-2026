package com.evecandy.exercises.javase007;

public class StudentTest {
    public static void main(String[] args) {

        // Test 1: Creating Student with different constructors
        System.out.println("\n===== Create Students=====");
        Student student1 = new Student();
        Student student2 = new Student("Leo", "15554");
        Student student3 = new Student("Linet", "15556", 5);

        student1.displayInfo();
        student2.displayInfo();
        student3.displayInfo();

        // Test 2: Adding grades
        System.out.println("\n----- Adding Grades to Leo -----");

        student2.addGrade(85);
        student2.addGrade(90);
        student2.addGrade(78);
        student2.addGrade(92);
        student2.displayInfo();

        System.out.println("\n----- Adding Grades to Linet -----");
        student3.addGrade(88);
        student3.addGrade(76);
        student3.addGrade(95);
        student3.addGrade(89);
        student3.displayInfo();

        // Test 3: Invalid grades
        System.out.println("\n----- Testing Invalid Grades -----");
        student2.addGrade(-10);
        student2.addGrade(120);

        // Test 4: Array of Students
        System.out.println("------ The array of students -----");
        Student[] classroom = new Student[3];
        classroom[0] = new Student("Leila", "15553");
        classroom[1] = new Student("Afrikana", "15557");
        classroom[2] = new Student("Eve", "15558");

        // Add grades to all students
        classroom[0].addGrade(91);
        classroom[0].addGrade(82);

        classroom[1].addGrade(75);
        classroom[1].addGrade(88);

        classroom[2].addGrade(93);
        classroom[2].addGrade(87);

        // Display all students
        System.out.println("\n----- All students in the classroom ");
        for (int i = 1; i < classroom.length; i++) {
            classroom[i].displayInfo();

        }
        System.out.println("----- top student in the classroom -----");

        // Test 5: To find student with the highest GPA
        Student topStudent = classroom[0];
        for (int i = 1; i < classroom.length; i++) {
            if (classroom[i].getGPA() > topStudent.getGPA()) {
                topStudent = classroom[i];
            }
        }

        System.out.println(
                "\nTop Student:" + topStudent.getName() + " with GPA: " + String.format("%.2f", topStudent.getGPA()));

        // Test 6: Object Reference Test
        System.out.println("\n----- Testing Object References -----");
        Student ref1 = student2; // Ref 1 points to the same object as student2
        System.out.println("student2 name: " + student2.getName());
        System.out.println("ref1 name: " + ref1.getName());

        ref1.setName("Lena"); // changes affect both
        System.out.println("\n----- After changing ref1's name: ");
        System.out.println("student2 name: " + student2.getName());
        System.out.println("ref1 name: " + ref1.getName());
    }
}