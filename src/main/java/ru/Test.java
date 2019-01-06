package ru;

public class Test {
    private static int tralala = 0;

    public static void main(String[] args) throws InterruptedException {
        doNothing();
        doTralala();

        System.out.println("end");
    }

    private static void doNothing() {
        for (int i = 0; i < 10; i++) {
            tralala = 0;
        }
    }

    private static void doTralala() throws InterruptedException {
        Thread.sleep(1000);
        tralala = 1;
    }

    private static Process runCmd(String[] args) {
        try {
            Runtime runtime = Runtime.getRuntime();
            return runtime.exec(args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}