package com.yyqian.algorithm.basic;

import java.util.Arrays;

/**
 * Created by yyqian on 2016/10/29.
 */
public class ArrayStack<T> {
  private T[] ary = (T[]) new Object[8];
  private int n = 0;
  private boolean isEmpty() {return n == 0;}
  public void resize(int cap) {
    T[] newAry = (T[]) new Object[cap];
    System.arraycopy(ary, 0, newAry, 0, n);
    ary = newAry;
  }
  public void resize2(int cap) {
    ary = Arrays.copyOf(ary, cap);
  }
  public void push(T item) {
    if (n == ary.length) resize2(2 * ary.length);
    ary[n] = item;
    n++;
  }
  public T pop() {
    if (isEmpty()) return null;
    T item = ary[--n];
    ary[n] = null;
    if (n > 0 && n == ary.length/4) resize(ary.length / 2);
    return item;
  }
}
