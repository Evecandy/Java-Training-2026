package com.evecandy.exercises.javase018.JavaGenerics;

import java.util.ArrayList;
import java.util.List;

class Stack<T> {

    private List<T> items;

    public Stack() {
        this.items = new ArrayList<>();
    }

    public void push(T item) {
        items.add(item);
        System.out.println("  [PUSH] Added: " + item);
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty! Cannot pop.");
        }

        T item = items.remove(items.size() - 1);
        System.out.println("  [POP] Removed: " + item);
        return item;
    }

    // PEEK: Look at the top item without removing it
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot peek.");
        }
        return items.get(items.size() - 1);
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return items.isEmpty();
    }

    // Get stack size
    public int size() {
        return items.size();
    }

    // Display all items (bottom to top)
    public void display() {
        System.out.print("  Stack [bottom → top]: ");
        if (isEmpty()) {
            System.out.println("(empty)");
        } else {
            for (T item : items) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }
}

// ============================================================
// GENERIC QUEUE
// ============================================================
// Think of a line at a store: first person in line is the
// first person served. First In First Out (FIFO).
// ============================================================

class Queue<T> {

    private List<T> items;

    public Queue() {
        this.items = new ArrayList<>();
    }

    // ENQUEUE: Add an item to the back of the queue
    public void enqueue(T item) {
        items.add(item);
        System.out.println("  [ENQUEUE] Added: " + item);
    }

    // DEQUEUE: Remove and return the front item
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty. Cannot dequeue.");
        }
        // Remove the FIRST item (front of queue)
        T item = items.remove(0);
        System.out.println("  [DEQUEUE] Removed: " + item);
        return item;
    }

    // PEEK: Look at the front item without removing it
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty. Cannot peek.");
        }
        return items.get(0);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int size() {
        return items.size();
    }

    public void display() {
        System.out.print("  Queue [front → back]: ");
        if (isEmpty()) {
            System.out.println("(empty)");
        } else {
            for (T item : items) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }
}

// ============================================================
// GENERIC TREE NODE (for Binary Trees)
// ============================================================
// A binary tree node has:
// - A value (the data)
// - A left child (another TreeNode)
// - A right child (another TreeNode)
// ============================================================

class TreeNode<T> {

    // The data this node holds (type T)
    private T data;

    // References to child nodes (also type TreeNode<T>)
    private TreeNode<T> left;
    private TreeNode<T> right;

    // Constructor
    public TreeNode(T data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    // Getters and setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    // Check if this node is a leaf (no children)
    public boolean isLeaf() {
        return left == null && right == null;
    }

    // Print the tree (in-order traversal: left, root, right)
    public void printInOrder() {
        if (left != null) {
            left.printInOrder();
        }
        System.out.print(data + " ");
        if (right != null) {
            right.printInOrder();
        }
    }

    // Print the tree (pre-order traversal: root, left, right)
    public void printPreOrder() {
        System.out.print(data + " ");
        if (left != null) {
            left.printPreOrder();
        }
        if (right != null) {
            right.printPreOrder();
        }
    }

    // Get tree height
    public int height() {
        if (isLeaf()) {
            return 0;
        }
        int leftHeight = (left != null) ? left.height() : -1;
        int rightHeight = (right != null) ? right.height() : -1;
        return 1 + Math.max(leftHeight, rightHeight);
    }
}

// ============================================================
// CUSTOM CLASS for testing
// ============================================================

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}

// ============================================================
// MAIN: Test all generic data structures
// ============================================================

public class GenericDataStructures {

