package com.yyqian.concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

public class Cache<K, V> {
    private final ConcurrentMap<K, V> cache = new ConcurrentHashMap<>();
    private final Function<K, V> func;

    public Cache(Function<K, V> func) {
        this.func = func;
    }

    public V get(K key) {
        V v = cache.get(key);
        if (v == null) { // 这儿是先检查后执行，有可能计算多次
            v = func.apply(key);
            cache.putIfAbsent(key, v);
        }
        return v;
    }
}
