package ru;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
        boolean serverError = true;
        boolean cashError = true;
        boolean coinError = false;

        if (!serverError && !(cashError && coinError)) {
            System.out.println("unblock");
        } else {
            if (serverError) {

            }
        }
    }

    private static boolean getBool() {
        Random random = new Random();
        int x = random.nextInt(10);

        return x % 2 == 0;
    }
}