package com.evecandy.Assignment3_EveCandy.OOPDesignImplimentation;

public class LibraryManager {
    public static void main(String[] args) {
        System.out.println("========== Library Management System ==========\n");

        // Test 1: Create library items (Polymorphism)
        System.out.println("--- Test 1: Creating Library Items ---");

        LibraryItem[] items = {
                new Book("B001", "Java Programming", "John Doe", "978-0123456789", "Education"),
                new DVD("D001", "Java Tutorial", "Jane Smith", 120, "PG"),
                new Magazine("M001", "Tech Monthly", "Tech Press", 15, "2024-03-01")
        };

        System.out.println(" Created " + LibraryItem.getTotalItems() + " library items\n");

        // Test 2: Display all items (Polymorphic behavior)
        System.out.println("--- Test 2: Displaying All Items ---");
        for (LibraryItem item : items) {
            item.displayDetails(); // Each shows different information!
        }

        // Test 3: Checkout items (Polymorphic behavior)
        System.out.println("--- Test 3: Checking Out Items ---");

        System.out.println("\n Checking out Book:");
        items[0].checkout("Alice Wanjiku", 14);

        System.out.println("\n Checking out DVD:");
        items[1].checkout("Bob Kimani", 7);

        System.out.println("\n Checking out Magazine:");
        items[2].checkout("Charlie Omondi", 3);

        // Test 4: Display items after checkout
        System.out.println("\n--- Test 4: Items After Checkout ---");
        for (LibraryItem item : items) {
            item.displayDetails();
        }

        // Test 5: Try to checkout already borrowed items
        System.out.println("--- Test 5: Try to Checkout Borrowed Items ---");
        items[0].checkout("David Mwangi", 14); // Should fail
        items[1].checkout("Eve Candy", 7); // Should fail

        // Test 6: Return items
        System.out.println("\n--- Test 6: Returning Items ---");

        System.out.println("\nReturning Book:");
        items[0].returnItem();

        System.out.println("\nReturning DVD:");
        items[1].returnItem();

        System.out.println("\nReturning Magazine:");
        items[2].returnItem();

        // Test 7: Overdue scenario
        System.out.println("\n--- Test 7: Overdue Scenario ---");
        Book overdueBook = new Book("B002", "Overdue Test Book", "Test Author", "978-9876543210", "Test");

        System.out.println("\nChecking out book:");
        overdueBook.checkout("Test User", 1); // Checkout for 1 day only

        System.out.println("\nChecking if overdue:");
        System.out.println("Is overdue: " + overdueBook.isOverdue());

        // overdue
        System.out.println("\n(Simulating overdue by waiting...)");
        System.out.println("In real scenario, wait until due date passes");

        // Test 8: Method Overloading (checkout with/without days)
        System.out.println("\n--- Test 8: Method Overloading ---");

        Book book2 = new Book("B003", "Another Java Book", "Author Name", "978-1111111111", "Programming");

        System.out.println("\nCheckout using default days (14 for books):");
        book2.checkout("User A");
        book2.displayDetails();

        book2.returnItem();

        System.out.println("\nCheckout using custom days:");
        book2.checkout("User B", 30);
        book2.displayDetails();

        // Test 9: Different late fees for different item types
        System.out.println("\n--- Test 9: Late Fee Comparison ---");
        System.out.println("Books: KES 5/day, DVDs: KES 20/day, Magazines: KES 2/day");

        // Test 10: Magazine archive check
        System.out.println("\n--- Test 10: Magazine Archive Check ---");
        Magazine oldMagazine = new Magazine("M002", "Old Magazine", "Publisher", 1, "2020-01-01");
        System.out.println("\nTrying to checkout old magazine:");
        oldMagazine.checkout("Test User");

        Magazine newMagazine = new Magazine("M003", "Current Magazine", "Publisher", 20, "2024-02-01");
        System.out.println("\nTrying to checkout current magazine:");
        newMagazine.checkout("Test User");

        // Test 11: Search functionality
        System.out.println("\n--- Test 11: Search by Title ---");
        searchByTitle(items, "Java");

        // Test 12: Available items count
        System.out.println("\n--- Test 12: Library Statistics ---");
        displayLibraryStats(items);

        // Test 13: Polymorphic array operations
        System.out.println("\n--- Test 13: Checkout All Available Items ---");
        checkoutAllAvailable(items, "System User");

        System.out.println("\n--- Test 14: Return All Items ---");
        returnAllItems(items);

        // Final display
        System.out.println("\n--- Final Library State ---");
        for (LibraryItem item : items) {
            System.out.println(item.getItemType() + ": '" + item.getTitle() + "' - " +
                    (item.isAvailable() ? "Available " : "Checked Out "));
        }

        System.out.println("\n========== System Summary ==========");
        System.out.println("Total Library Items: " + LibraryItem.getTotalItems());
        displayLibraryStats(items);
    }

    // Helper method: Search by title
    private static void searchByTitle(LibraryItem[] items, String keyword) {
        System.out.println("\nSearching for items containing '" + keyword + "':");
        boolean found = false;

        for (LibraryItem item : items) {
            if (item.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("  - " + item.getItemType() + ": " + item.getTitle());
                found = true;
            }
        }

        if (!found) {
            System.out.println("  No items found.");
        }
    }

    // Helper method: Display library statistics
    private static void displayLibraryStats(LibraryItem[] items) {
        int available = 0;
        int checkedOut = 0;
        int books = 0, dvds = 0, magazines = 0;

        for (LibraryItem item : items) {
            if (item.isAvailable()) {
                available++;
            } else {
                checkedOut++;
            }

            if (item instanceof Book)
                books++;
            else if (item instanceof DVD)
                dvds++;
            else if (item instanceof Magazine)
                magazines++;
        }

        System.out.println(" Library Statistics:");
        System.out.println("  Total Items: " + items.length);
        System.out.println("  Available: " + available);
        System.out.println("  Checked Out: " + checkedOut);
        System.out.println("  Books: " + books + ", DVDs: " + dvds + ", Magazines: " + magazines);
    }

    // Helper method: Checkout all available items
    private static void checkoutAllAvailable(LibraryItem[] items, String borrower) {
        for (LibraryItem item : items) {
            if (item.isAvailable()) {
                item.checkout(borrower, 7);
            }
        }
    }

    // Helper method: Return all items
    private static void returnAllItems(LibraryItem[] items) {
        for (LibraryItem item : items) {
            if (!item.isAvailable()) {
                item.returnItem();
            }
        }
    }
}
