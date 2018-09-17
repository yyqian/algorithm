package com.yyqian.algorithm.sort;

import com.yyqian.algorithm.sort.helper.AbstractSort;

/**
 * Created by yyqian on 5/22/16.
 * <p>
 * 这个算法和我们打牌时整理牌一样, 我们从左到右一张张牌进行检查
 * 如果当前牌比左侧牌小, 则跟左侧交换位置, 然后一直向左检查（反复交换）
 * 直到当前牌不比左侧牌小, 这个时候就能退出循环了（因为左侧是排序完毕的）
 * 然后检查下一张牌, 重复前面操作
 * <p>
 * 复杂度:
 * 平均: N^2/4 次比较, N^2/4 次交换
 * 最差: N^2/2 次比较, N^2/2 次交换
 * 最好: N - 1 次比较, 0 次交换
 * <p>
 * 优点: 如果当前数组已经差不多有序了, 排序的速度会快很多（最好的情况效率最佳）
 *
 * 这个算法是 ShellSort 的基础, 记住 ShellSort 的实现就行
 */
public class Insertion extends AbstractSort {

  public static void sort(Comparable[] a) {
    int N = a.length;
    int h = 1;
    for (int i = h; i < N; i++) {
      for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
        exch(a, j, j - h);
      }
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
