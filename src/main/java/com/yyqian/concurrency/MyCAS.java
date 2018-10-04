package com.yyqian.concurrency;

public class MyCAS {
    private int val;
    public int get() {
        return val;
    }
    public synchronized int compareAndSwap(int expect, int newVal) {
        int oldVal = val;
        if (oldVal == expect) {
            val = newVal;
        }
        return oldVal;
    }
    public boolean compareAndSet(int expect, int newVal) {
        return expect == compareAndSwap(expect, newVal);
    }

    public static void main(String[] args) {
        MyCAS cas = new MyCAS();
        System.out.println(cas.get());
        System.out.println(cas.compareAndSwap(1, 12));
        System.out.println(cas.compareAndSet(1, 12));
        System.out.println(cas.compareAndSwap(0, 11));
        System.out.println(cas.get());
    }
}

