package ru.test;

public class Que {
    private char arr[];
    private int putloc, getloc;
    char c;

    Que(int size) {
        arr = new char[size + 1];
        putloc = getloc = 0;
    }

    void put(char ch) {
        if (putloc == arr.length - 1) {
            System.out.println("Очередь заполнена");
            return;
        }
        ++putloc;
        arr[putloc] = ch;
    }

    char get() {
        if (putloc == getloc) {
            System.out.println("Очередь пуста");
            return (char) 0;
        }
        ++getloc;
        return arr[getloc];
    }

}

class Incups {
    public static void main(String[] args) {
        int u = 5;
        Que ob = new Que(u); // arr[6]
        for (int i = 1; i < u; ++i) {
            ob.put((char) i); //
            System.out.println("Заполнено " + i);
        }

        char c = ob.get();
        System.out.println((int)ob.get());
        System.out.println("Hello");
    }
}