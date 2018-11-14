package com;

public class Test {
    public static void main(String... doYourBest) {
        String text = "0.123.153";
        String[] parts = text.split(".");

        for (int i = 0; i < parts.length; i++) {
            System.out.println(i);
        }
    }

    private static byte[] fromASCIItoByteArray(String text) {
        byte[] res = new byte[text.length()];
        char[] ascii = text.toCharArray();

        for (int i = 0; i < text.length(); i++) {
            res[i] = (byte) (ascii[i] & 0xFF00 >> 8);
        }
        return res;
    }
}