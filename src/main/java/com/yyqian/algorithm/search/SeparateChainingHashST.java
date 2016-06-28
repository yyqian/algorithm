package com.yyqian.algorithm.search;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yyqian on 5/27/16.
 * 每一个 hashtable 的元素都是一个 SequentialSearchST, 冲突的元素都放在同一个 SequentialSearchST 中
 *
 * 这个算法的 load factor = N/M 大于 1
 *
 * HashMap 就是用的这个实现
 */
public class SeparateChainingHashST<K, V> {
  private int N;
  private int M;
  private Map<K, V>[] st;

  public SeparateChainingHashST(int M) {
    this.M = M;
    st = (HashMap<K, V>[]) new Object[M];
    for (int i = 0; i < M; i++) {
      st[i] = new HashMap<>();
    }
  }

  private int hash(K key) {
    return (key.hashCode() & 0x7fffffff) % M; // why & 0x7fffffff?
  }

  public V get(K key) {
    return st[hash(key)].get(key);
  }

  public void put(K key, V val) {
    st[hash(key)].put(key, val);
  }

}
