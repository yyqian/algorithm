package com.yyqian.algorithm.search;

/**
 * Created by yyqian on 5/27/16.
 * 如果发生冲突（当前位置被占用）, 则指针右移, 直到找到空缺位置
 *
 * 这个方法在删除时很麻烦, 如果删除一个元素, 不能把它设置为 null, 因为可能会把其他元素的查找链弄断,
 * 所以必须把该元素右侧所有的值都重新插入一遍
 *
 * 这个算法的 load factor = N/M 小于 1 (不能等于 1, 否则会导致 infinite loop)
 */
public class LinearProbingHashST<K, V> {
  private int N;
  private int M = 16;
  private K[] keys;
  private V[] vals;

  public LinearProbingHashST() {
    keys = (K[]) new Object[M];
    vals = (V[]) new Object[M];
  }

  private int hash(K key) {
    return (key.hashCode() & 0x7fffffff) % M; // why & 0x7fffffff?
  }

  public void put(K key, V val) {
    int i;
    for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
      if (key.equals(keys[i])) {
        vals[i] = val;
        return;
      }
    }
    keys[i] = key;
    vals[i] = val;
    N++;
  }

  public V get(K key) {
    for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
      if (key.equals(keys[i])) {
        return vals[i];
      }
    }
    return null;
  }
}
