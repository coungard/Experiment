package edu.finalTask;

import java.util.Arrays;
import java.util.Scanner;

class bookdb {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String action = "";
        String title = "";
        String[] booksDb = {};

        System.out.println("Welcome to the book titles database.");
        System.out.println("Type \"help\" to get a description of the available commands.");
        System.out.println();

        while (!action.equals("exit")) {
            System.out.println("Please enter one of the available commands (help, add, delete, edit, show, check, sort, exit):");
            System.out.print("> ");
            action = scanner.nextLine();
            switch (action) {
                case "help":
                    System.out.println("\"add\" command - adds the title of the book to the database, provided that there is no such title yet.");
                    System.out.println("\"delete\" command - removes the book title from the database, provided that such a title exists.");
                    System.out.println("\"edit\" command - allows you to change the title of a book in the database, provided that it exists.");
                    System.out.println("\"show\" command - displays all book titles from the database.");
                    System.out.println("\"check\" command - allows you to check if the book title exists in the database.");
                    System.out.println("\"sort\" command - sorts the titles of the books in the database alphabetically.");
                    System.out.println("\"exit\" command - allows you to exit the program.");
                    System.out.println();
                    break;
                case "add":
                    System.out.println("Please enter book title:");
                    System.out.print("> ");
                    title = scanner.nextLine();
                    if (title.equals("")) {
                        System.out.println("No title has been added, entered blank.");
                        break;
                    }
                    if (isStringInArray(booksDb, title)) {
                        System.out.println("The title of the book is already in the database!");
                        break;
                    }
                    booksDb = addToArray(booksDb, title);
                    System.out.println(String.format("Book title \"%s\" has been successfully added to the database.", title));
                    break;
                case "delete":
                    System.out.println("Please enter book title to delete:");
                    System.out.print("> ");
                    title = scanner.nextLine();
                    if (!isStringInArray(booksDb, title)) {
                        System.out.println(String.format("Book with title \"%s\" not found in database.", title));
                        break;
                    }
                    booksDb = deleteFromArray(booksDb, title);
                    System.out.println(String.format("Book title \"%s\" has been removed from the database.", title));
                    break;
                case "edit":
                    System.out.println("Please enter book title to edit:");
                    System.out.print("> ");
                    title = scanner.nextLine();
                    if (isStringInArray(booksDb, title)){
                        System.out.println("Please enter new book title");
                        System.out.print("> ");
                        String newBookTitle = scanner.nextLine();
                        if (!newBookTitle.equals("")) {
                            updateArrayValue(booksDb, title, newBookTitle);
                            System.out.println("Book title updated.");
                        } else {
                            System.out.println("Book title cannot be empty!");
                        }
                    } else {
                        System.out.println(String.format("Book with title \"%s\" not found in database.", title));
                    }
                    break;
                case "show":
                    if (booksDb.length < 1) {
                        System.out.println("There are no book titles in the database.");
                    }
                    for (int i = 0; i < booksDb.length; i++) {
                        System.out.println(booksDb[i]);
                    }
                    break;
                case "check":
                    System.out.println("Please enter book title:");
                    System.out.print("> ");
                    title = scanner.nextLine();
                    if (isStringInArray(booksDb, title)) {
                        System.out.println(String.format("Book title \"%s\" found in the database", title));
                    } else {
                        System.out.println(String.format("Book title \"%s\" not found in the database", title));
                    }
                    break;
                case "sort":
                    Arrays.sort(booksDb);
                    System.out.println("Book titles are sorted.");
                    break;
                case "exit":
                    break;
                default:
                    System.out.println(String.format("Command \"%s\" not found!", action));
                    break;
            }
        }

        scanner.close();
    }

    private static String[] addToArray(String[] inputArray, String newElement) {
        int arraySize = inputArray.length;
        String[] result = new String[arraySize + 1];

        for (int i = 0; i < arraySize; i++) {
            result[i] = inputArray[i];
        }
        result[arraySize] = newElement;

        return result;
    }

    private static String[] deleteFromArray(String[] inputArray, String element) {
        String[] result = {};
        for (int i = 0; i < inputArray.length; i++) {
            if (!inputArray[i].equals(element)) {
                result = addToArray(result, inputArray[i]);
            }
        }
        return result;
    }

    private static Boolean isStringInArray(String[] inputArray, String inpuString) {
        for (String element : inputArray) {
            if (element.equals(inpuString)) {
                return true;
            }
        }

        return false;
    }

    private static void updateArrayValue(String[] inputArray, String oldValue, String newValue) {
        for (int i = 0; i < inputArray.length; i++) {
            if (inputArray[i].equals(oldValue)) {
                inputArray[i] = newValue;
                return;
            }
        }
    }

}
