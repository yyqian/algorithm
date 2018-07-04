package com.yyqian.algorithm.graph.digraph;

/**
 * Created by yyqian on 5/30/16.
 *
 * 用来查看是否能从一个顶点到另一个顶点, 就是用深度优先算法从这个顶点出发, 爬一遍
 */
public class TransitiveClosure {
  private DirectedDFS[] all;

  public TransitiveClosure(Digraph G) {
    all = new DirectedDFS[G.V()];
    for (int v = 0; v < G.V(); v++) {
      all[v] = new DirectedDFS(G, v);
    }
  }

  boolean reachable(int v, int w) {
    return all[v].marked(w);
  }
}
