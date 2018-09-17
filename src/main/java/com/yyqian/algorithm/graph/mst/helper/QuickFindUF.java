package com.yyqian.algorithm.graph.mst.helper;

/**
 * Created by yyqian on 5/31/16.
 */
public class QuickFindUF extends AbstractUF {

  public QuickFindUF(int N) {
    super(N);
  }

  // quick-find
  @Override
  public int find(int p) {
    return id[p];
  }

  @Override
  public void union(int p, int q) {
    int pID = find(p);
    int qID = find(q);
    if (pID == qID) return;
    for (int i = 0; i < id.length; i++) {
      if (id[i] == pID) id[i] = qID;
    }
    count--;
  }
}
