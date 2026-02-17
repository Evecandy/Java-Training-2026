package com.evecandy.exercises.javase019;

/**
 * Custom Functional Interfaces for Practice Exercise 1
 * Each interface has exactly one abstract method (SAM - Single Abstract Method)
 */

// 1. Calculator - Performs mathematical operations
@FunctionalInterface
interface Calculator {
    double compute(double a, double b);
}

// 2. Validator - Validates input data
@FunctionalInterface
interface Validator {
    boolean isValid(double value);
}

// 3. Transformer - Transforms data from one format to another
@FunctionalInterface
interface Transformer {
    String convert(double value);
}

// 4. Logger - Logs messages
@FunctionalInterface
interface Logger {
    void print(String message);
}
