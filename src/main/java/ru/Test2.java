package ru;

import java.util.ArrayList;

public class Test2 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();

        list.add("123");
        list.add("abc");
        list.add("lolol");

        list.remove(1);

        System.out.println(list);
        System.out.println("Size = " + list);
    }
}
