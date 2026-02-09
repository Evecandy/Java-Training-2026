package com.evecandy.exercises.javase017.comparableAndComparator;

import java.util.Comparator;

public class Product implements Comparable<Product> {
    private int id;
    private String name;
    private String category;
    private double price;
    private double rating; // 0.0 to 5.0

    public Product(int id, String name, String category, double price, double rating) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.rating = rating;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        return rating;
    }

    // - Part 1: Comparable Implementation - Defines "Natural Ordering" by Product
    @Override
    public int compareTo(Product other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Price: $%.2f, ⭐ %.1f",
                id, name, price, rating);
    }

    // Part 2: Multiple Comparators defined as static fields

    // Sort by Price (Low to High)
    public static final Comparator<Product> BY_PRICE = Comparator.comparing(Product::getPrice);

    // Sort by Rating (High to Low) - utilizing reverseOrder
    public static final Comparator<Product> BY_RATING_DESC = Comparator.comparing(Product::getRating,
            Comparator.reverseOrder());
}