package com.yyqian.algorithm.string.sort;

/**
 * Created by yyqian on 6/14/16.
 * 时间复杂度:
 * N 个随机字符串, 用了 R 个字符, 时间复杂度: 介于 N 和 NW 之间
 *
 * 空间复杂度为: W + logN（W 是平均的字符串长度）
 *
 * 这个算法适用于普遍情况
 */
public class Quick3string {
  private static int charAt(String s, int d) {
    if (d < s.length()) {
      return s.charAt(d);
    } else {
      return -1;
    }
  }
  private static void sort(String[] a) {
    sort(a, 0, a.length - 1, 0);
  }

  private static void sort(String[] a, int lo, int hi, int d) {
    if (hi <= lo) {
      return;
    }
    int v = charAt(a[lo], d);
    int lt = lo;
    int gt = hi;
    int i = lo + 1;
    while (i <= hi) {
      int t = charAt(a[i], d);
      if (t < v) exch(a, i++, lt++);
      else if (t > v) exch(a, i, gt--);
      else i++;
    }
    // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]
    // d 位置还是乱的
    sort(a, lo, lt-1, d);
    // 如果 v 小于 0, 说明现在以及之后都没字符了, 也就不用继续比较了。
    if (v >= 0) sort(a, lt, gt, d + 1);
    sort(a, gt+1, hi, d);
  }

  private static void exch(Comparable[] a, int i, int j) {
    Comparable temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }

  public static void main(String[] args) {
    String[] a = {"4PGC938", "2IYE230fsd", "3CIO720", "1ICK", "1OHV8e45", "4J524", "1ICK750"
        , "3CIO720", "1OHV845", "fwe3", "2RLA629we", "2RLA629", "3ATWgfg723"};
    MSD.sort(a);
    for (String item : a) {
      System.out.println(item);
    }
  }
}
