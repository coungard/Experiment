package edu.csv;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class CSVParser {

    public static void main(String[] args) {
        String csvFile = "csv/10953.csv";
        int count = 0;

        CSVReader reader;
        try {
            reader = new CSVReader(new FileReader(csvFile), ';', '"');
            String[] line;
            while ((line = reader.readNext()) != null) {
                count++;
                System.out.print("Строка " + count + ":\t");
                for (String s : line) {
                    System.out.print(s + "\t");
                }
                System.out.println();
            }
            System.out.println("Строк всего = " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
