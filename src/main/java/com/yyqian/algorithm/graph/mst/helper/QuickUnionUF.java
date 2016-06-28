package com.yyqian.algorithm.graph.mst.helper;

/**
 * Created by yyqian on 5/31/16.
 *
 * 这个算法会把同一个 Component 的点形成一棵树
 * 每次 find 都会从这个点出发, 向上寻找根节点, 根节点的 id 就是这个 component 的 id
 */
public class QuickUnionUF extends AbstractUF {

  public QuickUnionUF(int N) {
    super(N);
  }

  @Override
  public int find(int p) {
    while (p != id[p]) {
      p = id[p]; // 寻找这个 component 的 root（因为 root 的 p == id[p]）
    }
    return p;
  }

  @Override
  public void union(int p, int q) {
    int pRoot = find(p);
    int qRoot = find(q);
    if (pRoot == qRoot) return;
    // 把两个点合起来实际上就是把它们其中一棵树的根加到另一棵树的根部, 两者形成一种父子关系
    id[pRoot] = qRoot;
    count--;
  }
}
