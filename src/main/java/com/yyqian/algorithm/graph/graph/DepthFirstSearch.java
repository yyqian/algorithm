package com.yyqian.algorithm.graph.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by yyqian on 5/30/16.
 * 深度优先搜索（非递归版本）和广度优先搜索的实现非常类似, 区别是一个用 Queue, 一个用 Stack
 */
public class DepthFirstSearch {
  private boolean[] marked;
  private int[] edgeTo;
  private final int s;
  private int count;

  public DepthFirstSearch(Graph G, int s) {
    marked = new boolean[G.V()];
    edgeTo = new int[G.V()];
    this.s = s;
    dfs(G, s);
  }

  // 如果一个节点有多个分支, dfs 先对一个分支进行深入探索, 在探索完毕后再从根部另外一个分支开始探索
  // 时间复杂度: V + E
  private void dfs(Graph G, int v) {
    marked[v] = true;
    count++;
    for (int w : G.adj(v)) {
      if (!marked[w]) {
        edgeTo[w] = v; // 记录第一次到 w 的节点
        dfs(G, w); // 如果没有访问过就继续深入查询
      }
    }
  }


  private void dfsWithStack(Graph G, int s) {
    // use offer, poll, peek
    Deque<Integer> stack = new LinkedList<>();
    marked[s] = true;
    stack.push(s);
    while (!stack.isEmpty()) {
      int vertex = stack.pop();
      for (int w : G.adj(vertex)) {
        if (!marked[w]) {
          marked[w] = true;
          edgeTo[w] = vertex;
          stack.push(w);
        }
      }
    }
  }

  public boolean hasPathTo(int v) {
    return marked[v];
  }

  public Iterable<Integer> pathTo(int v) {
    if (!hasPathTo(v)) return null;
    // use push, pop, peek
    Deque<Integer> path = new ArrayDeque<>();
    // 根据 edgeTo 从尾部开始向后推
    for (int x = v; x != s; x = edgeTo[x]) {
      path.push(x);
    }
    path.push(s);
    return path;
  }

  public boolean marked(int w) {
    return marked[w];
  }

  public int count() {
    return count;
  }
}
