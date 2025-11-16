import java.util.Scanner;

class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}

public class lab_2 {

    public static void validateEmail(String email) throws InvalidEmailException {
        if (!email.contains("@") || !email.contains(".com")) {
            throw new InvalidEmailException("Invalid Email: Must contain '@' and '.com'");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your email: ");
        String email = sc.nextLine();

        try {
            validateEmail(email);
            System.out.println("Email is valid!");
        } catch (InvalidEmailException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}
