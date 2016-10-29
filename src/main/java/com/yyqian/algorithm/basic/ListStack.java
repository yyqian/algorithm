package com.yyqian.algorithm.basic;

/**
 * Created by yyqian on 2016/10/29.
 */
public class ListStack<T> {
  private class Node<K> {
    K val;
    Node<K> next;
  }
  private Node<T> sentinel = new Node<>();
  private int n = 0;
  public void push(T val) {
    Node<T> node = new Node<>();
    node.val = val;
    node.next = sentinel.next;
    sentinel.next = node;
    n++;
  }
  public T pop() {
    if (sentinel.next == null) return null;
    T val = sentinel.next.val;
    sentinel.next = sentinel.next.next;
    n--;
    return val;
  }
}
