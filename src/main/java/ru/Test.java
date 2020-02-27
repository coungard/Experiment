package ru;

import java.util.Formatter;

public class Test {
    private static String message = "|U 9903600181                  |\n" +
            "|                              |\n" +
            "|                              |\n" +
            "|                              |\n" +
            "|          ИHКАССАЦИЯ          |\n" +
            "|            УСПЕШHА           | ";

    public static void main(String[] args) {

        double kot = 2.00;
        String res = new Formatter().format("%.2f", kot).toString().replace(",", ".");
        System.out.println(res);

//        if (message.contains("ИНКАССАЦИЯ") && message.contains("УСПЕШНА")) {
//            System.out.println("found");
//        }
//        if (message.contains("ИНКАССАЦИЯ")) {
//            System.out.println("found encash");
//        } else {
//            System.out.println("no found encash");
//        }
//        if (message.contains("УСПЕШНА")) {
//            System.out.println("found success");
//        } else {
//            System.out.println("no found success");
//        }
    }
}

