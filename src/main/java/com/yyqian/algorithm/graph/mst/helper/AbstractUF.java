package com.yyqian.algorithm.graph.mst.helper;

/**
 * Created by yyqian on 5/31/16.
 */
public abstract class AbstractUF {
  protected int[] id; // component id
  protected int count;

  public AbstractUF(int N) {
    count = N;
    id = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
    }
  }

  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  // quick-find
  public abstract int find(int p);

  public abstract void union(int p, int q);

}
