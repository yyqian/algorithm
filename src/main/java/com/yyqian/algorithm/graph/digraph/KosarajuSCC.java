package com.yyqian.algorithm.graph.digraph;

/**
 * Created by yyqian on 5/30/16.
 *
 * 这个算法用来查找 Digraph 中强联通的模块, 实现和无向图的 CC 类似, 但是原理难懂
 *
 * Kosaraju’s algorithm is an extreme example of a method that is easy to code but difficult
 * to understand
 */
public class KosarajuSCC {
  private boolean[] marked;
  private int[] id;
  private int count;

  public KosarajuSCC(Digraph G) {
    marked = new boolean[G.V()];
    id = new int[G.V()];
    // 这一步和后面 order.reversePost() 这一步是这个算法最神秘的地方
    DepthFirstOrder order = new DepthFirstOrder(G.reverse());
    for (int s : order.reversePost()) {
      if (!marked[s]) {
        dfs(G, s);
        count++;
      }
    }
  }

  private void dfs(Digraph G, int v) {
    marked[v] = true;
    id[v] = count;
    for (int w : G.adj(v)) {
      if (!marked[w]) dfs(G, w);
    }
  }

  public boolean stronglyConnected(int v, int w) {
    return id[v] == id[w];
  }

  public int id(int v) {
    return id[v];
  }

  public int count() {
    return count;
  }
}
