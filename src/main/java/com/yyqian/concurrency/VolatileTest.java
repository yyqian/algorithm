package com.yyqian.concurrency;

public class VolatileTest {
    private static volatile boolean isDone = false;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Child will wait");
            while ((!isDone)) {  // with out volatile, child's isDone is always false
                // busy waiting
            }
            System.out.println("Child is done");
        });
        thread.start();
        Thread.sleep(1000L);
        isDone = true;
        Thread.sleep(1000L);
    }
}
