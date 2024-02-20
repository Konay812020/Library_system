import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibrarySystem {
    private static Map<String, Book> library = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addBooks();
                    break;
                case 2:
                    borrowBooks();
                    break;
                case 3:
                    returnBooks();
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 4);
    }

    private static void displayMenu() {
        System.out.println("\nLibrary System Menu:");
        System.out.println("1. Add Books");
        System.out.println("2. Borrow Books");
        System.out.println("3. Return Books");
        System.out.println("4. Exit");
    }

    private static void addBooks() {
        System.out.println("\nAdd Books:");
        System.out.print("Enter the book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the author: ");
        String author = scanner.nextLine();
        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt();

        Book book = library.getOrDefault(title, new Book(title, author, 0));
        book.setQuantity(book.getQuantity() + quantity);
        library.put(title, book);
        System.out.println("Book added successfully!");
    }

    private static void borrowBooks() {
        System.out.println("\nBorrow Books:");
        System.out.print("Enter the book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the number of books to borrow: ");
        int quantityToBorrow = scanner.nextInt();

        Book book = library.get(title);
        if (book != null && book.getQuantity() >= quantityToBorrow) {
            book.setQuantity(book.getQuantity() - quantityToBorrow);
            System.out.println("Books borrowed successfully!");
        } else {
            System.out.println("Error: Not enough copies available for borrowing.");
        }
    }

    private static void returnBooks() {
        System.out.println("\nReturn Books:");
        System.out.print("Enter the book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the number of books to return: ");
        int quantityToReturn = scanner.nextInt();

        Book book = library.get(title);
        if (book != null) {
            book.setQuantity(book.getQuantity() + quantityToReturn);
            System.out.println("Books returned successfully!");
        } else {
            System.out.println("Error: The specified book does not belong to the library system.");
        }
    }

    static class Book {
        private String title;
        private String author;
        private int quantity;

        public Book(String title, String author, int quantity) {
            this.title = title;
            this.author = author;
            this.quantity = quantity;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
