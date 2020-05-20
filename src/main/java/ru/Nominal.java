package ru;

import java.util.Arrays;
import java.util.Formatter;
import java.util.HashMap;
import java.util.LinkedHashMap;


public class Nominal {
    private static final String COUNTRY = "RUB";
    private String note;
    private byte[] value;
    BillTable billTable = new BillTable();

    public Nominal(byte[] value) {
        this.value = value;
    }

    public Nominal(String note) {
        this.note = note;
    }

    public byte[] getValue() {
        byte[] array = new byte[7];

        String hex = new Formatter().format("%08X", billTable.getTable().get(note)).toString();

        String nominal = inverse(hex);
        char[] country = COUNTRY.toCharArray();
        StringBuilder countryBytes = new StringBuilder();
        for (char c : country) {
            countryBytes.append(new Formatter().format("%02X", (int) c));
        }

        nominal += countryBytes;

        int temp = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = (byte) Long.parseLong(nominal.substring(temp, temp + 2), 16);
            temp = temp + 2;
        }

        return array;
    }

    private String inverse(String s) {
        if ((null == s) || (s.length() <= 1)) {
            return s;
        }
        return inverse(s.substring(2)) + s.charAt(0) + s.charAt(1);
    }

    public static class BillTable {
        private LinkedHashMap<String, Integer> table = new LinkedHashMap<>();

        {
            table.put("0.50", 50);
            table.put("1", 100);
            table.put("2", 200);
            table.put("5", 500);
            table.put("10", 1000);
            table.put("20", 2000);
            table.put("50", 5000);
            table.put("100", 10000);
            table.put("200", 20000);
            table.put("500", 50000);
            table.put("1000", 100000);
        }

        public HashMap<String, Integer> getTable() {
            return table;
        }
    }
}
