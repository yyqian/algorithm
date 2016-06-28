package com.yyqian.algorithm.sort;

import com.yyqian.algorithm.sort.helper.AbstractSort;

/**
 * Created by yyqian on 5/22/16.
 *
 * 这个算法是 Insertion 的变种, 解决了 Insertion 算法交换的位置可能过远的问题
 * 它先进行粗粒度的排序, 最后才进行最细粒度的排序（最后一遍跟 Insertion 相同）
 *
 * 如果我们有个长度为 14 的数组
 * 我们先进行 h = 4 的排序, 也就是把这个数组分成几个小组:
 *
 * (1) 0   4   8   12
 * (2)  1   5   9   13
 * (3)   2   6   10
 * (4)    3   7    11
 *
 * 第一次排序: i = 4, 我们对小组(1)进行第一次插入排序（也就是把第 4 个元素往前面一个元素插）
 * 第二次排序: i = 5, 我们对小组(2)进行第一次插入排序（也就是把第 5 个元素往前面一个元素插）
 * 第三次排序: i = 6, 我们对小组(3)进行第一次插入排序（也就是把第 6 个元素往前面一个元素插）
 * 第四次排序: i = 7, 我们对小组(4)进行第一次插入排序（也就是把第 7 个元素往前面一个元素插）
 * 第五次排序: i = 8, 我们对小组(1)进行第二次插入排序（也就是把第 8 个元素往前面两个元素插）
 * ...
 *
 * 这个算法比 Insertion 优异是因为:
 * 1. 它一开始排序每个小组的长度很小, 这点适用于 Insertion 算法
 * 2. 之后粒度变细之后的小组因为前面已经初步排序了, 所以 Insertion 算法在这种情况下很高效
 *
 * ShellSort 的时间复杂度的分析比较复杂, 不用去深入了解, 已知的最差的情况是: O(n^1.5), 远高于 Insertion 和 Selection
 *
 * 这个算法可用于中大型的数组, 代码量较少, 空间复杂度是 O(1), 适用于嵌入式系统或者硬件。
 * 即使是更高效的算法, 可能也就比 ShellSort 快个两倍。
 * 因此 SheelSort 是个有实用价值的排序算法。
 */
public class Shell extends AbstractSort {
  public static void sort(Comparable[] a) {
    int N = a.length;
    int h = 1;
    while (h < N/3) {
      h = 3 * h + 1; // 1 4 13 40...
    }
    // increment sequence, h 表示的是一个粒度, 我们从粗粒度开始插入排序
    while (h >= 1) {
      // 这里 i 的遍历实际是在对不同的小组进行轮流的插入排序, h 次之后会轮到一次
      for (int i = h; i < N; i++) {
        // 将当前小组、当前位置的元素, 向小组前面几个元素中插入
        for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
          exch(a, j, j - h);
        }
      }
      h = h / 3;
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

