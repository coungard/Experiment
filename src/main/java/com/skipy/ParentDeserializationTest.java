package com.skipy;

import java.io.*;

/**
 * ParentDeserializationTest
 *
 * @author Eugene Matyushkin aka Skipy
 * @since 05.08.2010
 */
public class ParentDeserializationTest {

    public static void main(String[] args){
        try {
            System.out.println("Creating...");
            Child c = new Child(1);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            c.field = 10;
            System.out.println("Serializing...");
            oos.writeObject(c);
            oos.flush();
            baos.flush();
            oos.close();
            baos.close();
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            System.out.println("Deserializing...");
            Child c1 = (Child)ois.readObject();
            System.out.println("c1.i="+c1.getI());
            System.out.println("c1.field="+c1.getField());
        } catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    static class Parent {
        int field;
        Parent(){
            field = 5;
            System.out.println("Parent::Constructor");
        }
        int getField() {
            return field;
        }
    }

    public static class Child extends Parent implements Serializable {
        int i;
        Child(int i) {
            this.i = i;
            System.out.println("Child::Constructor");
        }
        int getI() {
            return i;
        }
    }
}
