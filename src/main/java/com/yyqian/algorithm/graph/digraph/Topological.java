package com.yyqian.algorithm.graph.digraph;

/**
 * Created by yyqian on 6/1/16.
 *
 * 调用 DepthFirstOrder 的结果 reversePost
 *
 * root 在前, 叶子在后, 可以把它们想象成依赖关系, 最通用的包要最先导入（例如 java.lang）
 */
public class Topological {

  private Iterable<Integer> order;

  public Topological(Digraph G) {
    DirectedCycle cyclefinder = new DirectedCycle(G);
    if (!cyclefinder.hasCycle()) {
      DepthFirstOrder dfs = new DepthFirstOrder(G);
      order = dfs.reversePost();
    }
  }

  public boolean isDAG() {
    return order != null;
  }

  public Iterable<Integer> order() {
    return order;
  }
}
