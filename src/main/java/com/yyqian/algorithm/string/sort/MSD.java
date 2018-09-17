package com.yyqian.algorithm.string.sort;

/**
 * Created by yyqian on 6/2/16.
 *
 * 这个算法的想法是从最高位开始排序, 排序完后把最高位相同的结果分成一个个个组, 然后对每个组的下一为进行排序, 如此重复
 *
 * 这个算法可以处理字符长度不同的情况
 *
 * 这里面最麻烦的是处理结尾字符串不一样长的情况。
 *
 * char 取值: 0 ~ R-1
 *
 * count:
 * index:      0     1    2  ...  R    R+1
 * 对应的char:  没用  空   0  ...  R-2  R-1
 *
 * count 一开始用来存每个 char 出现的次数
 * 然后用来存每个 char 存放的起始的 index
 * 最后用来分成下一级排序的小组
 *
 * MSD 有个很严重的性能问题:
 * 1. Small subarrays: 当 R 很大, 每一级都分出来大量的子分组, 将导致级数增长的递归操作, 所以当数组大小较小时, 必须改用其他排序方式
 * 2. equal keys: 大量前缀相同的情况, 这样就需要几层之后才能得到结果
 * 3. 空间利用: aux 可以只有一个, 但 count 数组因为在 sort 递归操作中, 所以会产生大量的 count 数组, 非常耗费空间
 *
 * MSD 时间复杂度:
 * N 个随机字符串, 用了 R 个字符, 时间复杂度: N log_R^N（介于 N 和 NW 之间）
 *
 * 空间复杂度为: N + WR（W 是平均的字符串长度）
 *
 * 这个算法适用于随机字符串
 */
public class MSD {
  private static int R = 256;
  private static int M = 15;
  private static String[] aux;

  // 如果取的字符的位置超过字符串长度, 返回 -1
  // 返回值是 0 ~ R - 1
  private static int charAt(String s, int d) {
    if (d < s.length()) return s.charAt(d);
    else return -1;
  }

  public static void sort(String[] a) {
    int N = a.length;
    aux = new String[N];
    sort(a, 0, N-1, 0);
  }

  private static void sort(String[] a, int lo, int hi, int d) {
    if (hi <= lo + M) {
      insertionSort(a, lo, hi, d);
      return;
    }
    // +2 是因为: count[0] 是没用的, count[1] 用来记录 char 为空的数目
    int[] count = new int[R+2];
    // char 为 null 的存在头部, 即 count[1], 其他 char 存在 count[char + 2]
    for (int i = lo; i <= hi; i++) {
      count[charAt(a[i], d) + 2]++;
    }
    // null 放头部, 紧接着是 0, 1, 2
    for (int r = 0; r < R+1; r++) {
      count[r+1] += count[r];
    }
    // 排序: 把数组 a 中的值放到 aux 数组中正确的位置
    for (int i = lo; i<= hi; i++) {
      aux[count[charAt(a[i], d) + 1]++] = a[i];
    }
    // 把 aux 数组的值再赋值回 a 数组
    for (int i = lo; i <= hi; i++) {
      a[i] = aux[i - lo];
    }
    // 对子分组进行排序, 由于之前 count 都执行过 ++ 操作, 这个时候 count[0] 是 char[0] 起始的位置, 以此类推
    for (int r = 0; r < R; r++) {
      sort(a, lo + count[r], lo + count[r+1] - 1, d+1);
    }
  }

  private static void insertionSort(String[] a, int lo, int hi, int d) {
    // Sort from a[lo] to a[hi], starting at the dth character.
    for (int i = lo; i <= hi; i++)
      for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
        exch(a, j, j-1);
  }

  private static boolean less(String v, String w, int d) {
    // substring 截取了 d 之后的字符串, 利用 Java 自身的 compareTo 来完成字符串的比较
    return v.substring(d).compareTo(w.substring(d)) < 0;
  }

  protected static void exch(Comparable[] a, int i, int j) {
    Comparable t = a[i];
    a[i] = a[j];
    a[j] = t;
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
