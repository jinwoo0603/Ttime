package backend;
import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int totalCredits = 0;
        double totalGradePoints = 0.0;

        System.out.print("Enter the number of courses: ");
        int numCourses = input.nextInt();

        for (int i = 1; i <= numCourses; i++) {
            System.out.print("Enter credit hours for course " + i + ": ");
            int creditHours = input.nextInt();
            totalCredits += creditHours;

            System.out.print("Enter the grade (A, B, C, D, or F) for course " + i + ": ");
            String grade = input.next();
            double gradePoints = calculateGradePoints(grade);
            totalGradePoints += (gradePoints * creditHours);
        }

        if (totalCredits > 0) {
            double gpa = totalGradePoints / totalCredits;
            System.out.println("Total credits: " + totalCredits);
            System.out.println("GPA: " + gpa);
        } else {
            System.out.println("No courses entered.");
        }
    }

    // Helper method to calculate grade points
    public static double calculateGradePoints(String grade) {
        switch (grade) {
            case "A":
                return 4.0;
            case "B":
                return 3.0;
            case "C":
                return 2.0;
            case "D":
                return 1.0;
            case "F":
                return 0.0;
            default:
                return 0.0; // Handle invalid grades as 0.0
        }
    }
}
