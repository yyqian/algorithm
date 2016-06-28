package com.yyqian.algorithm.graph.path;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yyqian on 6/1/16.
 *
 * 这个算法称作为 SPFA, 是 Bellman-Ford 优化版本
 * 它的核心是 queue 队列, 这个队列中保存的是待优化（relax）的顶点
 * 某个顶点 w 进入队列的条件是: distTo[w] 发生了变化
 *
 * 这里省去了 negative cycle 的检查
 *
 * 时间复杂度: EV
 * 空间复杂度: V
 *
 * 判断负环的应用之一是: 套汇 Arbitrage
 */
public class BellmanFordSP {
  private double[] distTo;
  private DirectedEdge[] edgeTo;
  private boolean[] onQ;
  private Queue<Integer> queue;

  public BellmanFordSP(EdgeWeightedDigraph G, int s) {
    // 初始化
    distTo = new double[G.V()];
    edgeTo = new DirectedEdge[G.V()];
    onQ = new boolean[G.V()];
    queue = new LinkedList<>();
    // 所有点的距离都设置为无穷大
    for (int v = 0; v < G.V(); v++) {
      distTo[v] = Double.POSITIVE_INFINITY;
    }
    // 源点距离设置为 0, push 到队列中
    distTo[s] = 0.0;
    queue.add(s);
    onQ[s] = true;
    while (!queue.isEmpty()) {
      int v = queue.remove();
      onQ[v] = false;
      relax(G, v);
    }
  }

  private void relax(EdgeWeightedDigraph G, int v) {
    for (DirectedEdge e : G.adj(v)) {
      int w = e.to();
      if (distTo[w] > distTo[v] + e.weight()) {
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
        if (!onQ[w]) {
          queue.add(w);
          onQ[w] = true;
        }
      }
    }
  }

}
