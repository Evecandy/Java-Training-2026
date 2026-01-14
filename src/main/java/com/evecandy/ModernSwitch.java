package com.evecandy;

public class ModernSwitch {
    public static void main(String[] args) {
        int day = 7;
        String dayName = switch (day) {
            case 1 -> "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            default -> "Weekend";

        };
        System.out.println("Day " + day + " is " + dayName);
    }
}
