package com.yyqian.algorithm.sort;

import com.yyqian.algorithm.sort.helper.AbstractSort;

/**
 * Created by yyqian on 5/26/16.
 *
 * 这个算法是构建在数据结构 heap 基础上的, 先将数组变成 heap-ordered, 然后再不断地取出最大值并且保持 heap 结构
 * 也可以把这个算法想象成将数组加入到 Priority Queue 中, 然后再从 PQ 中将顶部数据取出（PQ 自身来维护结构）。
 * 而这个算法实际上是 in place 的
 *
 * 该算法时间复杂度为 2NlgN, 空间复杂度为常数
 *
 * 这个算法在实际情况中很少使用是因为, 该算法中发生的数据比较一般都不是相邻元素的比较（cache performance 较差）
 * 而 quicksort, mergesort, shellsort 则较多的是相邻元素的比较
 *
 * 除此之外, Heap 数据结构在 Priority Queue 应用较广, 因为它插入和取出都是 lgN 的复杂度
 */
public class Heap extends AbstractSort {

  public static void sort(Comparable[] a) {
    // 注意: 这里的 a[0] 是不存数据的
    int N = a.length - 1;
    // 将当前的数组 a 排序成 heap, 让它变成 heap-ordered
    for (int k = N/2; k >= 1; k--) {
      sink(a, k, N);
    }
    // 我们迭代地把根节点的最大值交换到尾部去, 这个过程称为 sortdown
    while (N > 1) {
      // 由于根节点是最大值, 所以将根节点放到尾部, 并且将待排序的序列长度减一
      exch(a, 1, N--);
      // 将当前的根节点下沉, 注意的是这个时候的序列长度变小了, 下沉的时候不会影响后面排序完毕的序列
      sink(a, 1, N);
    }
  }

  private static void sink(Comparable[] a, int i, int N) {
    while (2 * i <= N) {
      int j = 2 * i;
      if (j < N && less(a[j], a[j + 1])) j++;
      if (!less(a[i], a[j])) break;
      exch(a, i, j);
      i = j;
    }
  }
}
