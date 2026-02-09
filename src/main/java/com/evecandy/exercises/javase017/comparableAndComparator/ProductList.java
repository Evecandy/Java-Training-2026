package com.evecandy.exercises.javase017.comparableAndComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductList {

    public static void main(String[] args) {
        // Create Catalog
        List<Product> catalog = new ArrayList<>();
        catalog.add(new Product(101, "Laptop", "Electronics", 1200.00, 4.5));
        catalog.add(new Product(105, "Mouse", "Electronics", 25.00, 4.2));
        catalog.add(new Product(102, "Desk", "Furniture", 150.00, 4.0));
        catalog.add(new Product(104, "Chair", "Furniture", 85.00, 3.8));
        catalog.add(new Product(103, "Monitor", "Electronics", 300.00, 4.6));
        catalog.add(new Product(106, "Keyboard", "Electronics", 45.00, 4.7));

        // Test 1: Natural Order
        System.out.println(" 1. Natural Order (By ID) ");
        Collections.sort(catalog);
        printCatalog(catalog);

        // Test 2: Custom Order
        System.out.println("\n 2. Custom Order (By Price) ");
        catalog.sort(Product.BY_PRICE);
        printCatalog(catalog);

        // Test 3: Complex Criteria
        System.out.println("\n- 3. Complex (Category, then Rating Descending) ");
        Comparator<Product> complexSort = Comparator
                .comparing(Product::getCategory)
                .thenComparing(Product::getRating, Comparator.reverseOrder())
                .thenComparing(Product::getPrice);

        catalog.sort(complexSort);
        printCatalog(catalog);
    }

    private static void printCatalog(List<Product> products) {
        for (Product p : products) {
            System.out.println(p);
        }
    }

}
