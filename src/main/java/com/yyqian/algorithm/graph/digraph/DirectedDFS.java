package com.yyqian.algorithm.graph.digraph;

/**
 * Created by yyqian on 5/30/16.
 *
 * 用途: Mark-and-sweep garbage collection
 * 可以找出没有被引用的对象（不可达）
 *
 * Undirected 和 directed Graph 的深度、广度搜索算法基本相同
 */
public class DirectedDFS {

  private boolean[] marked;

  public DirectedDFS(Digraph G, int s) {
    marked = new boolean[G.V()];
    dfs(G, s);
  }

  public DirectedDFS(Digraph G, Iterable<Integer> s) {
    marked = new boolean[G.V()];
    for (int e : s) {
      dfs(G, e);
    }
  }

  private void dfs(Digraph G, int s) {
    marked[s] = true;
    for (int v : G.adj(s)) {
      if (!marked[v]) dfs(G, v);
    }
  }

  public boolean marked(int v) {
    return marked[v];
  }
}
