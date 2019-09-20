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
            printer = new PrintWriter(new OutputStreamWriter(new FileOutputStream("/dev/usb/lp0"), StandardCharsets.UTF_8));
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

            System.out.println("before print raster");
            printRasterImage();
            System.out.println("after print raster");
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

    private static void printRasterImage() {
        try {
            printer.write(new char[]{0x1D, 0X76, 0X30, 0x00, 0x14, 0x03, 0x14, 0x03});
            byte[] date = getBytesFromImage("/home/artur/qrCode4.bmp");

            for (int i = 0; i < date.length; i++) {
                printer.write(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] getBytesFromImage(String path) throws IOException {
        File file = new File(path);

        FileInputStream fis = new FileInputStream(file);
        //create FileInputStream which obtains input bytes from a file in a file system
        //FileInputStream is meant for reading streams of raw bytes such as image data. For reading streams of characters, consider using FileReader.

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                //Writes to this byte array output stream
                bos.write(buf, 0, readNum);
            }
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            fis.close();
        }
        System.out.println("array len = " + bos.toByteArray().length + " " + Arrays.toString(bos.toByteArray()));
        return bos.toByteArray();
    }

    private static void selectBitImageMode() {
        printer.write(new char[]{0x1B, 0x21, 0x01, 0x14, 0x01, 0x10, 0x10, 0x10, 0x10});
    }

    private static void saveLogo(String path) {
        char[] binary = path.toCharArray();
        byte[] data = new byte[binary.length * 2];
        for (int i = 0; i < data.length; i++) {
            byte b = (byte) binary[i / 2];
            if (i % 2 == 0)
                data[i] = (byte) ((b & 0xFF00) >> 8); // MSB first
            else
                data[i] = (byte) (b & 0xFF);
        }
        printer.write(new char[]{0x1B, 0xFF, 0x01});
        for (byte b : data) {
            printer.write(b);
        }
    }

    private static void printLogo() {
        printer.write(new char[]{0x1B, 0xFA, 0x01, 0x00, 0x64, 0x00, 0xC7});
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