package com.yyqian.algorithm.search;

/**
 * Created by yyqian on 5/27/16.
 *
 * in an ordered array
 *
 * 这个算法的 put, delete, deleteMin 方法都是 O(N), 因为涉及到移动一长列数组
 * get, contains, floor, ceiling, rank 方法都是 O(lgN)
 * 总体来说, 查询复杂度是 O(lgN), 插入修改是 O(N)
 *
 * 对于一个 static table, binary search 是适用的, 因为没有增删操作, 只有初始化一次性的消耗
 */
public class BinarySearchST<K extends Comparable<K>, V> {
  private K[] keys; // keys 序列要保持有序（从小到大）
  private V[] vals; // keys[i] 对应的值应当是 vals[i]
  private int N; // 初始值为 0

  public BinarySearchST(int capacity) {
    keys = (K[]) new Comparable[capacity];
    vals = (V[]) new Object[capacity];
  }

  public int size() {
    return N;
  }

  public boolean isEmpty() {
    return N < 1;
  }

  // O(lgN)
  public V get(K key) {
    if (isEmpty()) return null;
    int i = rank(key);
    if (i < N && key.compareTo(keys[i]) == 0) return vals[i];
    else return null;
  }

  // 这个方法太慢 O(N)
  public void put(K key, V val) {
    int i = rank(key);
    // 如果 key 已经存在则更新 val
    if (i < N && key.compareTo(keys[i]) == 0) {
      vals[i] = val;
      return;
    }
    // 插入新的 key O(N), 新 key 的位置应当是在 i, 之后的序列都要右移一格, 所以速度很慢
    for (int j = N; j > i; j--) {
      keys[j] = keys[j - 1];
      vals[j] = vals[j - 1];
    }
    keys[i] = key;
    vals[i] = val;
    N++;
  }

  // 要么返回 key 所在 index, 要么返回 key 应当插入的位置
  // O(lgN)
  public int rank(K key) {
    int lo = 0;
    int hi = N - 1;
    while (lo <= hi) {
      int mid = (lo + hi) / 2;
      int cmp = key.compareTo(keys[mid]);
      if (cmp < 0) hi = mid - 1;
      else if (cmp > 0) lo = mid + 1;
      else return mid;
    }
    // If key is not in the table, rank() also returns the number of keys in the table
    // that are smaller than key.
    return lo;
  }

  // constant-time operation
  public K min() {
    return keys[0];
  }

  // constant-time operation
  public K max() {
    return keys[N - 1];
  }

  // constant-time operation
  public K select(int i) {
    return keys[i];
  }

  // O(lgN)
  public K ceiling(K key) {
    int i = rank(key);
    return keys[i];
  }
}
