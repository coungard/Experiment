package ru;

public class Test {

    public static void main(String[] args) {
        String s1 = "12";
        String s2 = "text";
        String s3 = "alo";
        String s4 = "22222";


//        isDigit(s1);
//        isDigit(s2);
//        isDigit(s3);
//        isDigit(s4);
    }

    private static boolean isDigit(String text) {
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}


// String 12 - is digit; String text = is not digit; ...
