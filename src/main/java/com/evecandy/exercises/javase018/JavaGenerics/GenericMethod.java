package com.evecandy.exercises.javase018.JavaGenerics;

public class GenericMethod {
    public static <T> void printArray(T[] inputArray) {
        for (T element : inputArray) {
            System.out.println(element);

        }

    }

    public static void main(String[] args) {
        Integer[] intArray = { 1, 2, 3 };
        String[] stringArray = { "Hello", "World" };

        printArray(intArray);
        printArray(stringArray);

    }
}
