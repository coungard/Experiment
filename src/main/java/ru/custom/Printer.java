package ru.custom;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Printer {
    private static PrintWriter printer;
    private static String printTxt;

    public static void main(String[] args) {
        List<String> array = new ArrayList<>();
        array.add("Two roads diverged in a yellow wood,");
        array.add("And sorry I could not travel both");
        array.add("And be one traveler, long I stood");
        array.add("And looked down one as far as I could");
        array.add("To where it bent in the undergrowth.\n");
        array.add("Then took the other, as just as fair,");
        array.add("And having perhaps the better claim,");
        array.add("Because it was grassy and wanted wear;");
        array.add("Though as for that the passing there");
        array.add("Had worn them really about the same.");

        String temp = "|==============================|\n" +
                "|             ОТЧЕТ            |\n" +
                "|** 27.02.20       11:03:33  **|\n" +
                "|                              |\n" +
                "|ТЕСТ ПРЕДПРИЯТИЕ              |\n" +
                "|ЛЮБАЯ УЛИЦА 1                 |\n" +
                "|МОСКВА                        | \n" +
                "|T 19999303            B: 0112 |\n" +
                "|U 9903600181                  |\n" +
                "|                              |\n" +
                "|0052   25.02.20  15:07:07   01|\n" +
                "|*** VISA ***...9964           |\n" +
                "|53.00           (1) 308902   D|\n" +
                "|                              | \n" +
                "|0053   25.02.20  15:09:27   00|\n" +
                "|*** VISA ***...9964           |\n" +
                "|53.00           (1) 308902   D|\n" +
                "|                              |\n" +
                "|0054   25.02.20  15:17:56   01|\n" +
                "|*** VISA ***...9964           |\n" +
                "|43.00           (1) 308902   D| \n" +
                "|                              |\n" +
                "|0055   25.02.20  16:39:21   00|\n" +
                "|*** VISA ***...9964           |\n" +
                "|18.00           (1) 308902   D|\n" +
                "|                              |\n" +
                "|0056   25.02.20  16:41:35   01|\n" +
                "|*** VISA ***...9964           | \n" +
                "|71.00           (1) 308902   D|\n" +
                "|                              |\n" +
                "|0057   25.02.20  17:10:11   01|\n" +
                "|*** VISA ***...9964           |\n" +
                "|116.66          (1) 308902   D|\n" +
                "|                              |\n" +
                "|0058   26.02.20  10:36:19   01| \n" +
                "|*** VISA ***...9964           |\n" +
                "|91.66           (1) 308902   D|\n" +
                "|                              |\n" +
                "|0059   26.02.20  10:50:55   01|\n" +
                "|*** VISA ***...9964           |\n" +
                "|61.00           (1) 308902   D|\n" +
                "|                              | \n" +
                "|0060   26.02.20  10:54:27   01|\n" +
                "|*** VISA ***...9964           |\n" +
                "|291.66          (1) 308902   D|\n" +
                "|                              |\n" +
                "|0061   26.02.20  10:59:24   00|\n" +
                "|*** VISA ***...9964           |\n" +
                "|925.00          (1) 308902   D| \n" +
                "|                              |\n" +
                "|0062   26.02.20  11:01:18   00|\n" +
                "|*** VISA ***...9964           |\n" +
                "|708.33          (1) 308902   D|\n" +
                "|                              |\n" +
                "|0063   26.02.20  14:42:23   01|\n" +
                "|*** VISA ***...9964           | \n" +
                "|127.54          (1) 308902   D|\n" +
                "|                              |\n" +
                "|0064   26.02.20  15:31:48   01|\n" +
                "|*** VISA ***...9964           |\n" +
                "|68.30           (1) 308902   D|\n" +
                "|                              |\n" +
                "|ТИП ВАЛЮТЫ               РУБ. | \n" +
                "|                              |\n" +
                "|         *** VISA ***         |\n" +
                "|ПРОДАЖА                       |\n" +
                "|009      :              923.82|\n" +
                "|ОТМЕНА                        |\n" +
                "|004      :             1704.33|\n" +
                "|                              | \n" +
                "|ИТОГО                         |\n" +
                "|013      :              923.82|\n" +
                "|                              |\n" +
                "|                              |\n" +
                "|ОБЩАЯ СУММА                   |\n" +
                "|009                     923.82|\n" +
                "|                              | \n" +
                "|++++++++++++++++++++++++++++++|";
        printTxt = temp;

        print(array);
    }

    private static void print(List<String> rows) {
        try {
            printer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("/dev/usb/lp0"), "windows-1251"));
//            printer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("/dev/ttyACM0"), StandardCharsets.UTF_8));
            printer.write(0x1B); // ESC
            printer.write(0x40); // @ - initialize printer
//            printer.write(new char[]{0x1B, 0x40, 0x11});
            printer.write(new char[]{0x1B, 0x4D, 0x01}); // select character font
//            printer.write(new char[]{0x1B, 0x45, 0x01}); // bold

//            printer.write(new char[]{0x1D, 0x4C, 0x0A, 0x00}); // set left margin (отступ в 10 мм слева от границы)
            String[] parts = printTxt.split("\n");
            for (String row : parts) {
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