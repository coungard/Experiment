import java.math.BigDecimal;

import static java.math.BigDecimal.*;

class A {
    public A() {
        System.out.println("A");
    }
}

class B {
    public B() {
        System.out.println("B");
    }
}

class C {
    public C() {
        System.out.println("C");
    }
}

public class Test extends C{
    private A objA = new A();
    private static B objB = new B();

    public Test() {
        System.out.println("D");
    }

    public static void main(String[] args) {
        new Test();
    }
}
