package com;

public class Test{

    public static int testMethod(int count, int val) {
        int res = 0;
        for (int i = 0; i < count; i++) {
            res += val;
        }
        return res;
    }
}
