package com.evecandy.exercises.javase008;

public class LibraryTest {
    public static void main(String[] args) {
        System.out.println("===== Library Book System Test =====\n");

        // Test 1: Create valid book
        System.out.println("--- Test 1: Creating Valid Book ---");
        Book book1 = new Book("978-0073398211", "Shigley's Mechanical Engineering Design (11th Edition",
                "Richard Budynas and Keith Nisbett");
        book1.displayInfo();

        // Test 2: Testing validation with method
        System.out.println("--- Test 2: Testing Validation ---");

        System.out.println("\nAttempting to create book with invalid ISBN (123):");
        Book invalidISBN = createBookSafely("123", "Bad ISBN", "Author");

        System.out.println("\nAttempting to create book with empty title:");
        Book emptyTitle = createBookSafely("9780134685991", "", "Author");

        System.out.println("\nAttempting to create book with empty author:");
        Book emptyAuthor = createBookSafely("9780134685991", "Title", "");

        // Test 3: Borrow a book
        System.out.println("\n--- Test 3: Borrowing Book ---");
        book1.borrowBook(14); // Borrow for 14 days
        book1.displayInfo();

        // Test 4: Try to borrow already borrowed book
        System.out.println("--- Test 4: Try to Borrow Again ---");
        book1.borrowBook(7); // Should fail

        // Test 5: Return the book
        System.out.println("\n--- Test 5: Returning Book ---");
        book1.returnBook();
        book1.displayInfo();

        // Test 6: Try to return already available book
        System.out.println("--- Test 6: Try to Return Again ---");
        book1.returnBook(); // Should fail

        // Test 7: Test overdue book
        System.out.println("\n--- Test 7: Overdue Book ---");
        Book book2 = new Book("978-0465050659", "The Design of Everyday Things", "Don Norman");
        book2.borrowBook(7);

        System.out.println("\n(Checking if book is overdue...)");
        System.out.println("Is overdue: " + book2.isOverdue());

        // Test 8: Update title and author
        System.out.println("\n--- Test 8: Updating Book Info ---");
        book1.setTitle("Shigley's Mechanical Engineering Design (11th Edition)");
        book1.setAuthor("Richard Budynas and Keith Nisbett");
        book1.displayInfo();

        // Test 9: Try to set invalid title
        System.out.println("--- Test 9: Invalid Updates ---");
        book1.setTitle(""); // Should fail
        book1.setAuthor("   "); // Should fail

        // Test 10: Demonstrate immutability of ISBN
        System.out.println("\n--- Test 10: ISBN Immutability ---");
        System.out.println("Current ISBN: " + book1.getIsbn());
        System.out.println(" ISBN cannot be changed - no setIsbn() method exists");
        System.out.println("ISBN is final and immutable");

        // Test 11: Array of books (Library simulation)
        System.out.println("\n--- Test 11: Library with Multiple Books ---");
        Book[] library = new Book[3];
        library[0] = new Book("978-0073398211", "Shigley's Mechanical Engineering Design (11th Edition)",
                "Richard Budynas and Keith Nisbett");
        library[1] = new Book("978-0465050659", "The Design of Everyday Things", "Don Norman");
        library[2] = new Book("978-0134814971", "Engineering Mechanics: Statics (15th Edition)", "Russell C. Hibbeler");

        // Borrow some books
        System.out.println("\nBorrowing books from library...");
        library[0].borrowBook(14);
        library[2].borrowBook(7);

        // Display all books
        System.out.println("\n Library Inventory:");
        for (int i = 0; i < library.length; i++) {
            System.out.println((i + 1) + ".");
            library[i].displayInfo();
        }

        // Count available books
        int availableCount = 0;
        for (Book book : library) {
            if (book.isAvailable()) {
                availableCount++;
            }
        }
        System.out.println(" Available books: " + availableCount + "/" + library.length);
        System.out.println(" Borrowed books: " + (library.length - availableCount) + "/" + library.length);
    }

    // Helper method to create book safely without crashing
    private static Book createBookSafely(String isbn, String title, String author) {
        // Validate before creating
        if (!isValidISBN(isbn)) {
            System.out.println(" Failed: Invalid ISBN. Must be exactly 13 digits.");
            return null;
        }

        if (title == null || title.trim().isEmpty()) {
            System.out.println(" Failed: Title cannot be empty.");
            return null;
        }

        if (author == null || author.trim().isEmpty()) {
            System.out.println(" Failed: Author cannot be empty.");
            return null;
        }

        // All validations passed, create book
        System.out.println(" Validation passed. Creating book...");
        return new Book(isbn, title, author);
    }

    // Helper method used to validate ISBN
    private static boolean isValidISBN(String isbn) {
        if (isbn == null) {
            return false;
        }

        // Remove spaces and hyphens
        String cleanISBN = isbn.replaceAll("[\\s-]", "");

        // Check if it is exactly 13 digits
        return cleanISBN.length() == 13 && cleanISBN.matches("\\d{13}");
    }
}