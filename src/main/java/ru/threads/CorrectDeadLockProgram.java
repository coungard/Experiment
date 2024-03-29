package ru.threads;

public class CorrectDeadLockProgram {
    public static void main(String[] args) throws Exception {
        String resourceA = "resA";
        String resourceB = "resB";
        Thread threadLockingResourceAFirst = new Thread(new DeadlockRunnable(resourceA, resourceB));
        Thread threadLockingResourceBFirst = new Thread(new DeadlockRunnable(resourceA, resourceB));
        threadLockingResourceAFirst.start();
        Thread.sleep(500);
        threadLockingResourceBFirst.start();
    }

    private static class DeadlockRunnable implements Runnable {
        private final Object firstResource;
        private final Object secondResource;

        public DeadlockRunnable(Object firstResource, Object secondResource) {
            this.firstResource = firstResource;
            this.secondResource = secondResource;
        }

        @Override
        public void run() {
            try {
                synchronized (firstResource) {
                    printLockedResource(firstResource);
                    Thread.sleep(1000);
                    synchronized (secondResource) {
                        printLockedResource(secondResource);
                    }
                }
            } catch (InterruptedException e) {
                System.out.println("Exception occurred: " + e);
            }
        }

        private static void printLockedResource(Object resource) {
            System.out.println(Thread.currentThread().getName() + ": locked resource -> " + resource);
        }
    }
}
