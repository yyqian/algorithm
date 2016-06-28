package com.yyqian.algorithm.graph.graph;

/**
 * Created by yyqian on 5/30/16.
 *
 * DFS 的应用
 */
public class Cycle {
  private boolean[] marked;
  private boolean hasCycle;

  public Cycle(Graph G) {
    marked = new boolean[G.V()];
    for (int s = 0; s < G.V(); s++) {
      if (!marked[s]) dfs(G, s, s);
    }
  }

  // 这个算法基于一个事实, 如果在深度搜索的过程中, 从来都没遇到过 marked 节点, 说明是 acycle 的
  // 如果遇到了 marked 节点, 说明和另外一条路径相通了, 所以可以组成一个 cycle
  public void dfs(Graph G, int v, int u) {
    marked[v] = true;
    for (int w : G.adj(v)) {
      if (!marked[w]) {
        dfs(G, w, v);
      } else if (w != u) {
        // 这里要判断 w != u 的原因是, u 是 v 的一个相邻点, 所以 w 有可能会是 u 本身, 这个时候不是 Cycle 的
        hasCycle = true;
      }
    }
  }

  public boolean hasCycle() {
    return hasCycle;
  }

}
