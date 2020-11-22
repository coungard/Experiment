package edu.finalTask;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Финальное техническое задание:
 * Необходимо реализовать базу знаний книг (не обязательно книг).
 * Пользователь вводит название книги, данные сохраняются в массив.
 * <p>
 * Будет небольшое меню действий:
 * 1. Добавить название книги.
 * 2. Удалить книгу по названию.
 * 3. Редактировать название книги.
 * 4. Показать все книги.
 * 5. Проверить книгу по названию, есть ли она в базе.
 * 6. Сортировка книг (по желанию).
 * 7. Выйти из программы.
 * <p>
 * Критерии приемки:
 * - Использовать только массивы, циклы, примитивные типы, условные операторы.
 * - Не использовать регулярные выражения и Stream API.
 * - Программа должна быть на английском.
 * - Программа должна быть понятной для человека, который видит ее впервые .
 * - Программа должна быть отказоустойчивой.
 */
public class Library {
    public static String[] books = new String[100];

    public static void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of book: ");

        String name = scanner.nextLine();
        System.out.println("name = " + name);

        for (int i = 0; i < books.length; i++) {
            if (books[i] == null) {
                books[i] = name;
                break;
            }
        }
        System.out.println(Arrays.toString(books));
    }

    public static void show() {

    }

    public static void main(String[] args) {
        label:
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Menu: \n" +
                    "1. Add book \n" +
                    "2. Delete book \n" +
                    "3. Edit name of book \n" +
                    "4. Show all book \n" +
                    "5. Find book \n" +
                    "6. Sorting book \n" +
                    "7. Exit");

            int choose = scanner.nextInt();

            switch (choose) {
                case 1:
                    add();
                    break;
                default:
                    break label;
            }
        }
    }
}
