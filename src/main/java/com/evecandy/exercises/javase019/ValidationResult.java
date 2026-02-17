package com.evecandy.exercises.javase019;

/**
 * Validation Result Class
 * Holds the result of a validation operation
 */
public class ValidationResult {
    private boolean valid;
    private String message;

    public ValidationResult(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}