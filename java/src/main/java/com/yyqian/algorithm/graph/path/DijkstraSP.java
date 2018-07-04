package com.yyqian.algorithm.graph.path;

import com.yyqian.algorithm.sort.helper.IndexMinPQ;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by yyqian on 5/31/16.
 *
 * 这个算法和 prim 算法非常相近, 最大的不同是 distTo 存的是该顶点到源点的距离
 *
 * 它可以解决:
 * 1.
 * 2. Single-source shortest paths in undirected graphs
 *
 * 时间复杂度: E log V
 * 空间复杂度: V
 */
public class DijkstraSP implements SP {
  private DirectedEdge[] edgeTo; // 记录各个点在最短路径上的边, Edges on the shortest-paths tree
  private double[] distTo; // 记录源点到各个点的最近距离
  private IndexMinPQ<Double> pq;

  public DijkstraSP(EdgeWeightedDigraph G, int s) {
    edgeTo = new DirectedEdge[G.V()];
    distTo = new double[G.V()];
    pq = new IndexMinPQ<>(G.V());
    for (int v = 0; v < G.V(); v++) {
      distTo[v] = Double.POSITIVE_INFINITY;
    }
    // By convention, edgeTo[s] is null and distTo[s] is 0.
    distTo[s] = 0.0;
    pq.insert(s, 0.0);
    while (!pq.isEmpty()) {
      relax(G, pq.delMin());
    }
  }

  private void relax(EdgeWeightedDigraph G, int v) {
    for (DirectedEdge e : G.adj(v)) {
      int w = e.to();
      if (distTo[w] > distTo[v] + e.weight()) {
        distTo[w] = distTo[v] + e.weight();
        edgeTo[w] = e;
        if (pq.containts(w)) pq.change(w, distTo[w]);
        else pq.insert(w, distTo[w]);
      }
    }
  }

  @Override
  public double distTo(int v) {
    return distTo[v];
  }

  @Override
  public boolean hashPathTo(int v) {
    return distTo[v] < Double.POSITIVE_INFINITY;
  }

  @Override
  public Iterable<DirectedEdge> pathTo(int v) {
    if (!hashPathTo(v)) return null;
    Deque<DirectedEdge> path = new LinkedList<>();
    for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
      path.addFirst(e);
    }
    return path;
  }
}
