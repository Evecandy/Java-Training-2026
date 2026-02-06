package com.evecandy.exercises.java016.HashsetvsLinkedHashset;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class UniqueWordCounter {

    // METHOD 1: Count unique words using HashSet

    public static Set<String> countUniqueWordsHashSet(String text) {
        // HashSet - an unordered collection that automatically prevents duplicates
        Set<String> uniqueWords = new HashSet<>();

        // Split text into words
        String[] words = text.split("\\s+"); // \\s+ breaks the text wherever there's whitespace (spaces, newlines)

        for (String word : words) {
            // Clean the word (remove punctuation, convert to lowercase)
            String cleanWord = cleanWord(word);

            if (!cleanWord.isEmpty()) {
                uniqueWords.add(cleanWord);
            }

        }

        return uniqueWords;  //return is for giving data back to whoever called the method.
    }

    // METHOD 2: Count unique words using LinkedHashSet

    public static Set<String> countUniqueWordsLinkedHashSet(String text) {
        // LinkedHashSet - maintains insertion order
        Set<String> uniqueWords = new LinkedHashSet<>();

        String[] words = text.split("\\s+");

        for (String word : words) {
            String cleanWord = cleanWord(word);
            if (!cleanWord.isEmpty()) {
                uniqueWords.add(cleanWord);
            }
        }

        return uniqueWords;
    }

    // METHOD 3: Count unique words using TreeSet

    // TreeSet = Automatically sorts words alphabetically (A-Z).

    public static Set<String> countUniqueWordsTreeSet(String text) {
        // TreeSet - automatically sorted
        Set<String> uniqueWords = new TreeSet<>();

        String[] words = text.split("\\s+");

        for (String word : words) {
            String cleanWord = cleanWord(word);
            if (!cleanWord.isEmpty()) {
                uniqueWords.add(cleanWord);
            }
        }

        return uniqueWords;  
    }

    // Removes punctuation and converts to lowercase

    public static String cleanWord(String word) {
        // Remove all punctuation and convert to lowercase
        // removes all punctuation
        return word.replaceAll("\\p{Punct}", "").toLowerCase();
    }

    public static void displayResults(Set<String> uniqueWords, String setType) {
        System.out.println("\n========== " + setType + " ==========");
        System.out.println("Unique words count: " + uniqueWords.size());
        System.out.println("\nUnique words:");

        int count = 1;
        for (String word : uniqueWords) {
            System.out.println("  " + count + ". " + word);
            count++;
        }

        System.out.println("=======================================\n");
    }

    public static void displayComparison(String text) {
        System.out.println("\n========== COMPARISON OF SET TYPES ==========");
        System.out.println("Original text: \"" + text + "\"");
        System.out.println();

        Set<String> hashSet = countUniqueWordsHashSet(text);
        Set<String> linkedHashSet = countUniqueWordsLinkedHashSet(text);
        Set<String> treeSet = countUniqueWordsTreeSet(text);

        System.out.println("┌─────────────────┬─────────────────┬─────────────────┬─────────────────┐");
        System.out.println("│ Feature         │ HashSet         │ LinkedHashSet   │ TreeSet         │");
        System.out.println("├─────────────────┼─────────────────┼─────────────────┼─────────────────┤");
        System.out.println("│ Order           │ Random/No order │ Insertion order │ Sorted (A-Z)    │");
        System.out.println("│ Performance     │ O(1) - Fastest  │ O(1) - Fast     │ O(log n) - Slow │");
        System.out.println("│ Allows null     │ Yes (1 null)    │ Yes (1 null)    │ No              │");
        System.out.println("│ Memory          │ Low             │ Medium          │ Medium          │");
        System.out.println("└─────────────────┴─────────────────┴─────────────────┴─────────────────┘");
        System.out.println();

        // Show the actual order
        System.out.println("HashSet order (random):");
        System.out.println("  " + hashSet);

        System.out.println("\nLinkedHashSet order (insertion order):");
        System.out.println("  " + linkedHashSet);

        System.out.println("\nTreeSet order (alphabetical):");
        System.out.println("  " + treeSet);

        System.out.println("\n=============================================\n");
    }

    // ANOTHER FEATURE: Find word frequency

    // Although Set doesn't track frequency, we can demonstrate
    // why you'd use Map for this instead

    /**
     * Shows what happens when you try to add duplicates to a Set.
     * Demonstrates that Set automatically ignores duplicates.
     **/
    public static void demonstrateDuplicateHandling() {
        System.out.println("========== DUPLICATE HANDLING DEMO ==========\n");

        Set<String> words = new HashSet<>();

        System.out.println("Adding words to HashSet:");

        // Trying to add "hello" multiple times
        System.out.println("  Adding 'hello' (1st time): " + words.add("hello")); // true
        System.out.println("  Adding 'world': " + words.add("world")); // true
        System.out.println("  Adding 'hello' (2nd time): " + words.add("hello")); // false - duplicate!
        System.out.println("  Adding 'hello' (3rd time): " + words.add("hello")); // false - duplicate!
        System.out.println("  Adding 'java': " + words.add("java")); // true

        System.out.println("\nFinal Set contents: " + words);
        System.out.println("Final Set size: " + words.size());

        System.out.println("\nKEY POINT:");
        System.out.println("  - add() returns TRUE if element was added (new)");
        System.out.println("  - add() returns FALSE if element already exists (duplicate)");
        System.out.println("  - Set AUTOMATICALLY prevents duplicates");

        System.out.println("\n=============================================\n");
    }

    public static void main(String[] args) {

        System.out.println("========== UNIQUE WORD COUNTER ==========\n");

        // TEST 1: Basic example from requirements - duplicate removal and counting
        // unique words

        System.out.println("--- Test 1: Basic Example ---");
        String text1 = "hello world hello java world programming";

        Set<String> unique1 = countUniqueWordsHashSet(text1);

        System.out.println("Input: \"" + text1 + "\"");
        System.out.println("Unique words: " + unique1.size());
        System.out.println("Words: " + unique1);
        System.out.println();

        // TEST 2: Text with punctuation

        System.out.println("--- Test 2: Handling Punctuation ---");
        String text2 = "Hello, World! Hello Java. World? Programming!";

        Set<String> unique2 = countUniqueWordsLinkedHashSet(text2);

        System.out.println("Input: \"" + text2 + "\"");
        System.out.println("Unique words: " + unique2.size());
        System.out.println("Words (insertion order): " + unique2);
        System.out.println();

        // TEST 3: Case sensitivity handling

        System.out.println("--- Test 3: Case Sensitivity ---");
        String text3 = "Java JAVA java JaVa";

        Set<String> unique3 = countUniqueWordsTreeSet(text3);

        System.out.println("Input: \"" + text3 + "\"");
        System.out.println("All treated as same word (case-insensitive)");
        System.out.println("Unique words: " + unique3.size());
        System.out.println("Words: " + unique3);
        System.out.println();

        // TEST 4: Compare all three Set types

        System.out.println("--- Test 4: HashSet vs LinkedHashSet vs TreeSet ---");
        String text4 = "zebra apple mango banana apple zebra cherry banana";

        Set<String> hashSet = countUniqueWordsHashSet(text4);
        Set<String> linkedHashSet = countUniqueWordsLinkedHashSet(text4);
        Set<String> treeSet = countUniqueWordsTreeSet(text4);

        displayResults(hashSet, "HashSet (Random Order)");
        displayResults(linkedHashSet, "LinkedHashSet (Insertion Order)");
        displayResults(treeSet, "TreeSet (Sorted Order)");

        // TEST 5: Detailed comparison

        System.out.println("--- Test 5: Detailed Comparison ---");
        displayComparison(text4);

        // TEST 6: Duplicate handling demonstration

        System.out.println("--- Test 6: How Set Handles Duplicates ---");
        demonstrateDuplicateHandling();

        // TEST 7: Realistic example - paragraph

        System.out.println("--- Test 7: Real Paragraph ---");
        String paragraph = "Java is a programming language. " +
                "Java is object-oriented. " +
                "Programming in Java is fun. " +
                "Java runs on many platforms.";

        Set<String> uniqueParagraph = countUniqueWordsTreeSet(paragraph);

        System.out.println("Input paragraph:");
        System.out.println("  \"" + paragraph + "\"");
        System.out.println("\nUnique words: " + uniqueParagraph.size());
        System.out.println("Words (sorted):");

        int index = 1;
        for (String word : uniqueParagraph) {
            System.out.println("  " + index + ". " + word);
            index++;
        }
        System.out.println();

        // TEST 8: Set operations(refer to venn diagram)

        System.out.println("--- Test 8: Set Operations ---");

        Set<String> set1 = new HashSet<>(Arrays.asList("java", "python", "javascript"));
        Set<String> set2 = new HashSet<>(Arrays.asList("python", "ruby", "java"));

        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);

        // Union (all elements from both)
        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("\nUnion (set1 ∪ set2): " + union);

        // Intersection (only common elements)
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection (set1 ∩ set2): " + intersection);

        // Difference (in set1 but NOT in set2)
        Set<String> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Difference (set1 - set2): " + difference);

        System.out.println();

        // TEST 9: Interactive mode

        System.out.println("--- Test 9: Interactive Mode ---");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your text: ");
        String userText = scanner.nextLine();

        Set<String> userUnique = countUniqueWordsHashSet(userText);

        System.out.println("\nYour unique words: " + userUnique.size());
        System.out.println("Words: " + userUnique);

        scanner.close();

        // Summary

        System.out.println("========== SUMMARY ==========");
        System.out.println("\nWhen to use which Set:");
        System.out.println("   HashSet - When you need speed, don't care about order");
        System.out.println("   LinkedHashSet - Need insertion order maintained");
        System.out.println("   TreeSet    -   Need elements sorted automatically");
        System.out.println("\nKey Points:");
        System.out.println("   Set automatically removes duplicates");
        System.out.println("   Set does NOT maintain insertion order (except LinkedHashSet)");
        System.out.println("   Set does NOT allow duplicate elements");
        System.out.println("   TreeSet does NOT allow null elements");
        System.out.println("   HashSet and LinkedHashSet allow ONE null element");

        System.out.println("\n========== AUF WIEDERSEHEN ==========");
    }
}
