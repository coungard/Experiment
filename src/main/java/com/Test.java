package com;

import java.util.HashMap;
import java.util.Map;

public class Test{
    public static void main(String[] args) {

        Map<String, Double> map = new HashMap<>();

        map.put("pi", 3.14159);
//        map.put("e", 2L);                         fail
        map.put("log(1)", new Double(0.0));
//        map.put('x', new Double(123.4));          fail
    }
}
