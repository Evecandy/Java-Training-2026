package com.evecandy.exercises.javase013.exceptionhandling.calculator;

// custom exception for calculation errors
public class CalculationException extends Exception {
    public CalculationException(String message) {
        super(message);

    }

}
