package com.yyqian.algorithm.sort;

import com.yyqian.algorithm.sort.helper.AbstractSort;

/**
 * Created by yyqian on 5/22/16.
 *
 * 首先, 最小值指针指到第一个元素, 然后遍历数组, 找到最小值
 * 然后最小的元素跟第一个元素交换位置
 * 接下来, 最小值指针指到第二个元素, 遍历后面的数组, 重复之前的步骤
 *
 * 时间复杂度（各种情况下都是这样）:
 * (N - 1) + (N - 2) + ... + 1 + 0 = N(N - 1)/2 约等于 N^2/2
 *
 * 优点: 这个算法只需要交换 N 次元素, 对数据访问的次数是所有排序算法中最少的
 * 缺点: 时间复杂度跟数组原本的排序情况无关, 即使是已经排序好的数组, 也要花同样长的时间
 */
public class Selection extends AbstractSort {

  public static void sort(Comparable[] a) {
    int N = a.length;
    for (int i = 0; i < N - 1; i++) {
      int min = i;
      for (int j = i + 1; j < N; j++) {
        if (less(a[j], a[min])) {
          min = j;
        }
      }
      exch(a, i, min);
    }
  }

  public static void main(String[] args) {
    Integer[] a = {123, 12, 43, 12, 90, -12, 0, 23, 12};
    System.out.print("Original: ");
    show(a);
    sort(a);
    System.out.print("Sorted: ");
    show(a);
    System.out.println("isSorted: " + isSorted(a));
  }
}
