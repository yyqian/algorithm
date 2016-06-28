package com.yyqian.algorithm.graph.digraph;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yyqian on 5/30/16.
 *
 * 这个算法实际上就是在 dfs 的过程中, 将首先返回的节点 push 到 stack 中, 相当于把叶子首先 push 到 stack 中
 * 同级别的节点 push 到 stack 中的先后顺序无所谓
 *
 * 参考 Page 579
 */
public class DepthFirstOrder {
  private boolean[] marked;
  private Queue<Integer> pre;
  private Queue<Integer> post;
  private Deque<Integer> reversePost;

  public DepthFirstOrder(Digraph G) {
    pre = new LinkedList<>();
    post = new LinkedList<>();
    reversePost = new LinkedList<>();
    marked = new boolean[G.V()];
    for (int v = 0; v < G.V(); v++) {
      if (!marked[v]) dfs(G, v);
    }
  }

  private void dfs(Digraph G, int v) {
    pre.add(v);
    marked[v] = true;
    for (int w: G.adj(v)) {
      if (!marked[w]) dfs(G, w);
    }
    // reversePost 就是 topological sort
    post.add(v);
    reversePost.addFirst(v);
  }

  public Iterable<Integer> pre() {
    return pre;
  }

  public Iterable<Integer> post() {
    return post;
  }

  public Iterable<Integer> reversePost() {
    return reversePost;
  }
}
