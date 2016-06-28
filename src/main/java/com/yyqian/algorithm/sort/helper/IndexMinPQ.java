package com.yyqian.algorithm.sort.helper;

/**
 * Created by yyqian on 5/31/16.
 *
 * 这个数据结构在 Prim 和 Dijkstra 算法中都有使用
 */
public class IndexMinPQ<K extends Comparable<K>> {
  private int N; // number of elements
  private int[] pq; // index
  private int[] qp; // inverted index
  private K[] keys; // 保存推入的数据

  public IndexMinPQ(int maxN) {
    if (maxN < 0) throw new IllegalArgumentException();
    keys = (K[]) new Comparable[maxN + 1];
    pq = new int[maxN + 1];
    qp = new int[maxN + 1];
    for (int i = 0; i <= maxN; i++) qp[i] = -1;
  }

  public void insert(int k, K key) {
    N++;
    pq[N] = k;
    qp[k] = N;
    keys[k] = key;
    swim(N);
  }

  public int minIndex() {
    return pq[1];
  }

  public K minKey() {
    return keys[pq[1]];
  }

  public int delMin() {
    int min = pq[1];
    exch(1, N--);
    sink(1);
    qp[min] = -1;
    keys[min] = null;
    pq[N+1] = -1;
    return min;
  }

  public K keyOf(int i) {
    return keys[i];
  }

  public void changeKey(int i, K key) {
    keys[i] = key;
    swim(qp[i]);
    sink(qp[i]);
  }

  public void change(int i, K key) {
    changeKey(i, key);
  }

  public void delete(int i) {
    int index = qp[i];
    exch(index, N--);
    swim(index);
    sink(index);
    keys[i] = null;
    qp[i] = -1;
  }

  private boolean less(int i, int j) {
    return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
  }

  private void exch(int i, int j) {
    int swap = pq[i];
    pq[i] = pq[j];
    pq[j] = swap;
    qp[pq[i]] = i;
    qp[pq[j]] = j;
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

  public boolean containts(int i) {
    return qp[i] != -1;
  }

  public int size() {
    return N;
  }

  public boolean isEmpty() {return N == 0;}
}
