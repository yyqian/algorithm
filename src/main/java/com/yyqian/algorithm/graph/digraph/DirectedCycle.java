package com.yyqian.algorithm.graph.digraph;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by yyqian on 5/30/16.
 *
 * Page 576
 *
 * 这个对象用来判断 digraph 是否有环
 *
 * 这里面最关键的是用一个 stack 来缓存单次 dfs 途经的节点, 如果探索到的节点出现在 stack 上面
 * 表明有 cycle。在单次 dfs 退出时, 要清空 stack, 这样下次 dfs 的时候 stack 就是空的（都是 false）
 */
public class DirectedCycle {
  private boolean[] marked;
  private int[] edgeTo;
  private Deque<Integer> cycle;
  private boolean[] onStack;

  public DirectedCycle(Digraph G) {
    onStack = new boolean[G.V()];
    edgeTo = new int[G.V()];
    marked = new boolean[G.V()];
    for (int v = 0; v < G.V(); v++) {
      if(!marked[v]) {
        dfs(G, v);
      }
    }
  }

  // undirected graph 判断是否有环比这个简单, 因为它只需要判断 connectivity
  private void dfs(Digraph G, int v) {
    marked[v] = true;
    onStack[v] = true;
    for (int w : G.adj(v)) {
      if (this.hasCycle()) return;
      else if (!marked[w]) {
        edgeTo[w] = v;
        dfs(G, v);
      }
      else if (onStack[w]) {
        // 这个时候已经找到了 cycle 的一个节点: v->w
        // 我们从 v 向后推, 直到 w, 然后单独 push w 和 v, 构成一个环
        cycle = new ArrayDeque<>();
        for (int x = v; x != w; x = edgeTo[x]) {
          cycle.push(x);
        }
        cycle.push(w);
        cycle.push(v);
      }
    }
    // 退出回调时清空 stack
    onStack[v] = false;
  }

  public boolean hasCycle() {
    return cycle != null;
  }

  public Iterable<Integer> cycle() {
    return cycle;
  }
}
