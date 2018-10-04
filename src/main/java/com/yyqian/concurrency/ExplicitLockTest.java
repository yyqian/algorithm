package com.yyqian.concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ExplicitLockTest {
    private static final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static final Lock r = readWriteLock.readLock();
    private static final Lock w = readWriteLock.writeLock();
    private static final Map<String, Object> map = new HashMap<>();

    private static Object get(String key) {
        r.lock();
        try {
            return map.get(key);
        } finally {
            r.unlock();
        }
    }

    private static void put(String key, Object val) {
        w.lock();
        try {
            map.put(key, val);
        } finally {
            w.unlock();
        }
    }

    public static void main(String[] args) {
        map.put("a", 1);
        System.out.println(map.get("a"));
    }
}
