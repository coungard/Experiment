package com;

public class Test{

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }

    private static long fibonacci(int count) {
        if (count == 1 || count == 2) {
            return 1;
        } else {
            return fibonacci(count - 1) + fibonacci(count - 2);
        }
    }
}
