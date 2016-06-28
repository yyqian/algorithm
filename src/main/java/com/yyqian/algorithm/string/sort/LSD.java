package com.yyqian.algorithm.string.sort;

/**
 * Created by yyqian on 6/2/16.
 *
 * 这就是 Radix Sort
 *
 * 时间复杂度: NW（W 是字符串长度）
 * 复杂度: N
 *
 * 这个算法适用于字符串长度固定的情况
 */
public class LSD {
  public static void sort(String[] a, int W) {
    int N = a.length;
    // Radix 表示字符表中的字符数。
    // 例如, 如果字符表中是纯数字, R = 10; 如果是数字和英文字母, 则需要 26 + 26 + 10 = 62
    // 我们这里用的是 EXTENDED_ASCII 表, 所以 R = 256, lgR = 8
    int R = 256;
    String[] aux = new String[N];
    // 我们从最低位开始一位一位排序（最右端）, 这样可行的原因是对单个位排序是 stable 的
    for (int d = W - 1; d >= 0; d--) {
      // count[0] 我们空着不用
      int[] count = new int[R + 1];
      // 统计每个字符出现的次数
      for (int i = 0; i < N; i++) {
        count[a[i].charAt(d) + 1]++;
      }
      // 把次数转换为index
      for (int r = 0; r < R; r++) {
        count[r + 1] += count[r];
      }
      // 排序: 把数组 a 中的值放到 aux 数组中正确的位置
      for (int i = 0; i < N; i++) {
        aux[count[a[i].charAt(d)]++] = a[i];
      }
      // 把 aux 数组的值再赋值回 a 数组
      for (int i = 0; i < N; i++) {
        a[i] = aux[i];
      }
    }
  }

  public static void main(String[] args) {
    String[] a = {"4PGC938", "2IYE230", "3CIO720", "1ICK750", "1OHV845", "4JZY524", "1ICK750"
        , "3CIO720", "1OHV845", "1OHV845", "2RLA629", "2RLA629", "3ATW723"};
    LSD.sort(a, 7);
    for (int i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
  }
}
