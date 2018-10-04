package com.yyqian.concurrency;

public class ThreadLocalTest {
    public static void main(String[] args) {
        ThreadLocal<Integer> tl0 = new ThreadLocal<>();
        ThreadLocal<Integer> tl1 = new ThreadLocal<>();
        tl0.set(66);
        tl1.set(99);
        Thread currentThread = Thread.currentThread();
        System.out.println(tl0.get());
        System.out.println(tl1.get());
    }
}
