package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String... doYourBest) throws IOException {
        List<String> engPointTypes = Arrays.asList("street", "floor", "floor_in_the_booth", "mounted");

        String[] pointTypesList = (String[]) engPointTypes.toArray();
        for (String s : pointTypesList) {
            System.out.println(s);
        }

    }

    public static Process runCmd(String[] args) {
        try {
            Runtime runtime = Runtime.getRuntime();
            return runtime.exec(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}