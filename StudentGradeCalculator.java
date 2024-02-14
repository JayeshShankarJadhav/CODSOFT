import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Take marks obtained in each subject
        System.out.println("Enter marks obtained in each subject (out of 100):");

        int totalSubjects;
        int totalMarks = 0;

        // Get the total number of subjects
        System.out.print("Enter the total number of subjects: ");
        totalSubjects = scanner.nextInt();

        // Calculate Total Marks and validate input
        for (int i = 1; i <= totalSubjects; i++) {
            System.out.print("Enter marks for Subject " + i + ": ");
            int marks = scanner.nextInt();

            // Validate input marks (should be between 0 and 100)
            if (marks < 0 || marks > 100) {
                System.out.println("Invalid marks! Marks should be between 0 and 100.");
                i--; // Decrement i to re-enter marks for the same subject
            } else {
                totalMarks += marks;
            }
        }

        // Calculate Average Percentage
        double Percentage = (double) totalMarks / totalSubjects;

        // Grade Calculation
        char grade;
        if (Percentage >= 90) {
            grade = 'A';
        } else if (Percentage >= 80) {
            grade = 'B';
        } else if (Percentage >= 70) {
            grade = 'C';
        } else if (Percentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Display Results
        System.out.println("\nResults:");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + Percentage + "%");
        System.out.println("Grade: " + grade);

        // Close the scanner to avoid resource leak
        scanner.close();
    }
}
