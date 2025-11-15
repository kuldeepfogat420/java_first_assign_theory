import java.util.Scanner;

class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited successfully. New balance: " + balance);
        } else {
            System.out.println("Invalid amount! Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount! Withdrawal amount must be positive.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful. Remaining balance: " + balance);
        }
    }

    public void displayAccountDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + accountHolderName);
        System.out.println("Balance        : " + balance);
        System.out.println("Email          : " + email);
        System.out.println("Phone Number   : " + phoneNumber);
        System.out.println("------------------------");
    }

    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated successfully!");
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}

class UserInterface {
    private Account[] accounts;
    private int accountCount;
    private Scanner scanner;

    public UserInterface() {
        accounts = new Account[100];
        accountCount = 0;
        scanner = new Scanner(System.in);
    }

    private Account findAccount(int accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }

    public void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial deposit amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.print("Enter email address: ");
        String email = scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        int newAccNo = 1000 + accountCount + 1;

        accounts[accountCount] = new Account(newAccNo, name, amount, email, phone);
        accountCount++;

        System.out.println("Account created successfully with Account Number: " + newAccNo);
    }

    public void performDeposit() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();

        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    public void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();

        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }


    public void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();
        scanner.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.displayAccountDetails();
        } else {
            System.out.println("Account not found!");
        }
    }

    public void updateContact() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();
        scanner.nextLine();

        Account acc = findAccount(accNo);
        if (acc != null) {
            System.out.print("Enter new email: ");
            String email = scanner.nextLine();

            System.out.print("Enter new phone number: ");
            String phone = scanner.nextLine();

            acc.updateContactDetails(email, phone);
        } else {
            System.out.println("Account not found!");
        }
    }

    public void mainMenu() {
        int choice;

        System.out.println("Welcome to the Banking Application!");

        do {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: createAccount(); break;
                case 2: performDeposit(); break;
                case 3: performWithdrawal(); break;
                case 4: showAccountDetails(); break;
                case 5: updateContact(); break;
                case 6: System.out.println("Exiting application. Thank you!"); break;
                default: System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 6);
    }
}

public class theory_1 {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.mainMenu();
    }
}
