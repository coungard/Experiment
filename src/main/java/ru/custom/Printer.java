package ru.custom;

import ru.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Printer {
    private static PrintWriter printer;

    public static void main(String[] args) {
        List<String> array = new ArrayList<>();
        array.add("Two roadds diverged in a yellow wood,");
        array.add("And sorry I could not travel both");
        array.add("And be one traveler, long I stood");
        array.add("And looked down one as far as I could");
        array.add("To where it bent in the undergrowth.\n");
        array.add("Then took the other, as just as fair,");
        array.add("And having perhaps the better claim,");
        array.add("Because it was grassy and wanted wear;");
        array.add("Though as for that the passing there");
        array.add("Had worn them really about the same.");

        print(array);
    }

    private static void print(List<String> rows) {
        try {
//            printer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("/dev/usb/lp0"), StandardCharsets.UTF_8));
            printer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("COM1"), StandardCharsets.UTF_8));
            printer.write(0x1B); // ESC
            printer.write(0x40); // @ - initialize printer
//            printer.write(new char[]{0x1B, 0x4D, 0x01}); // select character font
//            printer.write(new char[]{0x1B, 0x45, 0x01}); // bold

            printer.write(new char[]{0x1D, 0x4C, 0x0A, 0x00}); // set left margin (отступ в 10 мм слева от границы)
            for (String row : rows) {
                printer.write(row);
                lineFeed();
            }
            lineBreak(3);

            lineFeed();

            lineBreak(3);

            printer.write(0x0D); // Carriage return

            formFeed();
//            formFeedTG(); // 2 разные реализации для моделей VKP80 и TG (1 из которых выполнится, другая проигнорируется)

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            printer.close();
        }
    }

    private static void lineFeed() {
        printer.write(0x0A);
    }

    private static void formFeed() {
        printer.write(0x0C);
    }

    private static void formFeedTG() {
        printer.write(0x1B);
        printer.write(0x69);
    }

    private static void lineBreak(int lines) {
        for (int i = 0; i < lines; i++) {
            printer.write(0x0A);
        }
    }
}