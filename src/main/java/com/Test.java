package com;

public class Test {

    public static void main(String[] args) {
        System.out.println("Integer to binary:");
        for (int i = 0; i < 100; i++) {
            String res = Integer.toBinaryString(i);
            System.out.print(i + " = " + res + "  ");
            if (i % 10 == 0 && i != 0)
                System.out.println();
        }

        System.out.println("\n\nInteger to hex:");
        for (int i = 0; i < 100; i++) {
            String res = Integer.toHexString(i);
            System.out.print(i + " = 0x" + res.toUpperCase() + "  ");
            if (i % 10 == 0 && i != 0)
                System.out.println();
        }


        for (int i = 0; i < 100; i++) {
            int res = (i & 0x20);
            System.out.println("(" + i + ")" + Integer.toBinaryString(i) + " & 0x20 (100000) = " + res);
        }
    }
}