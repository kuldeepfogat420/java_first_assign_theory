import java.util.InputMismatchException;
import java.util.Scanner;

class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Error: Division by zero is not allowed!");
        }
        return (double) a / b;
    }
}

public class theory_2 {

    private Scanner scanner = new Scanner(System.in);
    private Calculator calculator = new Calculator();

    public void performAddition() {
        System.out.println("Choose addition type:");
        System.out.println("1. Add two integers");
        System.out.println("2. Add two doubles");
        System.out.println("3. Add three integers");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter first integer: ");
                int a1 = scanner.nextInt();
                System.out.print("Enter second integer: ");
                int b1 = scanner.nextInt();
                System.out.println("Result: " + calculator.add(a1, b1));
                break;

            case 2:
                System.out.print("Enter first double: ");
                double a2 = scanner.nextDouble();
                System.out.print("Enter second double: ");
                double b2 = scanner.nextDouble();
                System.out.println("Result: " + calculator.add(a2, b2));
                break;

            case 3:
                System.out.print("Enter first integer: ");
                int x = scanner.nextInt();
                System.out.print("Enter second integer: ");
                int y = scanner.nextInt();
                System.out.print("Enter third integer: ");
                int z = scanner.nextInt();
                System.out.println("Result: " + calculator.add(x, y, z));
                break;

            default:
                System.out.println("Invalid choice!");
        }
    }

    public void performSubtraction() {
        try {
            System.out.print("Enter first integer: ");
            int a = scanner.nextInt();
            System.out.print("Enter second integer: ");
            int b = scanner.nextInt();

            System.out.println("Result: " + calculator.subtract(a, b));
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter integers only.");
            scanner.next(); 
        }
    }

    public void performMultiplication() {
        try {
            System.out.print("Enter first double: ");
            double a = scanner.nextDouble();
            System.out.print("Enter second double: ");
            double b = scanner.nextDouble();

            System.out.println("Result: " + calculator.multiply(a, b));
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter numbers only.");
            scanner.next();
        }
    }

    public void performDivision() {
        try {
            System.out.print("Enter numerator (integer): ");
            int a = scanner.nextInt();
            System.out.print("Enter denominator (integer): ");
            int b = scanner.nextInt();

            double result = calculator.divide(a, b); 
            System.out.println("Result: " + result);

        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Enter integers only.");
            scanner.next();
        }
    }

    public void mainMenu() {
        int choice;

        do {
            System.out.println("\n=== Welcome to the Calculator Application ===");
            System.out.println("1. Add Numbers");
            System.out.println("2. Subtract Numbers");
            System.out.println("3. Multiply Numbers");
            System.out.println("4. Divide Numbers");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input! Enter a number between 1-5.");
                scanner.next();
            }

            choice = scanner.nextInt();

            switch (choice) {
                case 1: performAddition(); break;
                case 2: performSubtraction(); break;
                case 3: performMultiplication(); break;
                case 4: performDivision(); break;
                case 5: System.out.println("Exiting... Thank you!"); break;
                default: System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 5);
    }

    public static void main(String[] args) {
        new theory_2().mainMenu();
    }
}
