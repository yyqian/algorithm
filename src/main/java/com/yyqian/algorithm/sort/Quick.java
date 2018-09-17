package com.yyqian.algorithm.sort;

import com.yyqian.algorithm.sort.helper.AbstractSort;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by yyqian on 5/23/16.
 *
 * 当我们排序 a[lo..hi] 数组的时候:
 *
 * 首先, 调用 partition 方法: 以 a[lo] 为参照值, 从数组的两头向中间搜寻
 * 目的是把数组分为「小于或等于参照值」和「大于或等于参照值」的两个区域
 * 手段是左指针找到大于或等于参照值的位置就停下, 右指针找到小于或等于参照值的位置停下, 然后交换两个元素
 * 直到它们在中间汇合, 这个时候将 a[lo] 和左区域最右边的元素交换, 就完成了分区, 得到的分区满足:
 * a[lo..j-1] <= a[j] <= a[j+1..hi]
 *
 * 接下来, 我们再递归地处理 a[lo..j-1] 和 a[j+1..hi] ...
 *
 * 时间复杂度: O(NlgN)
 *
 * 这个算法最好的情况是每次 partition 正好对半分, 最差情况是 partition 之后左右两边严重的不平衡
 *
 * 改进的方法:
 * 1. 当 partition 之后的数组长度很小的时候（大概长度为 5 - 15 的时候）, 改用插入排序
 * 2. 对于有很多重复数据的数组, 可以用 3-way partitioning, 把一个数组划分成 < V, = V, > V 三个区域
 *    可以提高效率, 但是如果数据都是 distinct, 那么反而会降低效率
 *
 */
public class Quick extends AbstractSort {

  public static void sort(Comparable[] a) {
    Collections.shuffle(Arrays.asList(a)); // 打乱排序是为了避免最坏情况发生
    sort(a, 0, a.length - 1);
  }

  public static void sort(Comparable[] a, int lo, int hi) {
    if (lo >= hi) return;
    int j = partition(a ,lo, hi);
    sort(a, lo, j - 1);
    sort(a, j + 1, hi);
  }

  private static int partition(Comparable[] a, int lo, int hi) {
    Comparable v = a[lo];
    int i = lo; // 真正从头部开始比较的位置是 lo + 1, 后面会有 ++i
    int j = hi + 1; // 从尾部开始比较的位置是 hi, 后面会有 --j
    while (true) {
      // 如果当前指针的位置的值小于 v, 可以继续; 否则就暂停, 暂停位置的值大于或等于 v, 等待交换
      // 这个地方交换和 v 相等的元素看似是多余的, 实际上是有意图的
      while (less(a[++i], v)) {
        // 如果整列数组都比 v 小, 指针可能会越界
        if (i == hi) break;
      }
      while (less(v, a[--j])) {
        // 预防越界, 这一步是多余的, less(v, v) 会自动 break
        if (j == lo) break;
      }
      if (i >= j) break;
      exch(a, i, j); // 交换 >v 和 <v 的数
    }
    exch(a, lo, j); // 这个时候 j 比 i 小或相等, 指向的是比 v 小的数, 所以交换 j 而不是 i
    return j;
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
