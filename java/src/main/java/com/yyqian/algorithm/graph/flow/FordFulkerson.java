package com.yyqian.algorithm.graph.flow;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by yyqian on 6/22/16.
 * 最短增广路径的 Ford-Fulkerson 最大流算法
 */
public class FordFulkerson {
  private boolean[] marked;
  private FlowEdge[] edgeTo;
  private double value;

  public FordFulkerson(FlowNetwork graph, int from, int to) {
    while (hasAugmentingPath(graph, from, to)) {
      double bottle = Double.POSITIVE_INFINITY;
      // 这里先遍历一遍下面要增广的路径, 目的是获得每条路径的剩余容量, 取它们的最小值为瓶颈容量
      for (int v = to; v != from; v = edgeTo[v].other(v)) {
        bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
      }
      // 根据上面计算的瓶颈容量, 遍历增广路径, 增大容量
      for (int v = to; v != from; v = edgeTo[v].other(v)) {
        edgeTo[v].addResidualFlowTo(v, bottle);
      }
      value += bottle;
    }
  }

  // 计算得到增广路径
  private boolean hasAugmentingPath(FlowNetwork graph, int from, int to) {
    marked = new boolean[graph.V()];
    edgeTo = new FlowEdge[graph.V()];
    Deque<Integer> que = new ArrayDeque<>();
    marked[from] = true;
    que.add(from);
    while (!que.isEmpty()) {
      int start = que.remove();
      for (FlowEdge e : graph.adj(start)) {
        int end = e.other(start);
        if (e.residualCapacityTo(end) > 0 && !marked[end]) {
          edgeTo[end] = e;
          marked[end] = true;
          que.add(end);
        }
      }
    }
    return marked[to];
  }

}
