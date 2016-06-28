package com.yyqian.algorithm.sort.helper;

/**
 * Created by yyqian on 5/25/16.
 *
 * Priority Queue 有以下实现方式:
 * 1. unordered array, 插入时复杂度是 O(1), 删除时是 O(N)
 * 2. ordered array, 插入时复杂度是 O(N), 删除时是 O(1)
 * 3. heap, 插入时复杂度是 O(lgN), 删除时是 O(lgN)
 *
 * Heap 是一棵 binary tree（见 page 314 页）, 它的要求是:
 * 1. 父节点的值大于或等于自己的两个子节点的值
 * 2. 节点 k 的两个子节点是 2 * k 和 2 * k + 1
 * 3. 节点 k 的父节点是 k / 2
 */
public class MaxPQ<K extends Comparable<K>> {
  private K[] pq;
  private int N = 0;

  public MaxPQ(int maxN) {
    pq = (K[])new Comparable[maxN + 1];
  }

  public boolean isEmpty() {
    return N == 0;
  }

  public int size() {
    return N;
  }

  // 我们把新节点先插到尾部, 然后让它向上游
  public void insert(K v) {
    pq[++N] = v;
    swim(N);
  }

  // 这个函数分为三步:
  // 1. 从根节点取出最大值;
  // 2. 交换根节点和最后一个节点, 然后清除最后一个节点（也就是之前的最大值）;
  // 3. 让当前根节点下沉（因为这个节点是从尾部取出的, 所以值相对来说较小）
  public K delMax() {
    K max = pq[1];
    exch(1, N--);
    pq[N + 1] = null;
    sink(1);
    return max;
  }

  private boolean less(int i, int j) {
    return pq[i].compareTo(pq[j]) < 0;
  }

  private void exch(int i, int j) {
    K tmp = pq[i];
    pq[i] = pq[j];
    pq[j] = tmp;
  }

  // 如果当前节点的值比父节点大, 则与父节点交换位置
  private void swim(int k) {
    while (k > 1 && less(k/2, k)) {
      exch(k/2, k);
      k = k/2;
    }
  }

  // 选择当前节点的子节点中值较大的节点, 然后与它进行比较, 如果当前节点较小, 则与之交换位置
  private void sink(int k) {
    while (2*k <= N) {
      int j = 2*k;
      if (j < N && less(j, j+1)) j++;
      if (!less(k, j)) break;
      exch(k, j);
      k = j;
    }
  }
}
