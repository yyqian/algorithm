package com.yyqian.algorithm.search;

/**
 * Created by yyqian on 5/27/16.
 *
 * in an unordered linked list
 *
 * 这个算法的查询, 插入都是 O(N) 的复杂度, 效率很低
 */
public class SequentialSearchST<K, V> {

  private Node first;

  private class Node {
    K key;
    V val;
    Node next;
    public Node(K key, V val, Node next) {
      this.key = key;
      this.val = val;
      this.next = next;
    }
  }

  // O(N)
  public V get(K key) {
    for (Node x = first; x != null; x = x.next) {
      if (x.key.equals(key)) return x.val;
    }
    return null;
  }

  // O(N)
  public void put(K key, V val) {
    for (Node x = first; x != null; x = x.next) {
      if (x.key.equals(key)) {
        x.val = val;
        return;
      }
    }
    first = new Node(key, val, first);
  }
}
