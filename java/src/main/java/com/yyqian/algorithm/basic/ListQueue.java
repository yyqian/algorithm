package com.yyqian.algorithm.basic;

/**
 * Created by yyqian on 2016/10/29.
 */
public class ListQueue<T> {
  private class Node<K> {
    K val;
    Node<K> next;
  }
  private Node<T> sentinel = new Node<>();
  private Node<T> end = sentinel;
  private int n = 0;
  public void offer(T val) {
    Node<T> last = new Node<>();
    last.val = val;
    last.next = null;
    end.next = last;
    end = last;
    n++;
  }
  public T poll() {
    if (isEmpty()) return null;
    T val = sentinel.next.val;
    sentinel.next = sentinel.next.next;
    if (isEmpty()) end = sentinel;
    n--;
    return val;
  }
  public boolean isEmpty() {
    return sentinel.next == null;
  }
}
