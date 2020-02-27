package ru.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

class Generic {

    public static void main(String[] args) {
        SomeType<String> st = new SomeType<>();
        List<String> list = Arrays.asList("test");
        st.test(list);
    }

    public static class SomeType<T> {
        public <E> void test(Collection<E> collection) {
            for (E element : collection) {
                System.out.println(element);
            }
        }

        public void test(List<Integer> collection) {
            for (Integer element : collection) {
                System.out.println(element);
            }
        }
    }
}