    public static void main(String[] args) {

        System.out.println("========== EXERCISE 1: Generic Data Structures ==========\n");

        // --------------------------------------------------------
        // TEST 1: Stack with Integers
        // --------------------------------------------------------
        System.out.println("--- Test 1: Stack<Integer> ---");
        Stack<Integer> numberStack = new Stack<>();

        numberStack.push(10);
        numberStack.push(20);
        numberStack.push(30);
        numberStack.display();

        System.out.println("  Top item (peek): " + numberStack.peek());
        numberStack.pop();
        numberStack.pop();
        numberStack.display();
        System.out.println();

        // --------------------------------------------------------
        // TEST 2: Stack with Strings
        // --------------------------------------------------------
        System.out.println("--- Test 2: Stack<String> ---");
        Stack<String> wordStack = new Stack<>();

        wordStack.push("Hello");
        wordStack.push("World");
        wordStack.push("Java");
        wordStack.display();

        String top = wordStack.pop();
        System.out.println("  Popped word: " + top);
        wordStack.display();
        System.out.println();

        // --------------------------------------------------------
        // TEST 3: Queue with Integers
        // --------------------------------------------------------
        System.out.println("--- Test 3: Queue<Integer> ---");
        Queue<Integer> numberQueue = new Queue<>();

        numberQueue.enqueue(100);
        numberQueue.enqueue(200);
        numberQueue.enqueue(300);
        numberQueue.display();

        System.out.println("  Front item (peek): " + numberQueue.peek());
        numberQueue.dequeue();
        numberQueue.dequeue();
        numberQueue.display();
        System.out.println();

        // --------------------------------------------------------
        // TEST 4: Queue with Strings
        // --------------------------------------------------------
        System.out.println("--- Test 4: Queue<String> ---");
        Queue<String> taskQueue = new Queue<>();

        taskQueue.enqueue("Task A");
        taskQueue.enqueue("Task B");
        taskQueue.enqueue("Task C");
        taskQueue.display();

        String firstTask = taskQueue.dequeue();
        System.out.println("  Processing: " + firstTask);
        taskQueue.display();
        System.out.println();

        // --------------------------------------------------------
        // TEST 5: Stack with custom Person objects
        // --------------------------------------------------------
        System.out.println("--- Test 5: Stack<Person> ---");
        Stack<Person> personStack = new Stack<>();

        personStack.push(new Person("Alice", 25));
        personStack.push(new Person("Bob", 30));
        personStack.push(new Person("Charlie", 35));
        personStack.display();

        Person lastPerson = personStack.pop();
        System.out.println("  Removed person: " + lastPerson);
        personStack.display();
        System.out.println();

        // --------------------------------------------------------
        // TEST 6: Binary Tree with Integers
        // --------------------------------------------------------
        System.out.println("--- Test 6: TreeNode<Integer> ---");

        // 50
        // / \
        // 30 70
        // / \ / \
        // 20 40 60 80

        TreeNode<Integer> root = new TreeNode<>(50);
        root.setLeft(new TreeNode<>(30));
        root.setRight(new TreeNode<>(70));
        root.getLeft().setLeft(new TreeNode<>(20));
        root.getLeft().setRight(new TreeNode<>(40));
        root.getRight().setLeft(new TreeNode<>(60));
        root.getRight().setRight(new TreeNode<>(80));

        System.out.print("  In-order traversal: ");
        root.printInOrder();
        System.out.println();

        System.out.print("  Pre-order traversal: ");
        root.printPreOrder();
        System.out.println();

        System.out.println("  Tree height: " + root.height());
        System.out.println();

        // --------------------------------------------------------
        // TEST 7: Binary Tree with Strings
        // --------------------------------------------------------
        System.out.println("--- Test 7: TreeNode<String> ---");

        // "Dog"
        // / \
        // "Cat" "Elephant"
        // /
        // "Ant"

        TreeNode<String> animalRoot = new TreeNode<>("Dog");
        animalRoot.setLeft(new TreeNode<>("Cat"));
        animalRoot.setRight(new TreeNode<>("Elephant"));
        animalRoot.getLeft().setLeft(new TreeNode<>("Ant"));

        System.out.print("  In-order traversal: ");
        animalRoot.printInOrder();
        System.out.println();

        System.out.println("  Root is leaf? " + animalRoot.isLeaf());
        System.out.println("  'Ant' is leaf? " + animalRoot.getLeft().getLeft().isLeaf());
        System.out.println();

        // --------------------------------------------------------
        // TEST 8: Binary Tree with custom Person objects
        // --------------------------------------------------------
        System.out.println("--- Test 8: TreeNode<Person> ---");

        TreeNode<Person> personRoot = new TreeNode<>(new Person("Manager", 45));
        personRoot.setLeft(new TreeNode<>(new Person("Developer", 28)));
        personRoot.setRight(new TreeNode<>(new Person("Designer", 32)));

        System.out.print("  Pre-order traversal: ");
        personRoot.printPreOrder();
        System.out.println();

        System.out.println("\n========== ALL TESTS COMPLETE ==========");
    }
}
