package com;

public class Fibonacci {

    private static int fib(int p) {
        return (p==1 || p==2 ? 1 : fib(p-1) + fib(p-2));
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 20; i++) {
            System.out.print(fib(i) + " ");
        }
    }
}
