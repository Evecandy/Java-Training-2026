package com.evecandy;

public class SwitchBlocks {
    public static void main(String[] args) {
        int score = 85;
        String result = switch (score) {
            case 100 -> "Perfect!";
            case 90, 91, 92, 93, 94, 95, 96, 97, 98, 99 -> "Almost perfect!";
            default -> {
                if (score >= 70) {
                    yield "Passing grade";
                } else {
                    yield "Needs improvement";
                }
            }
        };
        System.out.println("Result: " + result);
    }
}
// Use "yield" to return a value from a switch block