import java.util.Scanner;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class BankAccount {
    private String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
            System.out.println("Current Balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Insufficient balance. Current balance: " + balance);
        }

        balance -= amount;
        System.out.println("Withdrawn: " + amount);
        System.out.println("Current Balance: " + balance);
    }

    public double getBalance() {
        return balance;
    }
}

public class lab_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();

        BankAccount account = new BankAccount(name, 0.0);

        while (true) {
            System.out.println("\n===== Bank Menu =====");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount to deposit: ");
                    double amount = scanner.nextDouble();
                    account.deposit(amount);
                }
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ");
                    double amount = scanner.nextDouble();
                    try {
                        account.withdraw(amount);
                    } catch (InsufficientBalanceException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                case 3 -> System.out.println("Current Balance: " + account.getBalance());
                case 4 -> {
                    System.out.println("Exiting. Thank you!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
