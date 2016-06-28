package com.yyqian.algorithm.graph.graph;

/**
 * Created by yyqian on 5/30/16.
 *
 * DFS 的应用
 */
public class TwoColor {
  private boolean[] marked;
  private boolean[] color;
  private boolean isTwoColorable = true;

  public TwoColor(Graph G) {
    marked = new boolean[G.V()];
    color = new boolean[G.V()];
    for (int i = 0; i < G.V(); i++) {
      if (!marked[i]) {
        dfs(G, i);
      }
    }
  }

  private void dfs(Graph G, int v) {
    marked[v] = true;
    for (int w : G.adj(v)) {
      if (!marked[w]) {
        // 给下一个节点标记相反的颜色
        color[w] = !color[v];
        dfs(G, w);
      } else if (color[w] == color[v]) {
        // 如果已经标记的节点的颜色相同, 说明不是 TwoColorable
        isTwoColorable = false;
      }
    }
  }
}
