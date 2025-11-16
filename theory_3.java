import java.util.InputMismatchException;
import java.util.Scanner;

class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);
    }
}

class Student {
    private int rollNumber;
    private String studentName;
    private int[] marks = new int[3];

    public Student(int rollNumber, String studentName, int[] marks) throws InvalidMarksException {
        this.rollNumber = rollNumber;
        this.studentName = studentName;
        this.marks = marks;
        validateMarks();
    }

    public void validateMarks() throws InvalidMarksException {
        for (int i = 0; i < marks.length; i++) {
            if (marks[i] < 0 || marks[i] > 100) {
                throw new InvalidMarksException(
                        "Invalid marks for subject " + (i + 1) + ": " + marks[i]);
            }
        }
    }

    public double calculateAverage() {
        int total = 0;
        for (int m : marks) {
            total += m;
        }
        return total / 3.0;
    }

    public void displayResult() {
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Student Name: " + studentName);
        System.out.print("Marks: ");
        for (int m : marks) {
            System.out.print(m + " ");
        }
        System.out.println();
        System.out.println("Average: " + calculateAverage());
        System.out.println("Result: " + (calculateAverage() >= 40 ? "Pass" : "Fail"));
    }

    public int getRollNumber() {
        return rollNumber;
    }
}

public class theory_3 {
    private Student[] students = new Student[100];
    private int studentCount = 0;
    private Scanner scanner = new Scanner(System.in);

    public void addStudent() {
        try {
            System.out.print("Enter Roll Number: ");
            int roll = scanner.nextInt();
            scanner.nextLine(); 
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();

            int[] marks = new int[3];
            for (int i = 0; i < 3; i++) {
                System.out.print("Enter marks for subject " + (i + 1) + ": ");
                marks[i] = scanner.nextInt();
            }

            students[studentCount++] = new Student(roll, name, marks);
            System.out.println("Student added successfully. Returning to main menu...");

        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage() + " Returning to main menu...");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter integers for roll number and marks.");
            scanner.nextLine();
        } finally {
            System.out.println();
        }
    }

    public void showStudentDetails() {
        try {
            System.out.print("Enter Roll Number to search: ");
            int roll = scanner.nextInt();

            boolean found = false;
            for (int i = 0; i < studentCount; i++) {
                if (students[i].getRollNumber() == roll) {
                    students[i].displayResult();
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Student with Roll Number " + roll + " not found.");
            }
            System.out.println("Search completed.");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter an integer for Roll Number.");
            scanner.nextLine();
        } finally {
            System.out.println();
        }
    }

    public void mainMenu() {
        int choice;

        do {
            System.out.println("===== Student Result Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. Show Student Details");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Enter a number between 1-3.");
                scanner.next();
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    showStudentDetails();
                    break;
                case 3:
                    System.out.println("Exiting program. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 3);

        scanner.close();
    }

    public static void main(String[] args) {
        new theory_3().mainMenu();
    }
}
