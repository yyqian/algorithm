package com.yyqian.algorithm.graph.graph;

/**
 * Created by yyqian on 5/30/16.
 *
 * DFS 的应用
 * 见 545 页图解
 */
public class CC {

  private boolean[] marked;
  private int[] id;
  private int count;

  public CC(Graph G) {
    marked = new boolean[G.V()];
    id = new int[G.V()];
    for (int s = 0; s < G.V(); s++) {
      if (!marked[s]) {
        // 用深度搜索爬遍某个节点所在的整个 Component
        dfs(G, s);
        // 这个方法返回之后这个 Component 所有的节点都被 mark 了, 所以这个时候可以增加 count
        count++;
      }
    }
  }

  // 用深度搜索爬完整个 Component, 过程中标记 marked, 以及给节点标记 component id
  private void dfs(Graph G, int v) {
    marked[v] = true;
    id[v] = count;
    for (int w : G.adj(v)) {
      if (!marked[w]) {
        dfs(G, w);
      }
    }
  }

  // 判断两个顶点是否相连
  public boolean connected(int v, int w) {
    return id[v] == id[w];
  }

  // 返回 components 的个数
  public int count() {
    return count;
  }

  // 返回顶点 v 所处的 component 的 id
  public int id(int v) {
    return id[v];
  }
}
