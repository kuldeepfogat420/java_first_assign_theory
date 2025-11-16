import java.io.*;
import java.util.*;

class Book implements Serializable, Comparable<Book> {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private boolean isIssued;

    public Book(int bookId, String title, String author, String category) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.isIssued = false;
    }

    public int getBookId() { return bookId; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getCategory() { return category; }
    public boolean isIssued() { return isIssued; }

    public void markAsIssued() { isIssued = true; }
    public void markAsReturned() { isIssued = false; }

    public void displayBookDetails() {
        System.out.println("ID: " + bookId + ", Title: " + title + ", Author: " + author +
                ", Category: " + category + ", Issued: " + (isIssued ? "Yes" : "No"));
    }

    @Override
    public int compareTo(Book o) {
        return this.title.compareToIgnoreCase(o.title);
    }
}

class Member implements Serializable {
    private int memberId;
    private String name;
    private String email;
    private List<Integer> issuedBooks;

    public Member(int memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.issuedBooks = new ArrayList<>();
    }

    public int getMemberId() { return memberId; }
    public String getEmail() { return email; }

    public void addIssuedBook(int bookId) {
        issuedBooks.add(bookId);
    }

    public void returnIssuedBook(int bookId) {
        issuedBooks.remove(Integer.valueOf(bookId));
    }

    public List<Integer> getIssuedBooks() {
        return issuedBooks;
    }

    public void displayMemberDetails() {
        System.out.println("ID: " + memberId + ", Name: " + name + ", Email: " + email);
        System.out.println("Issued Books: " + issuedBooks);
    }
}

public class theory_4 {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Member> members = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    private final String booksFile = "books.txt";
    private final String membersFile = "members.txt";

    public theory_4() {
        loadFromFile();
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(booksFile))) {
            books = (Map<Integer, Book>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Books file not found. Creating new file.");
        } catch (Exception e) {
            System.out.println("Error loading books: " + e.getMessage());
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(membersFile))) {
            members = (Map<Integer, Member>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Members file not found. Creating new file.");
        } catch (Exception e) {
            System.out.println("Error loading members: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(booksFile))) {
            oos.writeObject(books);
        } catch (Exception e) {
            System.out.println("Error saving books: " + e.getMessage());
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(membersFile))) {
            oos.writeObject(members);
        } catch (Exception e) {
            System.out.println("Error saving members: " + e.getMessage());
        }
    }

    public void addBook() {
        try {
            System.out.print("Enter Book ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            if (books.containsKey(id)) {
                System.out.println("Book ID already exists!");
                return;
            }
            System.out.print("Enter Book Title: ");
            String title = scanner.nextLine();
            System.out.print("Enter Author: ");
            String author = scanner.nextLine();
            System.out.print("Enter Category: ");
            String category = scanner.nextLine();

            Book book = new Book(id, title, author, category);
            books.put(id, book);
            saveToFile();
            System.out.println("Book added successfully!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Book not added.");
            scanner.nextLine();
        }
    }

    public void addMember() {
        try {
            System.out.print("Enter Member ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            if (members.containsKey(id)) {
                System.out.println("Member ID already exists!");
                return;
            }
            System.out.print("Enter Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Email: ");
            String email = scanner.nextLine();

            Member member = new Member(id, name, email);
            members.put(id, member);
            saveToFile();
            System.out.println("Member added successfully!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Member not added.");
            scanner.nextLine();
        }
    }

    public void issueBook() {
        try {
            System.out.print("Enter Book ID: ");
            int bookId = scanner.nextInt();
            System.out.print("Enter Member ID: ");
            int memberId = scanner.nextInt();

            Book book = books.get(bookId);
            Member member = members.get(memberId);

            if (book == null) {
                System.out.println("Book not found!");
                return;
            }
            if (member == null) {
                System.out.println("Member not found!");
                return;
            }
            if (book.isIssued()) {
                System.out.println("Book is already issued.");
                return;
            }

            book.markAsIssued();
            member.addIssuedBook(bookId);
            saveToFile();
            System.out.println("Book issued successfully!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            scanner.nextLine();
        }
    }

    public void returnBook() {
        try {
            System.out.print("Enter Book ID: ");
            int bookId = scanner.nextInt();
            System.out.print("Enter Member ID: ");
            int memberId = scanner.nextInt();

            Book book = books.get(bookId);
            Member member = members.get(memberId);

            if (book == null || member == null) {
                System.out.println("Book or Member not found!");
                return;
            }
            if (!book.isIssued()) {
                System.out.println("Book is not issued.");
                return;
            }

            book.markAsReturned();
            member.returnIssuedBook(bookId);
            saveToFile();
            System.out.println("Book returned successfully!");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input.");
            scanner.nextLine();
        }
    }

    public void searchBooks() {
        scanner.nextLine();
        System.out.print("Enter search term (title/author/category): ");
        String query = scanner.nextLine().toLowerCase();

        boolean found = false;
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(query) ||
                    book.getAuthor().toLowerCase().contains(query) ||
                    book.getCategory().toLowerCase().contains(query)) {
                book.displayBookDetails();
                found = true;
            }
        }

        if (!found) System.out.println("No books found matching the query.");
    }

    public void sortBooks() {
        List<Book> bookList = new ArrayList<>(books.values());
        System.out.println("Sort by: 1. Title 2. Author 3. Category");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> Collections.sort(bookList);
            case 2 -> bookList.sort(Comparator.comparing(Book::getAuthor, String.CASE_INSENSITIVE_ORDER));
            case 3 -> bookList.sort(Comparator.comparing(Book::getCategory, String.CASE_INSENSITIVE_ORDER));
            default -> {
                System.out.println("Invalid choice.");
                return;
            }
        }

        for (Book book : bookList) book.displayBookDetails();
    }

    public void mainMenu() {
        int choice;
        do {
            System.out.println("\n===== City Library Digital Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Search Books");
            System.out.println("6. Sort Books");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Enter a number 1-7.");
                scanner.next();
            }

            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addBook();
                case 2 -> addMember();
                case 3 -> issueBook();
                case 4 -> returnBook();
                case 5 -> searchBooks();
                case 6 -> sortBooks();
                case 7 -> System.out.println("Exiting program. Thank you!");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 7);

        scanner.close();
    }

    public static void main(String[] args) {
        new theory_4().mainMenu();
    }
}
