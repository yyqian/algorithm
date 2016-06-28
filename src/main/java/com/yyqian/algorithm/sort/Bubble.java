package com.yyqian.algorithm.sort;

import com.yyqian.algorithm.sort.helper.AbstractSort;

/**
 * Created by yyqian on 5/26/16.
 *
 * 这个算法比较简单, 就是不断地把相邻两个元素中大的交换到前面去, 一轮之后尾部就是最大值
 * 下一轮让序列长度减一（因为尾部的是排序完毕的, 不用再参与进来）, 然后重复上面的步骤
 */
public class Bubble extends AbstractSort {
  public static void sort(Comparable[] a) {
    int N = a.length;
    while (N > 1) {
      for (int i = 1; i < N; i++) {
        if (less(a[i], a[i - 1])) exch(a, i, i - 1);
      }
      N -= 1;
    }
  }
}
