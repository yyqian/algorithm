package com.yyqian.algorithm.sort;

import com.yyqian.algorithm.sort.helper.AbstractSort;

/**
 * Created by yyqian on 5/22/16.
 *
 * 时间复杂度是 O(NlgN)
 * 这个复杂度的计算可以用一棵二叉树来计算, 这棵树的叶子是 a[0..1] a[2..3] ... a[14..15]
 * 树的高度是 h = lgN
 * kth level 有 2^k 个节点, 每个节点的数组有 2^(h - k) 个元素, 因此每个节点最多 2^(h - k) 次比较操作, 所以一层的所有操作是 2^h
 * 整棵树的比较操作数就是 h 2^h = NlgN
 *
 * 空间复杂度是 N（aux 数组）, 对空间利用率太低
 *
 * 对于小数组（长度 15 以下）,insertion sort 更加高效
 *
 */
public class Merge extends AbstractSort {

  private static Comparable[] aux;

  public static void sort(Comparable[] a) {
    aux = new Comparable[a.length]; // 这个临时数组是在合并两个排序完毕的数组使用的
    sort(a, 0, a.length - 1); // 我们定义了一个递归版本的排序,
  }

  /*
   * 如果 sort(a, i, i + 1), 则:
   * mid = i
   * sort(a, i, i) // do nothing
   * sort(a, i + 1, i + 1) // do nothing
   * merge(a, i, i, i + 1) // 虽然这种情况上面两个 sort 都没有干任何事, 但实际上这个时候左右两个数组都只有一个元素, 所以不用排序就已经是有序的了
   */
  public static void sort(Comparable[] a, int lo, int hi) {
    if (hi <= lo) return; // hi = lo 的情况会发生, 这个时候就一个元素, 不需要排序。hi < lo 实际上应该不会发生
    int mid = lo + (hi - lo) / 2;
    sort(a, lo, mid); // 分治法, 分成两个数组来排序, 这个函数返回的时候, a[lo ... mid] 应当是排序完毕的
    sort(a, mid + 1, hi); // 返回时 a[mid+1 ... hi] 应当已经排序完毕
    merge(a, lo, mid, hi); // 合并两个已经排序完毕的数组
  }

  // bottom-up version sort
  public static void sortBU(Comparable[] a) {
    int N = a.length;
    aux = new Comparable[N];
    for (int sz = 1; sz < N; sz = sz + sz) {
      for(int lo = 0; lo < N - sz; lo += sz + sz) {
        merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1)); // 最右侧合并的时候不一定是等长的两个数组
      }
    }
  }

  /*
   * 如果 merge(a, 0, 0, 1), 则:
   * i = 0
   * j = 1
   * 第一次循环要先进行比较 aux[i] 和 aux[j], i 和 j 中必有一个元素插入数组 a
   * 第二次循环 i 和 j 必有一个落入 if 的前两种情况之一（因为一开始 i = mid = 0, j = hi = 0, i++ 或者 j++ 之后必定指针溢出）
   * 所以第二次循环会把另外一个值插入
   */
  public static void merge(Comparable[] a, int lo, int mid, int hi) {
    int i = lo; // 这个指针指向左边的排好的数组
    int j = mid + 1; // 这个指针指向右边的排好的数组
    System.arraycopy(a, lo, aux, lo, hi + 1 - lo);
    // 用 aux 作为原始数据, 重新挨个填充 a
    for (int k = lo; k <= hi; k++) {
      if (i > mid) a[k] = aux[j++]; // 特殊情况: 左边的指针已经溢出, 说明左边的数据已经全部插入到数组 a 中了, 接下来就可以插入所有的右边的数据了
      else if (j > hi) a[k] = aux[i++]; // 同上一条
      else if (less(aux[j], aux[i])) a[k] = aux[j++]; // 正常情况: 小的元素插入数组 a, 插入后指针要右移
      else a[k] = aux[i++]; // 同上一条
    }
  }

  public static void main(String[] args) {
    Integer[] a = {123, 12, 43, 12, 90, -12, 0, 23, 12};
    System.out.print("Original: ");
    show(a);
    //sortBU(a);
    sort(a);
    System.out.print("Sorted: ");
    show(a);
    System.out.println("isSorted: " + isSorted(a));
  }
}
