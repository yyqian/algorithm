package com.yyqian.algorithm.graph.mst.helper;

/**
 * Created by yyqian on 5/31/16.
 */
public class WeightedQuickUnionUF extends QuickUnionUF {
  private int[] sz;

  public WeightedQuickUnionUF(int N) {
    super(N);
    sz = new int[N];
    for (int i = 0; i < N; i++) {
      sz[i] = 1;
    }
  }

  @Override
  public void union(int p, int q) {
    int i = find(p);
    int j = find(q);
    if (i == j) return;
    // 这个算法和 QuickUnion 的差别就在于它把小的树加到大的树下面, 这样树的高度不一定会增加, 反之则很有可能会加一
    // 代价是多一个 sz 数组来保存树的节点数
    if (sz[i] < sz[j]) {
      id[i] = j;
      sz[j] += sz[i];
    } else {
      id[i] = j;
      sz[j] += sz[i];
    }
  }
}
